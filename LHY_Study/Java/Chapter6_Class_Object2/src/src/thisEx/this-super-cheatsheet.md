# Java `this` / `this(...)` / `super` / `super(...)` 비교 치트시트

> 핵심만 빠르게 확인할 수 있는 요약표 + 규칙 + 자주 틀리는 포인트 예시.

---

## 1) 한눈에 보기 (요약표)

| 표현 | 의미 | 사용 위치 | 대표 용도 | 주의사항 |
|---|---|---|---|---|
| `this` | **현재 객체(인스턴스) 참조** | 인스턴스 메서드/생성자 | 필드/매개변수 구분, 자기 자신 전달, 체이닝 `return this` | `static`에서 사용 불가 |
| `super` | **부모 타입 관점의 현재 객체** | 인스턴스 메서드/생성자 | 부모 필드/메서드 접근/호출 | 오버라이드된 부모 구현 호출 시 `super.m()` |
| `this(...)` | **동일 클래스의 다른 생성자 호출** | **생성자 첫 줄** | 생성자 로직 재사용(체이닝) | 첫 줄 필수, `super(...)`와 **동시 사용 불가** |
| `super(...)` | **부모 클래스의 생성자 호출** | **생성자 첫 줄** | 부모 초기화 필요 시 | 첫 줄 필수, `this(...)`와 **동시 사용 불가** |

---

## 2) 필수 규칙

1. **생성자 첫 줄 규칙**
    - `this(...)` **또는** `super(...)` 중 **하나만** 가능. (둘 다 X)
    - 아무 것도 안 쓰면 컴파일러가 **암묵적으로 `super();`** 삽입.
2. **정적 컨텍스트 금지**
    - `static` 메서드/블록에서는 **`this`/`super` 사용 불가**.
3. **오버라이드·숨김과의 관계**
    - `this.m()`은 **런타임 실제 타입**의 `m()` 호출(동적 디스패치).
    - `super.m()`은 **부모 구현**을 명시적으로 호출.
    - 필드 숨김 시 `this.x`는 현재 클래스의 `x`, `super.x`는 부모의 `x`.
4. **생성자 체이닝**
    - 중복 초기화를 줄이려면: 간단 생성자 → **`this(...)`** → 최종 생성자에서 필드 할당.
    - 상속 초기화는 **`super(...)`**로 부모부터.

---

## 3) 빠른 예제 모음

### 3-1. `this` — 필드/매개변수 구분 + 체이닝
```java
class User {
    private String name;
    User setName(String name) { this.name = name; return this; } // 체이닝
}
```

### 3-2. `super` — 부모 메서드/필드 접근
```java
class Parent {
    int v = 1;
    void hi() { System.out.println("parent"); }
}
class Child extends Parent {
    int v = 2;
    @Override void hi() {
        super.hi();             // 부모 구현 호출
        System.out.println(v);  // 2
        System.out.println(super.v); // 1
    }
}
```

### 3-3. `this(...)` — 동일 클래스 생성자 체이닝
```java
class Point {
    int x, y;
    Point(int x) { this(x, 0); }      // 다른 생성자 호출(첫 줄)
    Point(int x, int y) { this.x = x; this.y = y; }
}
```

### 3-4. `super(...)` — 부모 생성자 호출
```java
class Person { Person(String name) {} }
class Student extends Person {
    Student() {
        super("unknown"); // 부모 생성자 호출(첫 줄)
    }
}
```

### 3-5. 함께 정리 (둘 다는 불가 → 공통 초기화 메서드 추출)
```java
class A { A(int a) {} }
class B extends A {
    B() {
        super(0);     // OK
        // this();   // ❌ 생성자 첫 줄은 하나만
        init();
    }
    B(int x) {
        this();       // OK (동일 클래스 다른 생성자)
        // super(1); // ❌ 함께 사용 불가
    }
    private void init() { /* 공통 초기화 */ }
}
```

---

## 4) 내부 클래스·람다에서의 포인트

```java
class Outer {
    int o = 1;
    class Inner {
        int i = 2;
        void show() {
            System.out.println(this.i);       // 2 (Inner의 this)
            System.out.println(Outer.this.o); // 1 (바깥 Outer 인스턴스)
        }
    }
}
class Box {
    void run() {
        Runnable r1 = () -> System.out.println(this); // 람다: 바깥 this 캡처(Box)
        Runnable r2 = new Runnable() {
            public void run() { System.out.println(this); } // 익명객체의 this
        };
        r1.run(); r2.run();
    }
}
```

---

## 5) 자주 나는 컴파일 에러 & 원인

- **`Constructor call must be the first statement in a constructor`**  
  → `this(...)`/`super(...)`가 첫 줄이 아님.
- **`Cannot use this/super in a static context`**  
  → `static` 메서드/블록에서 `this`/`super` 사용 시도.
- **`Recursive constructor invocation`**  
  → `this(...)` 체이닝이 자기 자신을 다시 호출해 **무한 루프**.
- **`Implicit super constructor X() is undefined`**  
  → 부모에 기본 생성자가 없음. → **`super(args)`**를 명시해야 함.

---

## 6) 실전 체크리스트

- [ ] 필드/매개변수 이름이 같으면 `this.field = field;`
- [ ] 상속 초기화는 `super(...)`로, 동일 클래스 생성자 재사용은 `this(...)`로
- [ ] 생성자 첫 줄 규칙(`this(...)` XOR `super(...)`)
- [ ] 오버라이딩 내부에서 부모 동작 필요 시 `super.method()`
- [ ] `static`에서는 `this`/`super` 금지
- [ ] 생성자에서 `this`를 외부로 유출하지 말기(초기화 전 노출 위험)

---

## 7) 메서드 디스패치 감각 익히기

```java
class A { void who() { System.out.println("A"); } }
class B extends A { @Override void who() { System.out.println("B"); } }

A obj = new B();
obj.who(); // "B" — this는 실제 인스턴스(B)를 가리킴 → 동적 디스패치
```

---

## 8) 기억해 둘 문장

- **`this`는 현재 객체, `super`는 부모 관점의 현재 객체.**
- **`this(...)`와 `super(...)`는 “생성자 첫 줄”에 단 하나.**
- **오버라이딩된 부모 구현은 `super.m()`으로 호출.**
- **`static`에서 `this`/`super`는 없다.**

필요 시 이 치트시트를 프린트하거나 프로젝트 위키에 붙여두세요. 🚀
