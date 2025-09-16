package src.loopexample;

public class WhileEx1 {

    public static void main(String[] args) {

        int num = 1;
        int sum = 0;

        /**
         *  while(조건식) {
         *      수행문 1;      // 조건식이 참 인동안 반복 수행
         *      ...
         *  }
         *      수행문 2;
         *      ...
         * */

        while(num <= 10){       // num 값이 10보다 작거나 같을 동안
            sum += num;         // 합계를 뜻하는 sum에 num을 더하고  - 조건식이 참인 동안 반복 수행
            num++;              // num 에 1씩 더해 나감
        }
        System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
    }
}
