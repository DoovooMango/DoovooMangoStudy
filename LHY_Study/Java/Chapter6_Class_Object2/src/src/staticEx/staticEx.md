# staticEx.md
데이터 영역(= **메서드 영역 / 클래스 영역**), **스택**, **힙**에서 *무엇이 언제 만들어지고, 어디에 저장되며, 어떻게 공유되는지*를 `staticEx.staticVariable.Student` 예제로 단계별로 설명합니다.

---

## 1) JVM 메모리 구역 빠르게 보기
- **데이터(메서드) 영역**: 클래스 메타데이터, `static` 변수, 상수, 메서드/필드 테이블, 바이트코드가 한 번 적재되어 **모든 인스턴스가 공유**.
- **힙(Heap)**: `new` 로 만든 **인스턴스(객체)의 필드 값**이 저장. GC 대상.
- **스택(Stack)**: 메서드 호출마다 생기는 **스택 프레임**에 지역변수/매개변수/반환주소가 저장. **스레드별로 분리**되어 있고, 메서드가 끝나면 프레임도 제거.

> 용어 매핑: “데이터 영역” = JVM의 **메서드 영역(Method Area)** = 흔히 “클래스 영역”이라고도 부릅니다.

---

## 2) 클래스 로딩과 `static` 초기화 순서
JVM은 클래스를 처음 “적극적으로” 사용할 때 다음 순서로 처리합니다.

1. **로딩(Loading)**: 클래스 파일을 읽어 메타데이터를 메서드 영역에 적재.
2. **링킹(Linking) – 준비(Preparation)**: `static` 필드에 **자료형의 기본값(0/false/null)**으로 공간만 먼저 확보.
3. **링킹 – 검증/해결(Verification/Resolution)**: 바이트코드 무결성/심볼 결합.
4. **초기화(Initialization)**: `<clinit>`(정적 초기화) 실행 → **코드에 적은 초기값**으로 `static` 필드 값을 설정.

예) `public static int serialNum = 1000;`
- 준비 단계: `serialNum`에 **0** 저장
- 초기화 단계: **1000**으로 대입

> 이 과정은 **클래스당 한 번**만 일어납니다. 그래서 `static`은 “공유되는 한 벌”만 존재합니다.

---

## 3) 예제 코드에서 일어나는 일: 줄 단위 타임라인
`StudentTest.main`을 기준으로 메모리 변화를 추적해 봅니다.

```java
public static void main(String[] args) {

    Student studentLee = new Student();
    studentLee.setStudentName("이지원");

    System.out.println(studentLee.serialNum);
    studentLee.serialNum++;

    Student studentSon = new Student();
    studentSon.setStudentName("손수경");

    System.out.println(studentSon.serialNum);
    System.out.println(studentLee.serialNum);
}
```

### (A) `main` 진입
- **스택**: `main`의 스택 프레임 생성, 지역변수 슬롯 준비.

### (B) `new Student()` 를 **처음** 만나는 순간
- 아직 `Student` 클래스가 적재되지 않았다면 JVM이 **Student 클래스 로딩** 수행
    - **메서드 영역(데이터 영역)**: `Student`의 메타데이터 적재
    - `static int serialNum;` 준비단계에서 **0**으로 메모리 확보 → 초기화단계에서 **1000**으로 설정
- 이어서 **힙**에 `Student` 인스턴스 #1(= `studentLee`) 메모리 블록 할당
    - 인스턴스 필드(`studentID`, `studentName`, `grade`, `address`)는 **기본값(0/null)**으로 채워짐
- **스택**: 지역변수 `studentLee`에는 **힙 주소(참조값)** 저장

### (C) `studentLee.setStudentName("이지원");`
- **스택**: 매개변수 `"이지원"`과 `this`(= `studentLee` 참조)가 프레임에 올라왔다가 호출 종료 시 제거
- **힙**: `studentLee.studentName` 필드가 `"이지원"`으로 변경

