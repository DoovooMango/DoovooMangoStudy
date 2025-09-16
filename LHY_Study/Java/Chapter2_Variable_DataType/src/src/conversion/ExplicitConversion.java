package src.conversion;

public class ExplicitConversion {           /// 명시적 형 변환 (강제 형 변환)

    public static void main(String[] args) {

        ///  바이트 크기가 큰 자료형에서 작은 자료형으로 대입하는 경우
        int iNum1 = 10;
        byte bNum1 = (byte)iNum1;       // 강제로 형을 바꾸려면 바꿀 형을 괄호 안에 명시해야 함

        System.out.println(bNum1);
        // byte 형은 1바이트 로 int 형보다 크기가 작기 때문에 자료 손실이 발생할 수 있다.
        // 이 경우에는 대입된 값이 10을 1바이트 안에 표현할 수 있으므로 자료 손실이 발생하지 않는다.

        int iNum2 = 1000;
        byte bNum2 = (byte)iNum2;

        System.out.println(bNum2);       // 값 : -24
        // 그러나 이 경우에는 값이 1000이 byte 형 범위 (-128 ~ 127) 을 넘기 때문에 자료 손실이 발생한다.
        // 그래서 값은 -24로 출력된다.

        ///  더 정밀한 자료형에서 덜 정밀한 자료형으로 대입하는 경우
        double dNum3 = 3.14;
        int iNum3 = (int)dNum3;         // 실수 자료형 double을 정수 자료형 int 로 형 변환

        System.out.println(dNum3);      // 3.14
        System.out.println(iNum3);      // 3        실수의 소수점 이하 부분이 생략되고 정수 부분만 대입된다

        /// 연산 중 형 변환되는 경우
        double dNum1 = 1.2;
        float fNum2 = 0.9F;

        int iNum4 = (int)dNum1 + (int)fNum2;        // 두 실수가 각각 명시적으로 형 변환되어 더해짐
                                                    // 1과 0으로 변환된 두 값을 합하여 결과값이 1이 된다.
        int iNum5 = (int)(dNum1 + fNum2);           // 두 실수의 합이 먼저 계산되고 형 변환됨
                                                    // dNum1 + fNum2의 합을 먼저 계산한다.
                                                    // 이 때 두 실수의 자료형이 다르지만 float 형이 double형으로 변환되는 묵시적 형 변환이 일어나면서
                                                    // 두 수가 더해져 결과값이 2.1 이 되고,
                                                    // 이 후에 int 형으로 명시적 형 변환이 되어 결과값이 2가 된다.
        System.out.println(iNum4);          // 1
        System.out.println(iNum5);          // 2
    }
}
