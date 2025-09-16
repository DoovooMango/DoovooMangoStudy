package src.operator;

public class OperatorEx4 {                          // 조건 연산자를 사용하여 부모님의 나이 비교하기
    public static void main(String[] args) {
        int fatherAge = 45;
        int motherAge = 47;

        char ch;
        ch = (fatherAge > motherAge) ? 'T': 'F';
        /// 연산자
        // 조건식 ? 결과 1 : 결과 2;

        ///  기능
        // 조건식이 참이면 결과 1, 조건식이 거짓이면 결과 2가 선택

        ///  연산 예
        // int num = (5 > 3) ? 10 : 20;
        // 결과는 5가 3보다 크므로 조건식은 참, 따라서 num의 값은 10이다.

        System.out.println(ch);
    }
}
