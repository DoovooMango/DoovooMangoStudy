package src.character;

public class Character3 {                       // 문자형 연습 - 음수 오류 확인

    public static void main(String[] args) {

        int a = 65;
        int b = -65;

        char a2 = 65;
        // char b2 = -65; //문자형에는 음수값이 대입되면 오류
        // char 는 문자 자료형이지만 다른 자료형과 같이 컴퓨터 내부에서는 정수값으로 표현되므로 정수 자료형으로 분류되기도 한다.

        System.out.println((char)a);
        System.out.println((char)b);
        System.out.println((char)a2);
    }
}
