package src.reference;

public class Student4 {

    int studentID;
    String studentName;

    // Subject2 형을 사용하여 선언
    Subject2 korean;
    Subject2 math;

    public Student4(int studentID, String studentName){
        this.studentID = studentID;
        this.studentName = studentName;

        // 변수 korean 과 math 의 인스턴스를 생성
        korean = new Subject2();
        math = new Subject2();
    }

    public void showStudentInfo()
    {
        System.out.println(studentName + "님의 " + korean.getSubjectName() + " 과목의 점수는 "
                + korean.getScorePoint() + "점 이며 " + math.getSubjectName() + " 과목의 점수는 "
                + math.getScorePoint() + "점입니다.");

    }

    // 매개변수로 넘긴 값을 이용해 Subject 메서드를 호출하고 값을 대입
    public void setKoreanSubject(String subjectName, int score) {
        korean.setSubjectName(subjectName);
        korean.setScorePoint(score);
    }

    public void serMathSubject(String subjectName, int score) {
        math.setSubjectName(subjectName);
        math.setScorePoint(score);
    }
}
