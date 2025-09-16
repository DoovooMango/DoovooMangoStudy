package src.loopexample;

public class BreakEx2 {

    public static void main(String[] args) {

        int sum = 0;
        int num = 0;

        for(num=0; ; num++){                // for(num=0; ( 여기에 조건식을 생략하는 대신에 break문을 사용 ); num++){
            sum += num;
            if(sum >= 100)                  // sum 이 100 보다 크거나 같을 때 (종료 조건)
                break;                      // 반복 중단
        }

        System.out.println("num : " + num);
        System.out.println("sun : " +sum);
    }
}
