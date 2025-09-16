package src.doubleEx;

public class Double2 {                      /// 부동소수점의 오차 확인

    public static void main(String[] args) {

        double doubleNum = 1;

        for(int i=0; i < 10000; i++){
            doubleNum = doubleNum + 0.1;
        }
        System.out.println(doubleNum);

        // 숫자 1에 0.1 을 10,000 더하므로 1001 일것 같지만,
        // 결과는 1001.000000000159
    }
}
