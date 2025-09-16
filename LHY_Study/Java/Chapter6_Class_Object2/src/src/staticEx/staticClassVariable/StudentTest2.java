package src.staticEx.staticClassVariable;

///  클래스 이름으로 static 변수 참조하기
public class StudentTest2 {

    /**
     * static 변수, 정적 변수, 클래스 변수.
     * 셋 모두 자바에서 static 변수를 의미.
     * 자바에서 static 변수를 클래스 변수라고 하는 이유는 인스턴스마다 생성되는 변수가 아니라
     * 클래스에 속해 한 번 만 생성되는 변수이고 이를 여러 인스턴스가 공유한다.
     * */

    public static void main(String[] args) {

        Student1 studentLee = new Student1();
        studentLee.setStudentName("이지원");
        System.out.println(Student1.serialNum);

        /// Student1.serialNum.
        /// serialNum 변수를 직접 클래스 이름을 참조

        System.out.println(studentLee.studentName + " 학번:"
                + studentLee.studentID);

        Student1 studentSon = new Student1();
        studentSon.setStudentName("손수경");
        System.out.println(Student1.serialNum);
        System.out.println(studentSon.studentName + " 학번:"
                + studentSon.studentID);
    }
}
