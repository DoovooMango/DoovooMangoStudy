# Builder Pattern Java Demo

두 버전의 예제를 한 패키지(`thisEx.builder`)에 담았습니다.

## 파일 구성
- `Student.java` : 빌더 적용 **전** 예시 (텔레스코핑 생성자 + 체이닝 setter)
- `BeforeDemo.java` : 적용 전 문제가 어떻게 나타나는지 콘솔로 확인
- `StudentAdvanced.java` : 빌더 적용 **후** 예시 (불변 객체 + 선택 파라미터 + 검증)

## 컴파일 & 실행
```bash
# 컴파일
javac thisEx/builder/*.java

# 적용 전 데모 실행
java thisEx.builder.BeforeDemo

# 적용 후 데모 실행 (StudentAdvanced에 main 포함)
java thisEx.builder.StudentAdvanced
```

## 포인트
- 생성자 오버로딩은 필드가 많아지면 순서 실수 위험 ↑
- 빌더는 메서드 이름으로 의미가 드러나 가독성/안전성 ↑
- `build()`에서 최종 **검증**을 수행하고, 완성 후 **불변 객체**로 사용할 수 있음

---

## 4) 빌더 vs 체이닝 setter vs 생성자 오버로딩 — 한눈 비교

- **생성자 오버로딩**
    - ✅ 필드가 2~3개처럼 **적을 때** 간단하고 빠릅니다.
    - ❌ 필드가 늘수록 **순서 혼동**과 가독성 저하가 발생합니다.
    - ❌ 이름이 같은 타입(예: `String`)이 여러 개면 **컴파일 타임에 오류가 드러나지 않음**.

- **체이닝 setter**
    - ✅ 문법이 간결하고 익히기 쉽습니다.
    - ❌ **중간 상태가 노출**(객체가 완성되기 전에 외부에 보임), **불변성/일관성 보장**이 어렵습니다.
    - ❌ 필수값 누락이 런타임 전까지 숨어 있을 가능성↑

- **빌더**
    - ✅ **선택적 필드가 많을 때** 최적. 메서드 이름으로 값의 의미가 드러나 **가독성↑**.
    - ✅ `build()`에서 **최종 검증**을 모아 처리, 생성 후 **불변 객체**로 유지 가능.
    - ❌ 보일러플레이트(코드량) 증가 → Lombok 등으로 완화 가능.

> 미니 예시
```java
// (나쁨) 생성자 순서 실수라도 컴파일 에러가 안 날 수 있음
new Student(12345, "김원상", 3, "010-2222-3333", "컴퓨터공학"); // phone↔major 뒤바뀜

// (좋음) 빌더는 의미가 메서드로 드러남
StudentAdvanced.builder(1, "John")
    .major("Computer Science")
    .phoneNumber("010-2222-3333")
    .build();
```

## 5) 실무 팁

- **언제 쓰나?**
    - 필드가 많고(대략 **4개 이상**), 그중 **선택적 필드**가 절반 이상일 때
    - 생성 시 **검증이 필요**하거나, **불변(Immutable)** 이 유리한 도메인(예: 값 객체, 설정 스냅샷)

- **언제 과한가?**
    - DTO/Record처럼 단순한 값 묶음(필드 2~3개)은 **생성자/정적 팩토리**가 더 단순

- **검증 위치**
    - 개별 설정 시점이 아닌 **`build()`에서 일괄 검증** → 에러 메시지를 한 곳에서 관리

- **기본값 지정**
    - 선택 필드는 **빌더 필드에서 기본값**을 지정(예: `String major = "미정";`)

- **재사용 주의**
    - **빌더 인스턴스 재사용 금지**. 한 번 `build()` 한 빌더를 재사용하면 이전 상태가 섞일 수 있음.

- **상속 + 빌더는 난도↑**
    - 가능하면 **조합(Composition)** 우선. 불가피하면 *self-type 제네릭* 패턴, 또는 Lombok `@SuperBuilder` 고려.

- **도구**
    - Lombok `@Builder`/`@SuperBuilder`로 보일러플레이트 감소.

## 6) 자주 하는 실수 & 해결책

1. **필수값을 강제하지 않음**
    - ✅ 해결: 빌더 **생성자/정적 팩토리**에서 필수값을 받도록 설계
    - ```java
     public static Builder builder(int id, String name) { return new Builder(id, name); }
     private final int id; private final String name;
     ```

2. **빌더 재사용**
    - ✅ 해결: 매번 `StudentAdvanced.builder(...). ... .build()`로 **새 빌더 사용**

3. **가변 컬렉션을 그대로 보관**
    - ✅ 해결: 생성자에서 **방어적 복사** & `Collections.unmodifiableList(…)`

4. **검증이 setter로 분산**
    - ✅ 해결: **`build()`에서 집중 검증**하고, 메시지를 일관되게 제공

5. **생성 후에도 값이 바뀜**
    - ✅ 해결: **필드 `final` + setter 제거**로 **불변성 보장**

## 7) 테스트 팁 (간단 JUnit 예시)

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StudentAdvancedTest {
    @Test
    void builderBuildsImmutableStudent() {
        var s = StudentAdvanced.builder(1, "John")
                .major("CS")
                .phoneNumber("010-2222-3333")
                .build();
        assertEquals(1, s.getId());
        assertEquals("John", s.getName());
        assertEquals("CS", s.getMajor());
        assertEquals("010-2222-3333", s.getPhoneNumber());
        // setter가 없으므로 불변성 보장
    }

    @Test
    void gradeValidation() {
        assertThrows(IllegalArgumentException.class, () ->
            StudentAdvanced.builder(1, "John").grade(0).build()
        );
    }
}
```

## 8) Lombok로 전환하기 (선택)

```java
import lombok.Builder;
import lombok.Value; // 불변 객체 (모든 필드 private final + getter + equals/hashCode/toString)

@Value
@Builder
public class StudentDto {
    int id;
    String name;
    Integer grade;
    @Builder.Default String major = "미정"; // 기본값 사용 시 @Builder.Default 필요
    String phoneNumber;
}
```

- `@Builder.Default`를 빼면 빌더에서 해당 필드가 **null로 초기화**될 수 있습니다.
- 상속 구조라면 `@SuperBuilder`를 고려하세요.

## 9) (참고) JPA, Jackson과 함께 쓰기

- **JPA 엔티티**는 기본 생성자, 프록시 제약 때문에 완전 불변이 힘들 수 있습니다.
    - Tip: 엔티티는 최소한만 가변으로 두고, **명령/값 객체(DTO)** 쪽에서 빌더+불변을 적극 활용하세요.
- **Jackson 직렬화**: 불변 객체는 기본 생성자가 없어도, **모든 필드가 생성자를 통해 채워지면** 역직렬화가 가능합니다.
    - 필요 시 `@JsonCreator`, `@JsonProperty`를 이용해 명시.

## 10) 체크리스트 (요약)

- [ ] 필수값은 빌더 진입점에서 강제했는가?
- [ ] 선택값은 기본값이 정의되어 있는가?
- [ ] 검증은 `build()` 한 곳에서 처리되는가?
- [ ] 생성된 객체는 불변인가(필드 `final`, setter 없음)?
- [ ] (컬렉션 보유 시) 방어적 복사를 적용했는가?
- [ ] (테스트) 필수/선택값, 경계값 검증 케이스를 작성했는가?

---

필요하시면 **Lombok 버전** 또는 **JPA 친화 버전**으로 파일을 추가해 드릴게요.
