# 자바 변수의 자료형 정리 (dataType.md)

> **요약:** 자바의 변수 타입은 **기본 자료형(primitive)** 과 **참조 자료형(reference)** 으로 나뉩니다.  
> 기본형은 **값 자체**를, 참조형은 **객체의 주소(참조)** 를 저장합니다.

---

## 1) 개요
- **기본 자료형:** `byte, short, int, long, float, double, char, boolean`
    - 스택에 값 자체가 저장(필드인 경우 힙의 객체 내부에 값이 배치될 수 있지만, 개념적으로 “값 자체”를 가짐)
    - 크기와 표현이 **고정**(플랫폼 독립)
    - 연산이 빠름, 메모리 효율적
- **참조 자료형:** `String, Date, Student, 배열, 컬렉션, enum, 인터페이스 등 모든 클래스 타입`
    - 변수에는 **객체의 주소**가 저장, 실제 데이터는 **힙(Heap)** 에 존재
    - `null` 가능, 메서드/필드 보유
    - `==`는 동일 객체(주소) 비교, `equals()`는 내용(값) 비교

---

## 2) 기본 자료형 한 눈에 보기

| 타입 | 비트수 | 값의 예 | 비고 |
|---|---:|---|---|
| `byte` | 8 | `-128` ~ `127` | 파일/스트림 처리에 유용 |
| `short` | 16 | 약 `-3만` ~ `3만` | 임베디드/메모리 제한 환경 |
| `int` | 32 | 약 `-21억` ~ `21억` | **기본 정수형**, 루프/카운팅 |
| `long` | 64 | 매우 큰 정수 | 리터럴에 `L` 필요: `10L` |
| `float` | 32 | `3.14f` | 단정도 부동소수점, 리터럴에 `f` |
| `double` | 64 | `3.141592` | **기본 실수형**, 정밀도 높음 |
| `char` | 16 | `'A'`, `'가'` | 유니코드 코드 단위(UTF-16) |
| `boolean` | JVM 의존 | `true/false` | 크기는 명세에 고정 아님(구현 의존) |

> **기본값(필드일 때):** 수치형 `0`, 실수 `0.0`, `char '\u0000'`, `boolean false`  
> **지역변수는 기본값 없음** → **반드시 직접 초기화**해야 함(컴파일 오류 방지).

### 리터럴 & 표기
- 십진수: `42`
- 2진/8진/16진: `0b1010`, `010`, `0xFF`
- 가독성 언더스코어: `1_000_000`
- `long`: `42L`, `float`: `3.14f`, `double`: `3.14`(기본)

### 형변환(Casting)
- **묵시적(확대, widening):** `byte → short → int → long → float → double`
- **명시적(축소, narrowing):** 반대 방향은 `(타입)` 캐스팅 필요, **값 손실** 위험

```java
int i = 300;
byte b = (byte) i; // 오버플로우로 값 손실 가능
double d = i;      // OK: widening
```

### 오버플로우 & 실수 오차
```java
int x = Integer.MAX_VALUE;
System.out.println(x + 1); // -2147483648 (오버플로우)

double y = 0.1 + 0.2;
System.out.println(y);     // 0.30000000000000004 (이진 부동소수 표현 오차)
```
> 화폐/정밀 계산: **`BigDecimal`** 권장

---

## 3) 참조 자료형 핵심

- **저장 내용:** 객체의 **주소(참조)**
- **비교:**
    - `==` : 같은 객체(동일 주소)인가?
    - `equals()` : 내용(값)이 같은가?
- **`null` 주의:** `null`에 메서드 호출 시 **NullPointerException**

```java
String s1 = new String("Hi");
String s2 = new String("Hi");
System.out.println(s1 == s2);      // false (주소 다름)
System.out.println(s1.equals(s2)); // true  (내용 같음)
```

### 불변(immutable) vs 가변(mutable)
- **`String`은 불변** → 잦은 연결은 **`StringBuilder`** 사용
- **`java.util.Date`는 가변** → 최신 코드는 **`java.time` (예: `LocalDate`, `LocalDateTime`)** 권장 (불변 설계)

### 배열도 참조형
```java
int[] nums = {1, 2, 3};
int[] other = nums;
other[0] = 99;
System.out.println(nums[0]); // 99 (같은 배열 객체를 가리킴)
```

### 업캐스팅/다운캐스팅
```java
Object obj = "Hello";       // 업캐스팅
if (obj instanceof String s) {
  System.out.println(s.toUpperCase()); // 안전한 다운캐스팅
}
```

---

## 4) 자바는 항상 “값에 의한 호출(pass-by-value)”

- **기본형:** 값 자체가 복사 → 메서드 내부 변경이 **원본에 영향 없음**
- **참조형:** “참조(주소)값”이 복사 → **같은 객체를 가리키므로** 객체 **내용 변경**은 원본에 영향 있음

