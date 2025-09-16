package src.operator;

public class OperatorEx3 {                                  // 논리 연산자
    public static void main(String[] args) {

        int num1 = 10;
        int i = 2;

                                                     // ( i = i + 2 ) < 10) : 논리 곱에서 앞 항의 결과값이 거짓이므로 이 문장은 실행되지 않음
        boolean value = ((num1 = num1 + 10 ) < 10) && ( ( i = i + 2 ) < 10);
        System.out.println(value);
        System.out.println(num1);
        System.out.println(i);               // 2 : 논리 곱에서 앞 항이 거짓이면 뒤 항이 실행되지 않아 i값이 그대로 출력!

                                             // ( i = i + 2 ) < 10) : 논리 합에서 앞 항의 결과값이 참이므로 이 문장은 실행되지 않음
        value = ((num1 = num1 + 10 ) > 10) || ( ( i = i + 2 ) < 10);
        System.out.println(value);
        System.out.println(num1);
        System.out.println(i);               // 2 : 논리 합에서 앞 항이 참이면 뒤 항이 실행되지 않아 i값이 그대로 출력!
    }
}
