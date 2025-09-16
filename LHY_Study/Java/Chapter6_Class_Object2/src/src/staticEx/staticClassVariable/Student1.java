package src.staticEx.staticClassVariable;

///  학번 자동으로 부여하기
public class Student1 {

    public static int serialNum = 1000;

    /**
     * static 변수(serialNum) 하나를 선언.
     * 학생 인스턴스가 생성될 때마다 이 변수값은 증가한다.
     * 그러나 주의할 점은 static 변수를 바로 학번으로 사용하면 안된다.
     * static 변수는 모든 인스턴스가 공유하는 변수이므로 이 변수를 바로 학번으로 사용하면 모든 학생이 동일한 학번을 가지게 된다.
     * */

    int studentID;
    String studentName;
    int grade;
    String address;

    ///  생성자
    public Student1(){
        serialNum++;                // 학생이 생성될 때마다 증가
        studentID = serialNum;      // 증가된 값을 학번 인스턴스 변수에 부여
    }

    /**
     * 학번은 학생의 고유 번호이므로 학생의 인스턴스 변수로 선언해 주고, 학생이 한 명 생성될 때마다 증가한 serialNum 값을
     * studentID에 대입해 주면 이 문제를 해결할 수 있다.
     * public Student1(){
     *         serialNum++;                // 학생이 생성될 때마다 증가
     *         studentID = serialNum;      // 증가된 값을 학번 인스턴스 변수에 부여
     *     }
     * Student 클래스에 생성자를 추가해 생성자에서 serialNum 값을 증가시키고 이렇게 증가한 값을 studentID 변수에 대입하도록 구현한다.
     * */

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String name){
        studentName = name;
    }

    public static int getSerialNum() {
        int i = 10;
        //	studentName = "aaa";
        return serialNum;
    }

    public static void setSerialNum(int serialNum) {
        Student1.serialNum = serialNum;
    }
}
