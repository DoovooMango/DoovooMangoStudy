package src.reference;

public class Student3 {

    int studentID;          // 학번
    String studentName;     // 학생 이름

    // Subject1 형을 사용하여 선언
    Subject1 korean;         // 국어 과목
    Subject1 math;           // 수학 과목

    public Student3(){
        korean = new Subject1();
        math = new Subject1();
    }

    public void setKorean(String name, int score)
    {
        korean.subjectName = name;
        korean.scorePoint = score;
    }

    public void setMath(String name, int score)
    {
        math.subjectName = name;
        math.scorePoint = score;
    }
}
