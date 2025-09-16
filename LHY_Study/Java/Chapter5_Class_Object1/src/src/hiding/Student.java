package src.hiding;

public class Student {

    int studentID;
    private String studentName;         /// 접근 제어자 (Access Modifier)
    int grade;
    String address;

    /**
     * private으로 선언한 studentName 변수를 외부 코드에서 사용하려면?
     * studentName 변수를 사용할 수 있도록 public 메서드를 제공해야 한다. public 메서드가 제공되지 않는다면 studentName 변수에 접근할 방법이 없다.
     * 이 때 사용하는 것이 get(), set() 메서드이다.
     * */

    ///  값을 얻는 get() 메서드를 getter
    // private 변수인 studentName에 접근해 값을 가져오는 public get()메서드
    public String getStudentName() {
        return studentName;
    }

    ///  값을 지정하는 set() 메서드를 setter
    // private 변수인 studentName에접근해 값을 지정하는 pubilc set() 메서드
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
