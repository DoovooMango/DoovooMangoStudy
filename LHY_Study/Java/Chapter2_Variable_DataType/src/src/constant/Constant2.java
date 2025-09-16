package src.constant;

public class Constant2 {

    // 1) 값(리터럴)을 직접 사용한 경우
    private static void runWithLiterals() {
        int count = 30;                             // if(count == 30) 을 참으로 만들기 위해 30으로 설정
        int i = 0;

        System.out.println("[리터럴 사용]");
        if (count == 30) {                          // 값이 30이라면
            System.out.println("count는 30입니다.");
        }

        System.out.print("while(i < 30) 루프: ");
        while (i < 30) {                            // 30보다 작은 동안
            System.out.print(i + (i < 29 ? " " : ""));
            i++;
        }
        System.out.println("\n");
    }

    // 2) 상수(final)로 선언하여 사용한 경우
    private static void runWithConstant() {
        final int MAX_STUDENT_NUM = 35;             // 상수 선언
        int count = MAX_STUDENT_NUM;                // if(count == MAX_STUDENT_NUM)을 참으로 만들기 위해 동일하게 설정
        int i = 0;

        System.out.println("[상수 사용]");
        if (count == MAX_STUDENT_NUM) {             // 값이 MAX_STUDENT_NUM이라면
            System.out.println("count는 MAX_STUDENT_NUM과 같습니다. (" + MAX_STUDENT_NUM + ")");
        }

        System.out.print("while(i < MAX_STUDENT_NUM) 루프: ");
        while (i < MAX_STUDENT_NUM) {               // MAX_STUDENT_NUM보다 작은 동안
            System.out.print(i + (i < MAX_STUDENT_NUM - 1 ? " " : ""));
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runWithLiterals();
        runWithConstant();
    }
}
