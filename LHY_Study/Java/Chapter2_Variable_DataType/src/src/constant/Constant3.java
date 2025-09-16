package src.constant;

public class Constant3 {

    // 컴파일 타임 상수(리터럴로 초기화된 final 상수)
    public static final int MAX_STUDENT_NUM = 35;

    public static void main(String[] args) {
        // 1) 숫자 리터럴 (정수/실수)
        int dec = 30;                 // 10진수
        int hex = 0xFF;               // 16진수(255)
        int bin = 0b1010;             // 2진수(10)
        int million = 1_000_000;      // 가독성을 위한 언더스코어
        long big = 10_000_000_000L;   // long은 접미사 L
        double pi = 3.14159;          // 기본 실수는 double
        double exp = 1.23e3;          // 지수 표기(= 1230.0)
        float f = 3.14f;              // float은 접미사 f/F

        // 2) 문자 리터럴 (char)
        char letter = 'A';
        char newline = '\n';          // 이스케이프 문자
        char korean = '\uAC00';       // 유니코드(‘가’)

        // 3) 불리언 리터럴
        boolean ok = true;

        // 4) 문자열 리터럴 (String)
        String hi = "Hello";
        String textBlock = """
                멀티라인
                문자열입니다.
                """;                    // JDK 15+: 텍스트 블록(여러 줄 문자열)

        // 5) null 리터럴 (참조 타입에만 사용 가능)
        String none = null;

        // 6) 배열 리터럴
        int[] scores = { 10, 20, 30 };

        // 7) 문자열 상수 풀(intern) 동작 확인
        String a = "hi";
        String b = "hi";                                              // 같은 리터럴 → 같은 interned 객체 참조
        String c = new String("hi");                           // 항상 새 객체 생성(풀과 별개)
        System.out.println("a == b : " + (a == b));                   // true (동일 참조)
        System.out.println("a == c : " + (a == c));                   // false (서로 다른 객체)
        System.out.println("a.equals(c) : " + a.equals(c));           // true (내용 같음)
        System.out.println("c.intern() == a : " + (c.intern() == a)); // true (풀에 넣으면 같아짐)

        // 출력으로 값 확인
        System.out.println("dec=" + dec + ", hex=" + hex + ", bin=" + bin + ", million=" + million);
        System.out.println("big=" + big);
        System.out.println("pi=" + pi + ", exp=" + exp + ", f=" + f);
        System.out.println("letter=" + letter + ", korean=" + korean + ", newlineCode=" + (int) newline);
        System.out.println("ok=" + ok);
        System.out.println("hi=" + hi);
        System.out.println("textBlock:\n" + textBlock);
        System.out.println("none is null? " + (none == null));
        System.out.println("scores[0]=" + scores[0] + ", len=" + scores.length);

        // 컴파일 타임 상수 사용(인라인될 수 있음)
        int cap = MAX_STUDENT_NUM;
        System.out.println("MAX_STUDENT_NUM=" + cap);
    }
}
