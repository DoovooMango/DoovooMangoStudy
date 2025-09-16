# `this` 완전 가이드 (Java)

> **한 줄 요약**  
> `this`는 “**지금 이 인스턴스 메서드를 실행 중인 그 객체 자신**”을 가리키는 **참조**입니다.  
> 메모리 주소 숫자 그 자체가 아니라 *객체를 가리키는 레퍼런스*라고 이해하세요.

---

## 1) `this`가 정확히 뭔가요?

- **의미**: 현재 실행 중인 **인스턴스 메서드의 수신 객체(receiver)** 에 대한 참조  
  (타입은 그 클래스 자신. 예: `class BirthDay` → 메서드 안의 `this` 타입은 `BirthDay`)
- **형태**: 보이지 않는(암묵적) 첫 번째 파라미터처럼 전달됩니다. `obj.doX()`를 부르면 내부적으로 `doX(obj, ...)`처럼 `obj`가 `this`가 됩니다.
- **주소?**: `this`는 실제 메모리 주소 숫자를 노출하지 않습니다. `System.out.println(this)` 시 보이는 `@16진수`는 **해시코드**를 16진수로 출력한 것일 뿐, 주소가 아닙니다.

```java
class BirthDay{
    int day;
    int month;
    int year;

    public void setYear(int year) {
        this.year = year; // 왼쪽은 필드, 오른쪽은 매개변수
    }

    public void printThis() {
        System.out.println(this); // 현재 객체 자신을 출력 (toString() 호출됨)
    }
}

public class ThisExample {
    public static void main(String[] args) {
        BirthDay bDay = new BirthDay();
        bDay.setYear(2000);
        System.out.println(bDay); // Object.toString() 기본: 클래스명@해시(16진수)
        bDay.printThis();         // ↑ 와 동일한 객체 출력
    }
}
```

### 사람이 읽기 좋은 출력으로 바꾸기 (toString 오버라이드)
```java
class BirthDay {
    int day, month, year;

    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "BirthDay{year=" + year + ", month=" + month + ", day=" + day +
               ", id=" + System.identityHashCode(this) + "}";
    }

    public void printThis() {
        System.out.println(this); // 결국 toString()을 호출함
    }
}
```

---

## 2) `this`를 쓰는 대표 상황 4가지 (확장 설명 + 예제)

### 2-1. 필드/매개변수 이름 충돌 시 구분
```java
class User {
    private String name;
    public void setName(String name) {
        this.name = name; // 필드 name ← 매개변수 name
    }
}
```
- `this.name`은 **필드**, `name`은 **매개변수**입니다.
- `this`를 빼고 `name = name;` 하면 매개변수에 매개변수를 대입하는 셈이라 **필드가 바뀌지 않음**.

---

### 2-2. 현재 객체를 다른 메서드/생성자/콜백에 전달
```java
class Register {
    static void add(Object o) { System.out.println("등록: " + o); }
}
class Task {
    void run() {
        Register.add(this); // 현재 객체 자신을 넘김
    }
}
```
- 이벤트 리스너/콜백 패턴에서 자주 사용:
```java
button.setOnClickListener(this); // (안드로이드/스윙 등) 현재 객체가 리스너 역할
```

> **주의(중요!)**: **생성자 안에서 `this`를 외부에 유출(leak)** 하지 마세요.  
> 아직 완전히 초기화되지 않은 상태의 `this`가 외부 스레드/콜백에서 사용되면 **NPE, 불변식 위반, 레이스 컨디션**이 생길 수 있습니다.

```java
class Bad {
    Bad(Observer obs) {
        obs.register(this); // ❌ 생성자에서 this 유출 위험
        heavyInit();        // 아직 초기화 끝나기 전 this가 노출됨
    }
}
```

---

### 2-3. 메서드 체이닝 (Fluent API) – `return this`
```java
class QueryBuilder {
    private String table;
    private String where;

    public QueryBuilder table(String t) { this.table = t; return this; }
    public QueryBuilder where(String w) { this.where = w; return this; }

    public String build() { return "SELECT * FROM " + table + " WHERE " + where; }
}

String sql = new QueryBuilder().table("users").where("age > 20").build();
```
- 빌더 패턴/플루언트 API에서 관용적으로 사용됩니다.

---

### 2-4. 생성자 체이닝 `this(...)`
```java
class BirthDay {
    int year, month, day;
    BirthDay(int year) {
        this(year, 1, 1); // 동일 클래스의 다른 생성자 호출
    }
    BirthDay(int year, int month, int day) {
        this.year = year; this.month = month; this.day = day;
    }
}
```
- **규칙**
    - `this(...)`는 **생성자 첫 줄**에만 호출 가능
    - **`super(...)`와 동시에 쓸 수 없음** (첫 줄 자리는 하나뿐)
    - `this` (현재 객체 참조)와 **문법상 완전히 다름** — 헷갈리지 말 것!

---

## 3) `this`가 **안 되는** 곳 (금지/제약)

- `static` 메서드/블록에서는 인스턴스가 없으므로 `this` 사용 불가
```java
class Util {
    static void x() {
        // System.out.println(this); // 컴파일 에러
    }
}
```

