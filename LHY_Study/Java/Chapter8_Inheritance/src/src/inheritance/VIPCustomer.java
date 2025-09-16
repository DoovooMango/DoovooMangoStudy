package src.inheritance;

public class VIPCustomer extends Customer{
    ///  VIPCustomer 클래스는 Customer 클래스를 상속받음

    /// 인스턴스 변수
    /// Customer 클래스와 겹치는 인스턴스 변수
//    protected int customerID;       // 고객 아이디
//    protected String customerName;  // 고객 이름
//    protected String customerGrade; // 고객 등급
//    int bonusPoint;                 // 보너스 포인트
//    double bonusRatio;              // 적립 비율

    ///  VIP 고객 관련 기능을 구현할 때만 필요한 인스턴스 변수
    private int agentID;                // VIP 고객 담당 상담원 아이디
    double saleRatio;                   // 할인율

    ///  디폴트 생성자
//    public VIPCustomer()
//    {
//        super();                        /// 컴파일러가 자동으로 추가하는 코드로, 상위 클래스의 Customer()가 호출됨.
//        customerGrade = "VIP";          // 고객 등급 VIP
//                                        // 상위 클래스에서 private 변수 이므로 오류 발생 - > protected
//        bonusRatio = 0.05;              // 보너스 적립률 5%
//        saleRatio = 0.1;                // 할인율 10%
//        System.out.println("VIPCusomer() 생성자 호출");      // 하위 클래스를 생성할 때 출력되는 콘솔 출력문
//    }

    public VIPCustomer(int customerID, String customerName, int agentID){
        super(customerID, customerName);        /// 상위 클래스 생성자 호출

        /**
         * <super(customerID, customerName);>가 가리키는 것은 Customer 클래스의
         * public Customer(int customerID, String customerName){
         *         this.customerID = customerID;
         *         this.customerName = customerName;
         *         customerGrade = "SILVER";
         *         bonusRatio = 0.01;
         *         System.out.println("Cusomer(int, String) 생성자 호출");
         *     }
         * */

        customerGrade = "VIP";
        bonusRatio = 0.05;
        saleRatio = 0.1;
        this.agentID = agentID;
        System.out.println("VIPCusomer(int, String) 생성자 호출");
    }

    @Override
    ///  재정의한 메서드
    public int calcPrice(int price){
        bonusPoint += price * bonusRatio;           // 보너스 포인트 적립
        return price - (int)(price * saleRatio);    // 할인율을 적용한 가격 변환
    }

    ///  VIP 고객에게만 필요한 메서드
    public int getAgentID(){
        return agentID;
    }

    ///  고객 정보를 반환하는 showCustomerInfo() 메서드
    ///  Coustomer 클래스에 있는 동일한 메서드
//    public String showCustomerInfo(){
//        return customerName + "님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "입니다.";
//    }

    public String showVIPInfo() {
        return super.showCustomerInfo() + "담당 상담원 아이디는 " + agentID + "입니다.";
    }

}
