package src.staticEx.staticVariable;

///  static 변수 사용하기
public class Student {

    /**
     * Student 클래스를 사용하면 여러 학생의 인스턴스를 만들 수 있다.
     * 그리고 학생마다 고유한 학번 studentId를 가진다.
     * 학생이 입학하면(인스턴스가 생성ㅎ되면) 학번이 자동으로 생성되도록 만들고 싶다.
     * 이 때 생성된 인스턴스는 학번을 순서대로 가져야 한다. 어떻게 학생에게 학번을 부여하게 되는가?
     * 이 경우, 인스턴스마다 따로 생성되는 변수가 아닌 클래스 전반에서 공통으로 사용할 수 있는 기준이 되는 변수가 있어야 한다.
     * 그리고 학생이 한 명 생성될 때마다 기준 변수값을 하나씩 증가시켜서 각 학생 인스턴스의 학번 변수에 대입해 주면 된다.
     * 이 때에 어떤 변수를 여러 클래스에서 공통으로 사용하고 싶다면 변수를 static 변수로 선언하면 된다.
     * */

    /**
     * static 변수
     *
     * static 변수는 정적 변수라고도 한다. 이 변수는 자바 뿐 아니라 다른 언어에서도 비슷한 개념으로 사용되고 있다.
     * 자바에서는 다른 인스턴스 변수처럼 클래스 내부에 선언합니다.
     * 자바에서는 변수를 선언할 때에는 자료형 앞에 static 예약어를 사용한다.
     * <static 예약어 > <자료형> <변수 이름>
     * static           int     serialNum ;
     *
     * static 변수는 클래스 내부에 선언하지만, 다른 인스턴스 변수처럼 인스턴스가 생성될 때마다 새로 생성되는 변수가 아니다.
     * static 변수는 프로그램이 실행되어 메모리에 올라갔을 때 딱 한 번 메모리 공간이 할당된다.
     * 그리고 그 값은 모든 인스턴스가 공유한다.
     * 다시 말하면 일반 인스턴스 변수는 인스턴스가 생설될 때마다 새로 생성되어 각각 다른 studentName을 가지지만,
     * static 으로 선언한 변수는 인스턴스 생상과 상관없이 먼저 생성되고 그 값을 모든 인스턴스가 공유하는 것이다.
     * 이러한 이유 때문에 static 변수를 클래스에 기반한 변수라고 해서 클래스 변수 class variable 이라고 한다.
     * */

    public static int serialNum = 1000;         // static 변수는 인스턴스 생성과 상관없이 먼저 생성됨
    int studentID;
    String studentName;
    int grade;
    String address;


    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String name){
        studentName = name;
    }

    public static int getSerialNum() {
        int i = 10;
        //	studentName = "aaa";
        return serialNum;
    }

    public static void setSerialNum(int serialNum) {
        Student.serialNum = serialNum;
    }
}
