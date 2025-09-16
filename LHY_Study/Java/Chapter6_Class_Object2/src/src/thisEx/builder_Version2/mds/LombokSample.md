# Lombok Builder 샘플 모음 (Cheat Sheet)

> Lombok을 이용해 **보일러플레이트를 줄이면서** 빌더 패턴을 적용하는 실전 예시 모음입니다.

## 1) 기본 값 객체(Value Object) + `@Builder`

```java
import lombok.Builder;
import lombok.Value;

@Value               // 모든 필드 private final + getter + equals/hashCode + toString
@Builder             // 클래스 레벨에 붙여야 @Builder.Default 동작
public class StudentDto {
    int id;
    String name;

    Integer grade;

    @Builder.Default
    String major = "미정";      // 클래스 레벨 @Builder일 때만 동작

    String phoneNumber;
}
```

### 포인트
- `@Value`는 **불변**값 객체를 쉽게 만듭니다(모든 필드 `private final`, setter 없음).
- **클래스에 `@Builder`**를 붙여야 `@Builder.Default`가 적용됩니다.  
  (생성자/메서드에 `@Builder`를 붙인 경우 `@Builder.Default`는 **무시**됩니다.)

---

## 2) 생성자에 `@Builder` 붙이고 검증 넣기

```java
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudentDto2 {
    private final int id;
    private final String name;
    private final Integer grade;
    private final String major;
    private final String phoneNumber;

    @Builder // 이 경우 기본값은 코드로 직접 처리
    public StudentDto2(int id, String name, Integer grade, String major, String phoneNumber) {
        if (id <= 0) throw new IllegalArgumentException("id는 양수여야 합니다.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name은 필수입니다.");
        if (grade != null && (grade < 1 || grade > 12)) {
            throw new IllegalArgumentException("grade는 1~12 범위");
        }
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.major = major != null ? major : "미정"; // 기본값 수동 처리
        this.phoneNumber = phoneNumber;
    }
}
```

### 포인트
- **검증 로직**을 생성자에 모으면, 어떤 진입점에서도 일관된 유효성 보장이 가능합니다.
- 이 경우 `@Builder.Default` 대신 **생성자 내부에서 기본값 처리**를 해 주세요.

---

## 3) 컬렉션 + `@Singular`

```java
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CoursePlan {
    int year;

    @Singular("course")     // .course("알고리즘").course("네트워크")
    List<String> courses;   // 최종 build() 시 불변 리스트로 감쌉니다.
}
```

### 포인트
- 테스트/조립 코드에서 `courses(List)`를 한 번에 넣어도 되고, `course(...)`를 여러 번 호출해도 됩니다.
- Lombok이 빌더 단계에서 컬렉션을 **방어적 복사 + 불변**으로 감싸줍니다.

---

## 4) 상속 구조: `@SuperBuilder`

```java
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
class Person {
    private String name;
}

@Getter
@SuperBuilder
@NoArgsConstructor
class Student extends Person {
    private Integer grade;
}
```

### 포인트
- 상속 계층에서 빌더가 필요하면 `@SuperBuilder`를 사용합니다.
- JPA 엔티티에 쓸 땐 `@NoArgsConstructor(access = PROTECTED)` 같이 제약을 함께 고려하세요(아래 JPA 가이드 참조).

---

## 5) `toBuilder = true` 로 손쉬운 복제/수정

```java
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class StudentDto3 {
    int id;
    String name;
    Integer grade;
    String major;
    String phoneNumber;
}

// 사용
StudentDto3 origin = StudentDto3.builder()
        .id(1).name("John").grade(12).major("CS").phoneNumber("010-2222-3333")
        .build();

StudentDto3 changed = origin.toBuilder()
        .major("Software Engineering")
        .build();
```

---

## 6) Tip 모음

- **어디에 빌더를 붙일까?**
    - 도메인 **엔티티보다는 DTO/Command/Response** 객체에 먼저 적용하세요. (엔티티는 JPA 제약이 큼)
- **기본값**이 필요하면: **클래스 레벨 `@Builder + @Builder.Default`** 또는 **생성자 내부 처리**.
- **검증**은 생성자/`build()` 한 곳에 모으세요(흩어지면 유지보수 곤란).
- **컬렉션**은 `@Singular` + 불변으로 감싸는 습관.
- **상속**이 필요한 경우 `@SuperBuilder` 사용(단, JPA와 병행 시 추가 제약 주의).

