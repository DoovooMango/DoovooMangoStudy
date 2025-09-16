package src.localVariableTypeInference;

public class TypeInference {        // 지역 변수 자료형 추론 (Local Variable Type Inference)

    public static void main(String[] args) {

        var i = 10;                 // int i = 10;              (정수형)
        var j = 10.0;               // double j = 10.0;         (실수형)
        var str = "hello";          // String str = "hello";    (문자열)

        System.out.println(i);
        System.out.println(j);
        System.out.println(str);

        str = "test";       // 다른 문자열은 대입 가능하다.
        //str = 3;
        //한 번 선언한 자료형 변수를 다른 자료형으로 사용할 수 없다.
        //str 변수는 String 으로 선언되어서 다시 정수값을 넣을 수 없다.

        // 지역변수란?
        // 지역변수는 프로그램의 {  } 내에서 사용할 수 있는 변수
    }
}