```java
class Box { int n; }

static void addTen(int x) { x += 10; }   // 기본형
static void addTen(Box b) { b.n += 10; } // 참조형(객체 상태 변경)

public static void main(String[] args) {
    int a = 5; addTen(a);
    System.out.println(a); // 5

    Box box = new Box(); box.n = 5; addTen(box);
    System.out.println(box.n); // 15
}
```

---

## 5) 오토박싱/언박싱 & 래퍼 타입

- 기본형 ↔ 래퍼(`int ↔ Integer`) 간 **자동 변환**
- 제네릭 컬렉션에는 **기본형을 직접 담을 수 없음** → 래퍼 타입 사용
- **주의:** `null` 언박싱은 NPE 발생, 성능 비용 존재

```java
List<Integer> list = new ArrayList<>(); // int 불가, Integer 사용
Integer x = null;
// int y = x; // 언박싱 시 NPE
```

- **래퍼 비교 주의:** `Integer` 끼리 `==`는 주소 비교 → **항상 `equals()` 사용**
```java
Integer a = 128, b = 128;
System.out.println(a == b);       // false (캐시 범위 바깥)
System.out.println(a.equals(b));  // true
```

---

## 6) 실무에서의 선택 가이드
- **루프/카운트/플래그/연산 중심:** **기본형**
- **없음(null) 상태 필요 / 제네릭 컬렉션 사용:** **래퍼/참조형**
- **문자열:** `String`(불변), 대량 연결은 `StringBuilder`
- **날짜/시간:** `LocalDate`, `LocalDateTime`, `ZonedDateTime`
- **돈/정밀:** `BigDecimal`
- **대용량 데이터 모델:** 불변(immutable) + 빌더 패턴 고려(스레드 안정성/예측 가능성)

---

## 7) 빈출 함정 10가지 체크리스트
1. 지역변수 **미초기화** 사용(컴파일 에러) vs 필드 기본값 자동 부여
2. 참조형 `==`로 내용 비교(오답) → `equals()` 사용
3. `String` 반복 연결로 **성능 저하** → `StringBuilder`
4. `Integer` 비교를 `==`로 (오답) → `equals()`
5. 오버플로우/언더플로우 간과
6. 부동소수 오차 간과(금융 도메인에서 `double` 사용)
7. 오토박싱 과다로 성능 저하 & `null` 언박싱 NPE
8. 배열/컬렉션을 메서드에 넘기고 내부에서 수정 → **외부 영향** 발생
9. `Date/Calendar` 남용 → `java.time` 사용
10. 다운캐스팅 시 `instanceof` 미검사로 `ClassCastException`

---

## 8) 미니 연습문제

### Q1: 기본형 vs 참조형 전달
- 아래 코드에서 `a`와 `box.n`의 최종 출력값을 예측해보세요.
```java
class Box { int n; }
static void addTen(int x) { x += 10; }
static void addTen(Box b) { b.n += 10; }

public static void main(String[] args) {
    int a = 5;
    Box box = new Box(); box.n = 5;
    addTen(a);
    addTen(box);
    System.out.println(a);
    System.out.println(box.n);
}
```

### Q2: `==` vs `equals()`
- `String s1 = "Hi"; String s2 = new String("Hi");`
    - `s1 == s2` 와 `s1.equals(s2)` 는 각각 무엇을 의미할까요?

---

## 9) 부록: 치트시트

### 최소/최대값 상수
- `Byte.MIN_VALUE`, `Short.MIN_VALUE`, `Integer.MIN_VALUE`, `Long.MIN_VALUE`, `Float.MIN_VALUE`, `Double.MIN_VALUE`, `Character.MIN_VALUE`
- `Byte.MAX_VALUE`, `Short.MAX_VALUE`, `Integer.MAX_VALUE`, `Long.MAX_VALUE`, `Float.MAX_VALUE`, `Double.MAX_VALUE`, `Character.MAX_VALUE`

### 유용한 팁
- 정수 리터럴 기본은 `int`, 실수 리터럴 기본은 `double`
- **형변환 우선순위:** 표현 범위가 넓은 타입으로 **자동 승격**되며, 피연산자 중 하나가 `double`이면 결과는 `double`
- **switch** 가능한 타입(JDK 17 기준): `byte, short, char, int, String, enum` 등

---

## 10) 요약 멘트
- **기본형 = 값 자체, 고정 크기, 빠름**
- **참조형 = 객체 주소, `equals()`로 내용 비교, `null` 주의**
- **문자열 불변 / 날짜-시간은 `java.time` / 돈은 `BigDecimal`**

필요하시면 **예제 프로젝트 스캐폴드**(소스+테스트)도 만들어 드릴게요.
