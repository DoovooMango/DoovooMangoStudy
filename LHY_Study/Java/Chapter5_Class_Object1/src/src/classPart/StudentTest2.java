package src.classPart;

public class StudentTest2 {

    public static void main(String[] args) {

        // 첫 번째 학생 생성
        Student student1 = new Student();
        student1.studentName = "이훈영";                   // 멤버 변수 사용
        System.out.println(student1.getStudentName());    // 메서드 사용

        ///  참조변수 studentLee 사용법
        // 도트 연산자 활용해서!
        // 참조 변수.멤버 변수
        // studentLee.studentName
        // 참조 변수.메서드
        // studentLee.getStudentName

        // 두 번째 학생 생성
        Student student2 = new Student();
        student2.studentName = "김훈영";
        System.out.println(student2.getStudentName());

        /***
         *
         * [스택 메모리]                [힙 메모리]
         * studentLee ─────────────▶  Student@1a2b3c { name: "Lee" }
         * (참조 변수)                  (인스턴스)
         * (지역 변수)
         *
         * "지역 변수 studentLee에 생성된 인스턴스를 대입하는 것은
         * studentLee에 힙에서 생성된 인스턴스를 가리키는 '참조값'(힙 메모리의 주소)를 대입한다는것."
         *
         * 이렇게 설명하는 이유는
         * new Student() 는 (개념적으로) 힙에 객체를 만들고 ‘참조값’을 돌려줍니다.
         * studentLee 같은 참조 타입 변수는 그 참조값을 담아요. 객체 자체를 담는 게 아닙니다.
         * ‘주소’라고 흔히 말하지만, JVM은 실제 물리 주소를 노출하지 않으므로 정확한 표현은 “참조(reference)”가 더 적절합니다.
         *
         * Student a = new Student();
         * Student b = a;              // 참조값만 복사 (객체는 하나)
         * a.name = "Lee";
         * System.out.println(b.name); // Lee  ← 같은 객체를 가리키기 때문
         *
         * 참고: System.out.println(a); 처럼 찍히는 Student@1a2b3c 형태는 실제 메모리 주소가 아니라 기본 toString()이 보여주는 타입@해시코드 표현이에요.
         */
    }
}
