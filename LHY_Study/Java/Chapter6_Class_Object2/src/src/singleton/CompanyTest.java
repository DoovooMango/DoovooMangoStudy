package src.singleton;

public class CompanyTest {

    public static void main(String[] args) {

        ///  [4] 싱글톤 객체를 사용하는 코드 만들기
        // Company 클래스 이름으로 getInstance() 을 호출하여 참조 변수에 대입
        Company myCompany1 = Company.getInstance();
        Company myCompany2 = Company.getInstance();

                            // 두 변수가 같은 주소인지 확인
        System.out.println( myCompany1 == myCompany2 );     // 출력값 : true
        // myCompany1, myCompany2는 같은 참조값을 가지는 동일한 인스턴스임을 알 수 있다.
        // Company 클래스는 내부에 생성된 유일한 인스턴스 외에는 더 이상 인스턴스를 생성할 수 없다.
        // 이와 같이 static 을 사용하면 유일한 객체를 생성하는 싱글톤 패턴을 구현할 수 있다.


        /**
         * 외부 클래스에서는 Company를 생성할 수 없으므로 static 으로 제공되는 getInstance() 메서드를 호출합니다.
         * Company.getInstance(); 와 같이 호출하면 반환값으로 유일한 인스턴스를 받아 옵니다.
         * */
    }
}
