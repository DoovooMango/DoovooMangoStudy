package src.conversion;

public class ImplicitConversion {       /// 묵시적 형 변환 (자동 형 변환)

    public static void main(String[] args) {

        /// 바이트 크기가 작은 자료형에서 큰 자료형으로 대입하는 경우
        // byte 형 변수 bNum 값을 int 형 변수 iNum에 대입함, 남은 3 바이트는 0으로 채워짐
        byte bNum = 10;                 // byte 는 1바이트
        int iNum = bNum;                // int 는 4바이트, byte 형 값이 int 형 변수로 대입됨

        System.out.println(bNum);
        System.out.println(iNum);

        /// 덜 정밀한 자료형에서 더 정밀한 자료형으로 대입하는 경우
        // 두 변수의 크기가 같은 4바이트라도 float 형인 fNum 이 더 정밀하게 데이터를 표현할 수 있다.
        // 따라서 실수형인 float 형으로 변환된다.
        int iNum2 = 20;
        float fNum = iNum2;             // int 형 값이 float 형 변수로 대입됨

        System.out.println(iNum);
        System.out.println(fNum);

        /// 연산 중에 자동으로 형 변환하는 경우
        // int iNum = 20;
        // float fNum = iNum;
        double dNum;
        dNum = fNum + iNum;             //1. float 형(fNum + iNum) 2. dNum으로 double 형으로 자동 변환
        // dNum = fNum + iNum 에서는 형 변환이 두 번 일어난다.
        // fNum + iNum 연산에서는 int 형이 float 형으로 변환되고,
        // 두 변수를 더한 결과값이 dNum에 대입되면서 double 형으로 변환된다.
        // 정리하면, 바이트 크기가 작은 수에서 큰 수로,
        // 덜 정밀한 수 에서 더 정밀한 수로 자료형이 변환되는 경우에 자동 형 변환이 일어난다.

        System.out.println(dNum);
    }
}
