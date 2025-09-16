package src.operator;

public class OperatorEx1 {                              // 산술 연산자를 사용하여 총점과 평균 구하기
    public static void main(String[] args) {
        int mathScore = 90;
        int engSccore = 70;

        int totalScore = mathScore + engSccore;         // 총점 구하기
        System.out.println(totalScore);

        double avgScore = totalScore / 2.0;             // 평균 구하기
        System.out.println(avgScore);

    }
}
