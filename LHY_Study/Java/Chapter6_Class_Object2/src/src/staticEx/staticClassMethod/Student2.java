package src.staticEx.staticClassMethod;

///  클래스 메서드(static 메서드)와 인스턴스 변수
public class Student2 {

    /// private 변수로 변경
    private static int serialNum = 1000;

    int studentID;
    String studentName;
    int grade;
    String address;

    public Student2(){
        serialNum++;
        studentID = serialNum;
    }

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String name){
        studentName = name;
    }

    /// serialNum의 get() 메서드 - 클래스 메서드
    // studentName = "이지원"; 는 인스턴스 변수
    // 클래스 메서드 내부에서는 인스턴스 변수를 사용할 수 없다.
    public static int getSerialNum() {
        int i = 10;
//        studentName = "이지원";
        return serialNum;
    }

    /**
     * 클래스 메서드와 인스턴스 변수
     * getSerialNum() 메서드는 static 예약어를 붙인 클래스 메서드입니다.
     * 이 메서드는 세 종류의 변수를 사용하고 있다.
     * 일단 가장 먼저 선언한 int i를 보자. 이 변수는 메서드 내부에서 선언하였다.
     * 이렇게 메서드 내부에서 선언한 변수를 그 지역에서만 사용한다고 해서 지역 변수(Local variable) 이라고 한다.
     * 지역 변수는 메서드가 호출될 때 메모리에 생성되어 메서드가 끝나면 사라진다.
     * 따라서 이 변수는 getSerialNum()메서드 내부에서만 사용할 수 있다.
     *
     * 마지막 return serialNum; 문장을 보면 serialNum은 static 변수이다.
     * 그러므로 클래스 메서드인 getserialNum() 메서드 내부에서도 serialNu을 사용할 수 있다.
     * 그런데 메서드 내부의 두 번째 줄에 사용한 studentName 변수는 오류가 발생한다.
     * 이 변수는 Student2 클래스의 인스턴스 변수로, 인스턴스가 생성될 때 만들어지는 인스턴스 변수이기 때문이다.
     * */

    /// serialNum의 set() 메서드
    public static void setSerialNum(int serialNum) {
        Student2.serialNum = serialNum;
    }
}
