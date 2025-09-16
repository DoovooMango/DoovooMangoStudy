package src.classPart;

public class StudentTest3 {     ///  참조값 출력

    public static void main(String[] args) {

        Student student1 = new Student();
        student1.studentName = "이훈영";
        // student1 변수를 사용하용 student1 인스턴스를 참조할 수 있다.
        // student1 : 참조변수
        // 주소값을 참조값.

        Student student2 = new Student();
        student2.studentName = "김훈영";

        System.out.println(student1);
        System.out.println(student2);

        /**
         * System.out.println(student1); 처럼 찍히는 Student@119d7047 형태는 `클래스 이름@참조값(주소값)`
         * 실제 메모리 주소가 아니라 기본 toString()이 보여주는 타입 @해시코드 표현입니다. '해시코드 값'
         * */
    }
}
