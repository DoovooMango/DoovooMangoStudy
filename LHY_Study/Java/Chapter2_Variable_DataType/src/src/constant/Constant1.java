package src.constant;

public class Constant1 {

    public static void main(String[] args) {

        final double PI = 3.14;         // 상수의 대표 예제
        System.out.println(PI);

        final int MAX_NUM = 100;        // 선언과 동시에 초기화
        final int MIN_NUM;

        MIN_NUM = 0;                    // 초기화하지 않으면 오류가 발생하므로 사용하기 전 반드시 초기화해야 한다.

        System.out.println(MAX_NUM);
        System.out.println(MIN_NUM);

        //MAX_NUM = 1000;               // 상수는 값을 변경할 수 없으므로, 이 코드를 입력하면 오류가 발생한다.
    }
}
