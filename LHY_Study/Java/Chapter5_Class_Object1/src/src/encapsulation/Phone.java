package src.encapsulation;

public class Phone {

    // 핸드폰 모델, 가격
    // 두 가지 인스턴스 변수를 private로 선언
    private String model;
    private double price;

    public Phone(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }
}
