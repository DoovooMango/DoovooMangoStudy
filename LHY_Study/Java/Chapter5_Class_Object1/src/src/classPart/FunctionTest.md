# FunctionTest: `main` vs `addNum` 함수 비교

아래 예제를 기준으로 **프로그램의 시작점인 `main`** 과 **연산만 담당하는 `addNum`**의 차이를 정리했습니다.

```java
public class FunctionTest {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;

        int sum = addNum(num1, num2);  // addNum() 함수 호출
        System.out.println(num1 + " + " + num2 + " = " + sum + "입니다.");
    }

    // addNum() 함수 구현
    public static int addNum(int n1, int n2){
        int result = n1 + n2;
        return result;
    }
}
```

---

## 1) 실행 흐름(한눈에)
1. `main`에서 `num1=10`, `num2=20`을 만든다.
2. `main`이 `addNum(num1, num2)`를 **호출**한다. (값이 복사되어 전달)
3. `addNum`은 전달받은 `n1`, `n2`를 더해 `result`를 **반환**한다.
4. `main`은 반환값을 `sum`에 담아 **출력**한다.

---

## 2) 역할 차이
- **`main(String[] args)`**
    - JVM이 가장 먼저 호출하는 **프로그램의 시작점(엔트리 포인트)**.
    - 전체 실행 흐름을 조립·지휘하고 **출력/부수효과**를 담당.
    - 반환형이 `void` (아무 값도 돌려주지 않음).

- **`addNum(int n1, int n2)`**
    - **연산(덧셈)**만 담당하는 **유틸리티 함수**.
    - 입력 → 계산 → **결과(정수)** 반환.
    - 반환형이 `int`.

---

## 3) 시그니처/매개변수
- `main(String[] args)`: 명령행 인자를 받는 특별한 시그니처(형식 고정).
- `addNum(int n1, int n2)`: 비즈니스 로직에 맞춰 자유롭게 설계 가능(원하면 오버로딩 가능).

둘 다 예제에선 `static`이므로 **객체 생성 없이 호출** 가능.  
만약 `addNum`을 `static`이 아니게 바꾸면, 아래처럼 **인스턴스**가 필요합니다.

```java
FunctionTest t = new FunctionTest();
int sum = t.addNum(num1, num2);
```

---

## 4) 호출 관계
- `main` = **호출자(caller)**
- `addNum` = **피호출자(callee)**
- `addNum`의 반환값이 `main`의 지역변수 `sum`에 저장됩니다.

---

## 5) 자바는 “값 전달(pass-by-value)”
자바는 **항상 값으로 전달**합니다. 즉, 매개변수는 **복사본**입니다.

```java
public static int addNum(int n1, int n2) {
    n1 = 999;           // 복사본 n1만 바뀜
    return n1 + n2;     // 999 + 20 = 1019
}
```
위처럼 수정해도 `main`의 `num1`은 **여전히 10**입니다.  
따라서 화면에는 `10 + 20 = 1019입니다.` 처럼 보일 수 있는데,  
왼쪽의 `10 + 20`은 **원본 값(출력 시점의 num1, num2)**, 오른쪽 결과는 **함수 반환값**입니다.

> ※ 참조형(객체)도 **참조값 자체를 값으로 복사**해서 전달합니다. 복사된 참조를 통해 **객체 내부 상태**는 바뀔 수 있지만, **참조 변수 자체**를 다른 객체로 바꿔도 호출자 쪽 변수는 바뀌지 않습니다.

---

## 6) 단위 테스트/확장 팁
- `addNum`처럼 **입력 → 출력이 명확한 순수 함수**는 테스트하기 쉽습니다.
- 출력 책임은 `main`(또는 상위 레이어)이 맡고, 연산 책임은 **작은 함수들**에 분리하면 유지보수성↑.
- 필요 시 아래처럼 **오버로딩** 또는 **가변인자**로 확장할 수 있습니다.

```java
public static int addNum(int a, int b)         { return a + b; }
public static int addNum(int a, int b, int c)  { return a + b + c; }

public static int addAll(int... nums) {
    int sum = 0;
    for (int n : nums) sum += n;
    return sum;
}
```

---

## 7) 요약
- **`main`**: 시작점, 흐름 제어, 출력 담당, `void` 반환.
- **`addNum`**: 계산 전담, `int` 반환, 재사용·테스트 용이.
- **전달 방식**: 자바는 **값 전달** — 매개변수는 복사본.
- **설계 권장**: I/O/흐름과 계산 로직을 **분리**하라.
