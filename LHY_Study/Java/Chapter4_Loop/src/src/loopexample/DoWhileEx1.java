package src.loopexample;

public class DoWhileEx1 {

    public static void main(String[] args)  {

        int num = 1;
        int sum = 0;

        /**
         * do {
         *     수행문 1;
         *     ...
         * } while(조건식);
         *     수행문 2;
         *     ...
         * */

        do{
            sum += num;     // 조건식이 참이 아니더라도 무조건 한 번 수행함
            num++;

        }while( num <= 10  );

        System.out.println("1부터 10까지의 합은 " + sum + "입니다.");
    }
}
