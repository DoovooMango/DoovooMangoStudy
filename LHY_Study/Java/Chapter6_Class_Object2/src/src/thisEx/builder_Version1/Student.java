package src.thisEx.builder_Version1;

/**
 * 빌더 패턴
 * 클래스에서 인스턴스를 생성할 때 사용하는 방법 중 하나.
 * 객체를 생성할 때 복잡함을 줄이기 위해 사용하는 디자인 패턴
 * 인스턴스를 생성할 때 생성자나 매개변수가 많은 경우에 유용하다.
 * 예를 들어 인스턴스 변수가 많고, 이 변수를 다양한 방법으로 초기화 할 경우엔 여러 생성자가 오버로딩 되어야 한다.
 * */

class Student {
    private int id;
    private String name;
    private int grade;
    private String major;
    private String phoneNumber;

    ///  생성자 오버로딩을 통해 다양한 방법으로 Student 객체를 생성할 수 있게 한다.
    // 생성자 1 학생 객체 초기화
    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    // 생성자 2 학생 객체 초기화
    public Student(int id, String name, int grade){
        this(id, name);
        this.grade = grade;
    }

    // 생성자 3 학생 객체 초기화
    // 생성자가 여러 개로 오버로딩 된 경우 매개변수의 순서를 다르게 전달하면 개발자가 의도한 것과 다른 값이 생성될 수 있다.
    // 매개변수가 5개인 생성자를 보면,
    // major와 phoneNumber는 같은 String 매개변수를 가지는데, 두 생성자로 인스턴스를 생서할 경우 다음과 같은 경우
    // Student studentLee = new Student(12345, "김원상", 3, "010-2222-3333", "컴퓨터공학");
    // 매개 변수에 들어갈 값의 순서가 바뀌어도 컴파일 오류가 발생할지 않아서, 코드에서 잘못된 점을 알기 어렵다.
    // 그럴 경우에 매개변수가 다양하게 입력될 경우 빌더 패턴을 활용하면 유용하다.
    public Student(int id, String name, int grade, String major, String phoneNumber){
        this(id, name, grade);
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public Student setId(int id) {
        this.id = id;
        return this;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setGrade(int grade) {
        this.grade = grade;
        return this;
    }

    public void showStudentInfo(){
        System.out.println(name +"님의 학번은 " + id + "이고, " + grade + "학년입니다.");
    }
}
