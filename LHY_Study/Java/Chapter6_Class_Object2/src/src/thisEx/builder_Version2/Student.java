package src.thisEx.builder_Version2;

/**
 * 빌더 패턴 적용 전 - 텔레스코핑 생성자 & 체이닝 setter 예시
 * 문제점: 매개변수 순서가 바뀌어도 컴파일 에러 없이 잘못된 값이 들어갈 수 있음.
 */
class Student {
    private int id;
    private String name;
    private int grade;
    private String major;
    private String phoneNumber;

    // 생성자 1
    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    // 생성자 2
    public Student(int id, String name, int grade){
        this(id, name);
        this.grade = grade;
    }

    // 생성자 3 (모든 필드)
    public Student(int id, String name, int grade, String major, String phoneNumber){
        this(id, name, grade);
        this.major = major;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getGrade() { return grade; }
    public String getMajor() { return major; }
    public String getPhoneNumber() { return phoneNumber; }

    // 체이닝 setter (불변성 보장 X)
    public Student setId(int id) { this.id = id; return this; }
    public Student setName(String name) { this.name = name; return this; }
    public Student setGrade(int grade) { this.grade = grade; return this; }
    public Student setMajor(String major) { this.major = major; return this; }
    public Student setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }

    public void showStudentInfo(){
        System.out.println("[Student] id=" + id +
                ", name=" + name +
                ", grade=" + grade +
                ", major=" + major +
                ", phone=" + phoneNumber);
    }
}