### (D) `System.out.println(studentLee.serialNum);`
- 문법상 인스턴스를 통해 접근했지만 **실제로는 `Student.serialNum`**을 읽습니다.
- **메서드 영역**의 `serialNum` 현재값 **1000**이 출력됩니다.

### (E) `studentLee.serialNum++;`
- 역시 **`Student.serialNum`**을 증가시키는 것입니다.
- **메서드 영역**: `serialNum` 값이 **1001**이 됨 → **모든 인스턴스에 공유**.

### (F) 두 번째 `new Student()`
- **힙**: `Student` 인스턴스 #2(= `studentSon`) 생성, 필드 기본값으로 초기화 → `setStudentName`로 이름 설정
- **메서드 영역**: 이미 로딩됨. `serialNum`은 **1001** 상태 유지

### (G) 마지막 두 줄 출력
- `studentSon.serialNum` → **1001**
- `studentLee.serialNum` → **1001**
- 두 인스턴스가 **같은 `static` 값을 공유**하므로 동일하게 보입니다.

**따라서 예상 출력**
```
1000
1001
1001
```

---

## 4) `static` 메서드가 인스턴스 필드를 못 건드리는 이유
```java
public static int getSerialNum() {
    int i = 10;        // OK: 지역변수는 스택에
    // studentName = "aaa";  // 컴파일 오류: 인스턴스 맥락(this)이 없음
    return serialNum;  // OK: static 필드는 클래스 단 하나
}
```
- `static` 메서드는 **클래스에 속**하며, 특정 인스턴스를 가리키는 `this`가 없습니다.
- 따라서 **힙의 특정 객체 필드**(예: `studentName`)에는 직접 접근할 수 없습니다.
- 대신 **메서드 영역의 `static` 필드**에는 접근 가능합니다.

---

## 5) 흔한 질문 Q&A
### Q1. 왜 `studentLee.serialNum++`처럼 인스턴스로 접근해도 되나요?
가능은 하지만 **권장하지 않습니다**. 의미를 명확히 하려면 `Student.serialNum++`로 쓰세요.

### Q2. `static` 필드는 GC 대상인가요?
클래스가 **언로드(unload)** 되기 전까지 메서드 영역에 남습니다. 일반적으로 애플리케이션 종료까지 유지됩니다.

### Q3. 멀티스레드에서 `serialNum++`는 안전한가요?
아닙니다. `++`는 읽기→연산→쓰기의 **복합 동작**이므로 경쟁조건(race)이 생깁니다.  
원자성 보장이 필요하면 `AtomicInteger`나 동기화(synchronized)를 사용하세요.

```java
public class Student {
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    public final int studentID = SEQ.incrementAndGet();
    // ...
}
```

### Q4. 인스턴스 필드는 어디에 저장되나요?
**힙**에 저장되고, 지역변수/매개변수/반환값 같은 **일시적인 값**은 **스택**에 저장됩니다.

---

## 6) 핵심 요약(한 장 그림)
```
[프로그램 시작]
   │
   ├─ main() 호출 → [스택] main 프레임 생성
   │
   ├─ 처음 만난 Student 사용 → [메서드(데이터)영역]
   │      로딩/링킹/초기화 수행
   │      serialNum = 1000
   │
   ├─ new Student() → [힙] 객체 #1 생성 → setName("이지원")
   │
   ├─ println(Student.serialNum)  → 1000
   │
   ├─ Student.serialNum++         → 1001
   │
   ├─ new Student() → [힙] 객체 #2 생성 → setName("손수경")
   │
   └─ println(Student.serialNum)  → 1001
      println(Student.serialNum)  → 1001
```

---

## 7) 베스트 프랙티스
- `static` 멤버는 **클래스명으로 접근**해 의도를 분명히 하기.
- 공유 상태를 변경할 때는 **스레드 안전성** 고려.
- `static`은 “전역”처럼 퍼지기 쉬우므로 **남용 금지**(테스트/유지보수 어려움).
- 자동 증가 학번처럼 **공용 시퀀스**에는 `AtomicInteger` 등 안전한 도구 사용 권장.
