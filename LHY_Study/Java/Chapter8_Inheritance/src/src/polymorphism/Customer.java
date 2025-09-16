package src.polymorphism;

public class Customer {

    protected int customerID;
    protected String customerName;
    protected String customerGrade;
    int bonusPoint;
    double bonusRatio;

    public Customer()
    {
        initCustomer();                     // 고객 등급과 보너스 포인트 적립률 지정 함수 호출
    }

    public Customer(int customerID, String customerName){
        this.customerID = customerID;
        this.customerName = customerName;

        initCustomer();                     // 고객 등급과 보너스 포인트 적립률 지정 함수 호출
    }

    ///  클래스의 인스턴스 변수의 초기화 부분
    private void initCustomer()         // private 생성자에서만 호출하는 메서드이므로 private로 선언
    {
        customerGrade = "SILVER";
        bonusRatio = 0.01;
    }

    public int calcPrice(int price){
        bonusPoint += price * bonusRatio;
        return price;
    }

    public String showCustomerInfo(){
        return customerName + " 님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "점입니다.";
    }

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
