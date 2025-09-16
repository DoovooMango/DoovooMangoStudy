package src.inheritance;

public class Customer {

    /// 인스턴스 변수
    protected int customerID;       // 고객 아이디
    protected String customerName;  // 고객 이름
    protected String customerGrade; // 고객 등급
    public int bonusPoint;                 // 보너스 포인트
    double bonusRatio;              // 적립 비율

    /**
     * 상속을 위한 protected 예약어
     * 상위 클래스 Customer 에서 선언된 customerGrade 가 private로 선언한 인스턴스 변수이기 때문에 발생한 오류..
     * 상위 클래스에 작성한 변수나 메서드 중 외부 클래스에서 사용할 수 없지만
     * 하위 클래스에서는 사용할 수 있도록 지정하는 예약어가 protected 이다.
     * 상속받은 하위 클래에서는 public 처럼 사용할 수 있는 것이다.
     * 즉, protected 는 상속된 하위 클래스를 제외한 나머지 외부 클래스에서는 private와 동일한 역할을 한다.
     * */

    ///  디폴트 생성자
//	public Customer()
//	{
//		customerGrade = "SILVER";                       // 기본 고객 등그
//		bonusRatio = 0.01;                              // 보너스 포인트 기본 적립 비율
//		System.out.println("Cusomer() 생성자 호출");      // 상위 클래스를 생성할 때 출력되는 콘솔 출력문
//	}

    public Customer(int customerID, String customerName){
        this.customerID = customerID;
        this.customerName = customerName;
        customerGrade = "SILVER";
        bonusRatio = 0.01;
        System.out.println("Cusomer(int, String) 생성자 호출");
    }

    ///  보너스 포인트 적립, 지불가격 계산 calcPrice() 메서드
    public int calcPrice(int price){
        bonusPoint += price * bonusRatio;               // 보너스 포인트 계산
        return price;
    }

    ///  고객 정보를 반환하는 showCustomerInfo() 메서드
    public String showCustomerInfo(){
        return customerName + "님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "입니다.";
    }

    /** protected 예약어로 선언한 변수를 외부에서 사용할 수 있또록 get(), set() 메서드 추가 */

    /**
     * protected 예약어로 선언한 변수는 외부 클래스에는 private 변수 처럼 get() 메서드를 사용해 값을 가져올 수 있고,
     * set() 메서드를 사용해 값을 지정할 수 있다.
     * Customer 클래스를 상속받은 VIPCustomer 클래스는 protected로 선언한 변수를 상속받게 되고,
     * public 메서드도 상속받아 사용할 수 있다. 결과적으로 protected 로 선언하면 VIPCustomer 클래스를 작성할 때 생긴 오류는 사라진다.
     * */

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

}
