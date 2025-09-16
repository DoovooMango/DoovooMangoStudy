package src.staticEx.staticClassMethod;

public class StudentTest4 {

    public static void main(String[] args) {

        System.out.println(Student2.getSerialNum());

        /**
         * 클래스 메서드와 클래스 변수는 인스턴스가 생성되지 않아도 사용할 수 있다.
         *
         * (Student2.getSerialNum()) 과 같이 인스턴스가 생성되지 않아도 클래스 메서드는 언제든 호출할 수 있다.
         * studentName 처럼 인스턴스가 생성되어야 메모리가 할당되는 인스턴스 변수는 클래스 메서드에서 사용할 수 없다.
         * */

        /**
         * 클래스 메서드 내부에서 지역 변수와 클래스 변수는 사용할 수 있찌만, 인스턴스 변수는 사용할 수 없다.
         * 또한 클래스 메서드에서 인스턴스 변수를 사용할 수는 없지만, 반대로 일반 메서드에서 클래스 변수를 사용하는 것은 문제가 되지 않는다.
         * 왜냐하면 일반 메서드는 인스턴스가 생성될 때 호출되는 메서드이고,
         * 클래스 변수는 이미 만들어진 변수이기 때문이다. 일반 메서드에서도 클래스 변수를 호출할 수 있기 때문이다.
         * */
    }
}
