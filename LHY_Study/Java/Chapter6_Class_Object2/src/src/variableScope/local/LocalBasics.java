package src.variableScope.local;

class LocalBasics {
    static void greet(String name) {
        // 지역변수: method 내부에서 선언
        String message = "Hello, " + name; // 반드시 초기화 후 사용
        System.out.println(message);
    }
    public static void main(String[] args) {
        greet("Java");
        // System.out.println(message); // 컴파일 오류: 지역변수는 블록 밖에서 사용 불가
    }
}
