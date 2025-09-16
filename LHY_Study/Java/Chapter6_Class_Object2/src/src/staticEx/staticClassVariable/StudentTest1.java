package src.staticEx.staticClassVariable;

///  학번 확인하기
public class StudentTest1 {

    public static void main(String[] args) {

        Student1 studentLee = new Student1();
        studentLee.setStudentName("이지원");
        System.out.println(studentLee.serialNum);

        /// 노란색 블록이 표시되는 이유
        ///  serialNum이 static 변수이므로 인스턴스가 아닌 클래스 이름으로 직접 참조하라는 뜻
        ///  예시 StudnetTest2 에서 System.out.println(Student1.serialNum);
        ///  인텔리제이에서는 static 변수와 static 메서드는 이탤릭체로 표시된다.

        System.out.println(studentLee.studentName + " 학번:"
                + studentLee.studentID);

        Student1 studentSon = new Student1();
        studentSon.setStudentName("손수경");
        System.out.println(studentSon.serialNum);
        System.out.println(studentSon.studentName + " 학번:"
                + studentSon.studentID);
    }
}
