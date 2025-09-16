package src.classPart;

public class StudentTest1 {

    public static void main(String[] args) {

        Student studentLee = new Student();

        studentLee.studentName = "이훈영";

        System.out.println(studentLee.studentName);
        System.out.println(studentLee.getStudentName());
    }
}