- **정적 컨텍스트에서 인스턴스가 필요**하면, 그 인스턴스를 **파라미터로 받거나 생성**해야 합니다.

---

## 4) `this` vs `super` vs `this(...)` vs `super(...)`

| 표현 | 의미 | 어디서 사용 | 제약 |
|---|---|---|---|
| `this` | 현재 객체 참조 | 인스턴스 메서드 | static 불가 |
| `super` | 부모 타입 관점 참조 | 인스턴스 메서드 | 부모 멤버 접근 시 사용 |
| `this(...)` | **동일 클래스 다른 생성자 호출** | 생성자 첫 줄 | `super(...)`와 동시에 사용 불가 |
| `super(...)` | **부모 생성자 호출** | 생성자 첫 줄 | `this(...)`와 동시에 사용 불가 |

```java
class Parent { Parent(int x) {} }
class Child extends Parent {
    Child() {
        super(0);  // 부모 생성자 호출 (첫 줄)
        // this(); // ❌ 함께 사용 불가
    }
}
```

---

## 5) 내부 클래스·람다에서의 `this`

- **멤버 내부 클래스**에서 `this`는 내부 클래스 인스턴스를 가리킵니다.  
  바깥 인스턴스를 가리키려면 `OuterClass.this` 사용.
```java
class Outer {
    int o = 1;
    class Inner {
        int i = 2;
        void f() {
            System.out.println(this.i);        // 2 (Inner의 this)
            System.out.println(Outer.this.o);  // 1 (바깥 Outer 인스턴스)
        }
    }
}
```

- **람다식**은 고유한 `this`를 갖지 않고 **바깥의 `this`를 캡처**합니다. (익명 클래스와의 차이점)
```java
class Box {
    void run() {
        Runnable r1 = () -> System.out.println(this); // Box의 this
        Runnable r2 = new Runnable() {
            public void run() { System.out.println(this); } // 익명 객체의 this
        };
        r1.run();
        r2.run();
    }
}
```

---

## 6) 동적 디스패치와 `this`

오버라이딩된 메서드를 호출하면 **런타임 실제 타입**의 메서드가 실행됩니다.  
`this`는 항상 **실제 인스턴스**를 가리키므로, 같은 코드라도 다형성에 따라 동작이 달라질 수 있습니다.

```java
class A { void who() { System.out.println("A"); } }
class B extends A { void who() { System.out.println("B"); } }
void test(A a) { a.who(); } // a가 가리키는 실제 객체에 따라 A/B 출력
```

---

## 7) 흔한 오해·함정 체크리스트

- [ ] `this`는 **주소 숫자**가 아니다 → **참조**다. 출력의 `@16진수`는 해시코드 표현일 뿐.
- [ ] `this`와 `this(...)`는 전혀 다른 것 (참조 vs 생성자 호출)
- [ ] 생성자에서 `this`를 외부로 유출하지 말기 (미완성 상태 노출)
- [ ] static 컨텍스트에서는 `this` 사용 불가
- [ ] 빌더/체이닝에서는 `return this`가 편리하지만, **불변(immutable) 설계**를 원한다면 새 객체를 반환하는 방식이 더 안전할 수 있음

---

## 8) 실습 문제

### Q1. 출력 결과 예측
```java
class X {
    int v;
    X set(int v) { this.v = v; return this; }
    void print() { System.out.println(this + " v=" + v); }
}
public class Main {
    public static void main(String[] args) {
        X a = new X().set(10);
        System.out.println(a);
        a.print();
    }
}
```
- `System.out.println(a);`와 `a.print();`의 첫 부분(`this`)이 **같은 객체**를 출력함을 확인하세요.

### Q2. 생성자 체이닝 규칙
```java
class Y {
    Y() {
        // super(); // 컴파일러가 자동 삽입
        init();
    }
    Y(int n) {
        this(); // 다른 생성자 호출 → 항상 첫 줄
        // super(); // ❌ 함께 쓸 수 없음
        this.init();
    }
    void init() {}
}
```
- 왜 `this()`와 `super()`는 함께 쓸 수 없을까요? (첫 줄은 단 하나만 허용)

### Q3. 내부 클래스와 바깥 `this`
```java
class Outer {
    int v = 1;
    class Inner {
        int v = 2;
        void show() {
            System.out.println(v);            // ?
            System.out.println(this.v);       // ?
            System.out.println(Outer.this.v); // ?
        }
    }
}
```
- 각 줄의 출력값을 적어 보세요.

---

## 9) 요약 치트시트

- `this`: 인스턴스 메서드 안에서 **현재 객체**
- **주요 용도 4가지**: (1) 필드/파라미터 구분 (2) 콜백/등록에 자기 자신 전달 (3) 체이닝 `return this` (4) 생성자 체이닝 `this(...)`
- **주의**: static 금지, 생성자에서 this 유출 금지, `this` vs `this(...)` 구분
- **심화**: 내부 클래스 `Outer.this`, 람다의 `this`는 바깥을 캡처, 다형성과 `this`

행운을 빕니다! `this`만 확실히 잡아도 객체지향 코드가 훨씬 선명해집니다. ✨
