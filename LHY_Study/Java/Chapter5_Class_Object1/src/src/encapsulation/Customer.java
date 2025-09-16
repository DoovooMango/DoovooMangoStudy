package src.encapsulation;

public class Customer {

    // 이름, 예산
    // 두 속성 모두 private 로 선언
    private String name;
    private double budget;

    public Customer(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    // getBudget() 메서드는 budget(예산) 을 반환
    public double getBudget() {
        return budget;
    }

    // buyPhone() 메서드는 PhoneStore 객체를 매개변수로 받아 고객이 핸드폰을 구매하는 기능을 구현
    public void buyPhone(PhoneStore store) {
        // store.sellPhone() 메서드는 대리점에서 고객이 예산 내에서 아이폰을 구매하려고 할 때,
        Phone phone = store.sellPhone("아이폰", budget);
        // phone 객체가 null 이 아니면 구입할 수 있다는 메시지 출력
        if(phone != null) {
            System.out.println("고객: 핸드폰 구입이 완료되었습니다.");
        }
        // null 이면 구매할 수 없다는 메시지 출력
        else{
            System.out.println("고객: 핸드론을 구입하지 못했습니다.");
        }
    }
}
