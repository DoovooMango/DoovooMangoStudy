package src.thisEx;

/// 자신을 반환하는 this 활용 - Fluent Setters/메서드 체이닝 예제
/**
 * this는 자기 자신의 객체 자료형으로 해 인스턴스를 반환하는 예약어이기도 하다.
 * 메서드가 this를 반환하면 해당 인스턴스 자체를 반환하는 것이므로 같은 인스턴스의 메서드를 연속하여 호출할 수 있다.
 * 여러 메서드를 호출하면서 객체의 값을 채워 가는 코드를 예제로 보자.
 *
 * - this: 현재 인스턴스를 가리킵니다.
 * - 메서드의 반환형을 클래스 타입(Student)으로 선언하고 `return this`를 사용하면
 *   "현재 인스턴스 자체"를 반환하므로 같은 인스턴스의 메서드를 연속 호출(체이닝)할 수 있습니다.
 * */

///  this로 객체 자신의 인스턴스를 반환하기
/**
 * 예제의 핵심은 “setter의 반환형을 Student로 두고 return this;로 현재 인스턴스를 돌려주면 메서드 체이닝이 가능하다”는 점이에요.
 * 주석·문구를 조금 다듬고, 아주 얕은 유효성 검사만 더한 버전을 아래에 정리했어요.
 *
 * 무엇을 이해하면 되나요?
 * this는 “현재 인스턴스” 참조예요. 메서드의 반환형을 클래스 타입으로 선언하면 return this;로 자기 자신을 반환할 수 있어요.
 * 마지막에 void 메서드(showStudentInfo)를 붙여 호출해도 OK.
 * 다만 void는 더 이어서 체이닝할 수 없으니 맨 끝에 두면 됩니다.
 *
 * 체이닝은 읽기 쉽고(DSL처럼) 필드 채우기(빌더 느낌)의 의도가 분명해집니다.
 * */

class Student{
    private int id;
    private String name;
    private int grade;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    // this 로 현재 객체를참조해 인스턴스 변수 id를 매개변수로 설정하고 이를 반환
    // 현재 객체(this)의 필드를 설정한 뒤 자기 자신을 반환 → 체이닝 가능
    public Student setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("id는 양수여야 합니다.");
        this.id = id;
        return this;
    }

    public Student setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name은 비어 있을 수 없습니다.");
        this.name = name;
        return this;
    }

    public Student setGrade(int grade) {
        // 예시: 1~6학년만 허용한다고 가정
        if (grade < 1 || grade > 6) throw new IllegalArgumentException("grade는 1~6 사이여야 합니다.");
        this.grade = grade;
        return this;
    }

    public void showStudentInfo1(){
        System.out.println(name +"님의 학번은 " + id + "이고, " + grade + "학년입니다.");
        // 출력 결과 : 김원상님의 학번은 12345이고, 3학년입니다.
    }

    public void showStudentInfo2() {
        System.out.printf("%s님의 학번은 %d이고, %d학년입니다.%n", name, id, grade);
        // 예) 김원상님의 학번은 12345이고, 3학년입니다.
    }
}

public class ReturnItSelf {

    public static void main(String[] args) {
        // 1) 체이닝으로 한 줄에 값 설정 + 출력
        new Student()
                .setId(12345)
                .setName("김원상")
                .setGrade(3)
                .showStudentInfo1();


        Student studentLee = new Student();
        studentLee.setId(12345).setName("김원상").setGrade(3).showStudentInfo1();
        // 연속 호출

        // 2) 단계별로 나눠서 동일 동작
        Student student1 = studentLee.setId(12345);
        Student student2 = student1.setName("김원상");
        Student student3 = student2.setGrade(3);
        student3.showStudentInfo1();

        /**
         * setId(), setName(), setGrade() 메서드는 모두 this를 반환하고 showStudentInfo()는 void를 반환하고 있다.
         * 메서드가 this를 반환하면 반환되는 자료형이 자신이기 때문에 여러 줄의 코드로 작성하지 않고 연속하여 호출하는 방식으로 작성할 수 있다.
         * */

        /**
         * 요약:
         * - setId/setName/setGrade는 모두 this(Student)를 반환 → 연속 호출 가능
         * - showStudentInfo는 void 반환 → 더 이어서 체인할 수 없으므로 마지막에 호출
         */

        /**
         * 코멘트 & 팁
         *
         * 주석 문구 다듬기
         * (원문) “this는 자기 자신의 객체 자료형으로 해 인스턴스를 반환하는 예약어…”
         * (제안) “this는 현재 인스턴스를 가리키며, 메서드의 반환형을 클래스 타입으로 선언하면 return this로 현재 인스턴스를 반환할 수 있습니다.”
         *
         * 불변(immutable)로 가고 싶다면 setXxx 대신 withXxx가 새 인스턴스를 반환하도록 만들거나, Builder 패턴을 고려해도 좋아요.
         *
         * 상속과 체이닝이 섞이면 반환형이 Student로 고정되어 하위 클래스 체인이 끊길 수 있어요. 필요하면 self-referential generics로 T extends Student<T> 패턴을 쓰는 방법이 있습니다.
         *
         * Lombok을 사용 중이면 @Setter @Accessors(chain = true)로 동일한 체이닝 세터를 자동 생성할 수 있어요.
         * */
    }
}
