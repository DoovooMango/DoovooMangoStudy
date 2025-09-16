# JPA 친화적인 변환 가이드 (Builder → Entity)

> 빌더 패턴으로 조립한 값/명령 객체를 **JPA 엔티티**로 자연스럽게 전환하는 방법과 주의사항을 정리했습니다.

## 0) 왜 엔티티에 빌더를 바로 붙이지 않나요?

- JPA는 **프록시 생성**과 **지연 로딩** 등을 위해
    - `@NoArgsConstructor(access = PROTECTED)` (파라미터 없는 생성자)
    - 필드의 **가변성**(완전 불변이 어려움)
      을 요구하는 경우가 많습니다.
- 따라서 엔티티 자체를 강한 불변으로 만들기보다는,
    - **DTO/Command를 빌더로 조립 → 검증**
    - **엔티티는 최소한의 가변 + 명확한 행위 메서드 제공**
      전략이 유지보수에 유리합니다.

---

## 1) 추천 패턴: DTO/Command 빌더 → 엔티티 생성

```java
// 1) DTO/Command (빌더 사용)
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateStudentCommand {
    String name;
    Integer grade;
    @Builder.Default String major = "미정";
    String phoneNumber;

    public Student toEntity() {
        return Student.builder()
                .name(name)
                .grade(grade)
                .major(major)
                .phoneNumber(phoneNumber)
                .build();
    }
}
```

```java
// 2) 엔티티 (필요시 빌더 허용, 기본 생성자 필수)
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 규약
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer grade;

    private String major;

    private String phoneNumber;

    @Builder // 편의 빌더 (JPA 기본 생성자와 별개)
    private Student(String name, Integer grade, String major, String phoneNumber) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name은 필수");
        if (grade != null && (grade < 1 || grade > 12)) throw new IllegalArgumentException("grade 1~12");
        this.name = name;
        this.grade = grade;
        this.major = (major != null) ? major : "미정";
        this.phoneNumber = phoneNumber;
    }

    // 의도적인 상태 변경 메서드(행위) 제공
    public void changeMajor(String newMajor) {
        this.major = newMajor;
    }
}
```

### 포인트
- 엔티티에도 `@Builder`를 붙일 수는 있지만, **기본 생성자**는 반드시 필요합니다.
- 검증은 **생성자/빌더 경로**로 들어오는 지점에서 수행하여 **불변 조건 유지**.

---

## 2) 연관관계와 빌더

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    private Department(String name) {
        this.name = name;
    }
}

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private String name;

    @Builder
    private Student2(Department department, String name) {
        this.department = java.util.Objects.requireNonNull(department);
        this.name = name;
    }
}
```

### 포인트
- 연관 엔티티를 빌더로 주입할 때는 **식별자(Long)** 대신 **엔티티 참조**를 넘기는 편이 안전합니다.  
  (트랜잭션/영속성 컨텍스트에서 관리되는 엔티티를 보장하기 쉬움)

- **FK 원시값 + 연관 엔티티를 동시에 매핑**해야 한다면 아래처럼 분리:
```java
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "dept_id", nullable = false)
private Department department;

@Column(name = "dept_id", insertable = false, updatable = false)
private Long departmentId; // 읽기 전용 미러링 필드
```
> 같은 필드에 `@JoinColumn`과 `@Column`을 **동시에** 붙이지 마세요.  
> **분리된 필드**로 매핑하고, `insertable=false, updatable=false`로 **읽기 전용**임을 명시합니다.

---

## 3) 값 타입(Embeddable)로 깔끔하게

```java
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class PhoneNumber {

    @Column(name = "phone_number")
    private String value;

    public static PhoneNumber of(String raw) {
        if (raw == null || !raw.matches("\\d{2,3}-\\d{3,4}-\\d{4}")) {
            throw new IllegalArgumentException("전화번호 형식");
        }
        return new PhoneNumber(raw);
    }
}
```

```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student3 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Builder
    private Student3(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
```

### 포인트
- **검증이 필요한 문자열/숫자**는 `@Embeddable` 값 타입으로 분리하면 강력한 **도메인 규칙 캡슐화**가 됩니다.

---

## 4) 전환 흐름 예시 (Service 계층)

```java
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Long create(CreateStudentCommand cmd, Long deptId) {
        Department dept = departmentRepository.getReferenceById(deptId); // or findById
        Student2 student = Student2.builder()
                .department(dept)
                .name(cmd.getName())
                .build();
        return studentRepository.save(student).getId();
    }
}
```

---

## 5) 체크리스트

- [ ] 엔티티에 **기본 생성자**가 있는가? (보통 `PROTECTED`)
- [ ] 검증은 엔티티 생성자/빌더 또는 Command에서 **일관**되게 수행되는가?
- [ ] 연관 관계는 원시 FK보다 **엔티티 참조**를 넘기는가?
- [ ] FK를 원시값으로도 읽어야 한다면 **분리 필드 + 읽기 전용** 매핑인가?
- [ ] 값 타입(Embeddable)으로 도메인 규칙을 캡슐화할 수 있는가?
- [ ] 엔티티는 **행위 메서드**로 의도된 변경만 허용하는가?
