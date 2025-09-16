# 자바 접근 제어자 & 정보 은닉(캡슐화) 가이드

## 1) 접근 제어자 한눈에 보기

자바의 **접근 제어자(Access Modifiers)** 는 **네 가지뿐**입니다.

| 대상 | `public` | `protected` | *(package‑private)* = “default” | `private` |
|---|---|---|---|---|
| **같은 클래스** | O | O | O | O |
| **같은 패키지의 다른 클래스** | O | O | O | X |
| **다른 패키지의 하위클래스** | O | O | X | X |
| **다른 패키지의 비하위클래스** | O | X | X | X |

### 설명
* public : 외부 클래스 어디에서나 접근할 수 있습니다.
* protected : 같은 패티지 내부와 상속 관계인 클래스에서만 접근할 수 있고 그 외 클래스에서는 접근할 수 없다.
* 아무것도 없음 : default 상태이며, 같은 패키지 내부에서만 접근할 수 있다.
* private : 같은 클래스 내부에서만 접근할 수 있다.

protected 
1. 같은 패키지 내부에 있는 클래스
2. 다ㅣ른 패키지에 있더라도 만약 해당 클래스를 상속받은 자식클래스에서 접근이 가능!
   주로 확장 관계에서 부모 클래스의 멤버를 자식 클래스가 접근할 수 있도록 허용 하는 경우!
3. 일반적으로 실무에서 생성자의 접근제어자를 protected

- **package‑private(일명 default)**: **키워드가 없습니다.** 아무 것도 안 쓰면 이 수준입니다.  
  (※ `default` 키워드는 `switch` 문이나 인터페이스의 `default 메서드`에서 쓰는 *다른 문법*이니 혼동 주의)
- **protected의 핵심**
    - 같은 패키지: 일반 멤버처럼 접근 가능
    - **다른 패키지**: **하위클래스 내부에서만**, 그마저도 **상속 경로**(`this`/`super`)로 접근해야 합니다.  
      예) `this.protectedField` ✅, `super.protectedMethod()` ✅, **`new Parent().protectedField` ❌**

### 톱레벨 클래스(파일 최상위 클래스)에 쓸 수 있는 접근 제어자
- **`public`** 또는 **package‑private**만 가능. (`private`/`protected`는 불가)

### 생성자에도 접근 제어자가 걸립니다
- `private` 생성자 → 외부 `new` 금지(싱글턴/정적 팩토리/빌더에서 활용)
- `protected` 생성자 → 같은 패키지 또는 하위클래스에서만 생성 가능

---

## 2) 예제로 이해하기 (동봉된 .java 파일)

패키지 구조 예시:

```
com.example.a
 ├─ Parent.java
 └─ Sibling.java
com.example.b
 ├─ Child.java       // Parent 상속
 └─ Stranger.java    // 상속 안 함
com.example.info
 ├─ Account.java     // 정보 은닉(검증 로직 포함)
 ├─ Student.java     // 불변(Immutable) + 방어적 복사
 ├─ PasswordEncoder.java
 ├─ Sha256PasswordEncoder.java
 └─ PasswordEncoders.java
com.example
 └─ DemoMain.java    // 간단 실행 예 (선택)
```

각 파일은 접근 범위 차이와 **정보 은닉(캡슐화)** 기법(검증, 불변, 인터페이스/팩토리)을 보여 줍니다.

---

## 3) 정보 은닉(Information Hiding, Encapsulation)

**객체의 내부 구현(데이터/로직)을 숨기고, 안정된 ‘공개 인터페이스’만 노출**하는 설계 원칙입니다.

### 왜 필요한가?
- **불변식(invariant) 유지**: 잘못된 값 유입 방지/검증 가능
- **교체 용이성**: 내부 구현을 바꿔도 외부 의존 영향 최소화
- **복잡성 감소**: 사용하는 쪽은 “무엇을”에 집중, “어떻게”는 감춤

### 자주 쓰는 기법
- 필드는 **`private`**, 동작은 **`public` 메서드**로 공개(검증 로직 포함)
- **불변 객체**(모든 필드 `private final`, setter 없음) 사용
- 가변 필드(컬렉션 등)에는 **방어적 복사** & **읽기 전용 뷰** 반환
- **인터페이스** + **팩토리**(또는 DI)로 구현체를 캡슐화
- 공개 API 최소화(“최소 공개 원칙”) & 내부 구현은 **package‑private**로 감추기

---

## 4) 자주 헷갈리는 포인트

- **protected**
    - 같은 패키지: 접근 가능
    - 다른 패키지: **하위클래스 내부에서만**, **상속 경로로만** 접근 가능  
      (`this.pro`/`super.pro` ✅, **`new Parent().pro` ❌**)
- **package‑private**: “키워드 없음”, **같은 패키지까지만** 접근 허용
- **톱레벨 클래스**: `public` 또는 package‑private만 허용
- **private 멤버는 상속 여부와 무관하게 외부에서 접근 불가**
- **생성자 접근 제어**로 인스턴스 생성 자체 제한 가능(싱글턴/팩토리 등)

---

## 5) 빠른 체크리스트

- 외부에서 건드리면 안 되는 데이터/로직은 **`private`** 로 감춰라.
- 패키지 내부만 쓰는 유틸/도우미는 **package‑private** 로.
- 다른 패키지까지 공개해야 한다면 신중히 **`public`**.
- 상속 확장을 고려할 때만 **`protected`**.
- 컬렉션 같은 가변 객체는 **방어적 복사** & **읽기 전용 반환**.
- 불변 클래스를 적극 활용하고, 생성 시 **검증**을 넣자.
- 구체 구현은 감추고, **인터페이스 + 팩토리/DI**로 의존을 역전시키자.