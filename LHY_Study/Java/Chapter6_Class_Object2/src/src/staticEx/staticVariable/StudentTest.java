package src.staticEx.staticVariable;

///  static 변수 테스트하기
public class StudentTest {

    public static void main(String[] args) {

        Student studentLee = new Student();
        studentLee.setStudentName("이지원");

        System.out.println(studentLee.serialNum);       // serialNum 변수의 초기값 출력 // 1000
        studentLee.serialNum++;                         // static 변수값 증가

        Student studentSon = new Student();
        studentSon.setStudentName("손수경");

        System.out.println(studentSon.serialNum);       // 증가한 값 출력                 // 1001
        System.out.println(studentLee.serialNum);                                       // 1001

        // static 으로 선언한 serialNum 변수는 모든 인스턴스가 공유하기 때문에 1001 로 출력된다.
        // 두 개의 참조 변수가 동일한 변수의 메모리를 가리킨다는 것을 알 수 있다.
    }
}
