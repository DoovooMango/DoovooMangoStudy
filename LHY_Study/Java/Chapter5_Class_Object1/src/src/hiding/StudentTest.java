package src.hiding;

public class StudentTest {

    public static void main(String[] args) {
        Student studentLee = new Student();
//        studentLee.studentName = "이훈영";     // studentName 오류 발생
        /// 오류 발생 이유
        // Student 클래스에서 studentName에 private 라면 외부 클래스의 접근이 허용되지 않음.
        // public 이라면 외부 클래스인 StduentTest 클래스에서 접근이 가능하다.

        studentLee.setStudentName("이훈영");
        // setStudentName() 메서드를 활용해 private 변수에 접근 가능

        System.out.println(studentLee.getStudentName());
    }
}
