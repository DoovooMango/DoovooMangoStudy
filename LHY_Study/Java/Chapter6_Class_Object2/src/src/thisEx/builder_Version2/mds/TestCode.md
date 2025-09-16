# Builder 패턴 테스트 코드 샘플 (JUnit 5)

> `StudentAdvanced`(순수 자바)와 Lombok DTO의 주요 테스트 패턴 예시입니다.

## 0) 의존성 (Gradle 예시)

```groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.2'
    testImplementation 'org.assertj:assertj-core:3.26.0'
}
test {
    useJUnitPlatform()
}
```

---

## 1) 성공 케이스: 정상 build

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class StudentAdvancedSuccessTest {

    @Test
    void build_success() {
        var s = thisEx.builder.StudentAdvanced.builder(1, "John")
                .grade(12)
                .major("Computer Science")
                .phoneNumber("010-2222-3333")
                .build();

        assertThat(s.getId()).isEqualTo(1);
        assertThat(s.getName()).isEqualTo("John");
        assertThat(s.getGrade()).isEqualTo(12);
        assertThat(s.getMajor()).isEqualTo("Computer Science");
        assertThat(s.getPhoneNumber()).isEqualTo("010-2222-3333");
    }
}
```

---

## 2) 검증 케이스: 경계값/예외

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class StudentAdvancedValidationTest {

    @Test
    void grade_out_of_range_then_throw() {
        assertThatThrownBy(() ->
            thisEx.builder.StudentAdvanced.builder(1, "John").grade(0).build()
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->
            thisEx.builder.StudentAdvanced.builder(1, "John").grade(99).build()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void id_must_be_positive() {
        assertThatThrownBy(() ->
            thisEx.builder.StudentAdvanced.builder(0, "John").build()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void name_required() {
        assertThatThrownBy(() ->
            thisEx.builder.StudentAdvanced.builder(1, " ").build()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void phone_format_invalid() {
        assertThatThrownBy(() ->
            thisEx.builder.StudentAdvanced.builder(1, "John").phoneNumber("01012345678").build()
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
```

---

## 3) 파라미터라이즈드 테스트 (여러 잘못된 전화번호 케이스)

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

class PhoneNumberParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = { "01012345678", "010-12-34567", "02-12-3456", "abc-def-ghij" })
    void invalid_phone_numbers(String input) {
        assertThatThrownBy(() ->
            thisEx.builder.StudentAdvanced.builder(1, "Kim").phoneNumber(input).build()
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
```

> Gradle에 `junit-jupiter-params` 의존성이 포함되어 있어야 합니다. (최신 JUnit 5 BOM 사용 시 포함 가능)

---

## 4) Lombok DTO 테스트 (`@Value` + `@Builder`)

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LombokStudentDtoTest {

    @Test
    void builder_and_defaults() {
        var dto = new StudentDto.StudentDtoBuilder()
                .id(1).name("John").build(); // major 기본값 "미정"

        assertThat(dto.build().getMajor()).isEqualTo("미정");
    }

    @Test
    void toBuilder_updates_field() {
        var origin = StudentDto.builder().id(1).name("John").major("CS").build();
        var changed = origin.toBuilder().major("SE").build();
        assertThat(changed.getMajor()).isEqualTo("SE");
        assertThat(changed).isNotSameAs(origin);
    }
}
```

> `StudentDto`는 `@Value @Builder(toBuilder = true)`로 선언되어 있다고 가정합니다.

---

## 5) 픽스처/유틸: 공통 빌더 베이스

```java
public final class Fixtures {
    private Fixtures(){}

    public static thisEx.builder.StudentAdvanced.Builder aStudent() {
        return thisEx.builder.StudentAdvanced.builder(1, "John")
                .grade(3)
                .major("CS")
                .phoneNumber("010-2222-3333");
    }
}
```

```java
class FixtureUsageTest {
    @org.junit.jupiter.api.Test
    void use_fixture() {
        var s = Fixtures.aStudent().build();
        org.assertj.core.api.Assertions.assertThat(s.getGrade()).isEqualTo(3);
    }
}
```

---

## 6) 매핑 테스트 (DTO → Entity)

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MappingTest {

    @Test
    void command_to_entity() {
        var cmd = CreateStudentCommand.builder()
                .name("Jane")
                .grade(2)
                .major("CS")
                .phoneNumber("010-1111-2222")
                .build();

        var e = cmd.toEntity();

        assertThat(e.getId()).isNull();          // 저장 전
        assertThat(e.getName()).isEqualTo("Jane");
        assertThat(e.getGrade()).isEqualTo(2);
        assertThat(e.getMajor()).isEqualTo("CS");
    }
}
```

---

## 7) 체크리스트

- [ ] **성공/실패/경계값**을 모두 검증했는가?
- [ ] **메시지**가 충분히 구체적인가?(디버깅 편의)
- [ ] **픽스처**(공통 빌더)로 중복을 줄였는가?
- [ ] **불변성**(setter 부재, 컬렉션 불변)을 간접적으로 검증했는가?
- [ ] DTO ↔ Entity **매핑 테스트**를 작성했는가?
