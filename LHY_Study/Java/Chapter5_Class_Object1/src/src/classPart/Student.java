package src.classPart;

public class Student {
    // class    : 클래스를 만드는 예약어
    // Student  : 클래스 이름

    /**
     * 멤버 변수
     * */

    ///  클래스 속성을 구현하는 멤버 변수 (속성 Property, 특성 Attribute)
    int studentID;                  // 학번
    String studentName;             // 학생 이름
    int grade;                      // 학년
    String address;                 // 사는 곳

    /**
     * 멤버 함수, 메서드
     * */

    ///  클래스 기능을 구현하는 멤버 함수(member Function) 또는 메서드(Method)
    public void showStudentInfo(){
        System.out.println(studentName + "," + address);  // 이름, 주소 출력
    }

    /// 학생 이름을 반환하는 메서드 구현
    /// StudentName을 반환하는 get() 메서드 구현
    public String getStudentName() {
        return studentName;
    }

    /// 학생 이름을 부여하는 메서드 구현
    /// 학생 이름 String name 을 배개변수로 전달
    /// void 는 반환값이 없으므로 void로 지정
    public void setStudentName(String name){
        studentName = name;
    }

    public static void main(String[] args) {
        Student studentLee = new Student();         // Student 클래스 생성 // 참조 변수 ← 인스턴스의 주소
        studentLee.studentName = "이훈영";           // Student 클래스의 멤버 변수에 값을 대입

        /**
         * 클래스(Class) = 설계도
         * 인스턴스(Instance) = 설계도로 만든 실제 객체(메모리에 존재하는 값)
         *
         * new 생성자() 는 힙(Heap) 메모리에 인스턴스를 만들고, 그 "참조(주소)"를 반환합니다.
         * 클래스형(참조 타입) 변수는 그 참조(주소)를 담는 변수이므로 "참조 변수"라고 합니다.
         *
         * 예) Student studentLee = new Student();
         *  - 클래스형 / 변수 이름 = new 생성자;
         *  - new Student() : 힙에 Student 인스턴스를 생성
         *  - studentLee    : Student 타입의 "참조 변수" (인스턴스 자체가 아님!)
         *  - =             : 생성된 인스턴스의 참조(주소)를 studentLee에 대입
         *
         * 한 줄 요약
         *  - 클래스는 설계도, new는 객체 생성, 참조 변수는 그 객체의 주소를 가진다.
         *  - 참조 변수 ≠ 인스턴스
         *  - 참조 변수는 “인스턴스를 가리키는 주소”를 담는 변수
         *
         * [스택 메모리]                [힙 메모리]
         * studentLee ─────────────▶  Student@1a2b3c { name: "Lee" }
         * (참조 변수)                  (인스턴스)
         * */

        System.out.println(studentLee.studentName);
        System.out.println(studentLee.getStudentName());
    }
}
