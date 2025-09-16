package src.doubleEx;

public class Double1 {                          /// 실수형 변수 선언과 값 출력
    public static void main(String[] args) {

        double doubleNum = 3.14;
        float floatNum = 3.14F;
        // F 는 식별자
        // F 는 3.14가 double 형이 아니라 float 형 값이 대입된다는 의미이다. 따라서 숫자 뒤에 F나 f를 붙여줘야 한다.

        // 참고
        // long 자료형의 식별자 인 L과 l 도 이와 비슷하다.

        System.out.println(doubleNum);
        System.out.println(floatNum);
    }
}
