package src.character;

public class Character1 {                       /// 문자형 연습

    public static void main(String[] args) {

        char char1 = 'A';
        System.out.println(char1);              // 문자 출력
        System.out.println((int) char1);        // 문자에 해당하는 정수값 출력

        char char2 = 24;                        // 정수값 대입
        System.out.println(char2);              // 정수값에 해당하는 문자 출력

        int int1 = 25;                          // 정수값 대입
        System.out.println(int1);               // 문자 정수값 출력
        System.out.println((char) int1);        // 정수값에 해당하는 문자 출력

    }
}
