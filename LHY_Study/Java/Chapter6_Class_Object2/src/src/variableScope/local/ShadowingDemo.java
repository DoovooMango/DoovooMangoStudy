package src.variableScope.local;

class ShadowingDemo {
    int number = 10; // 인스턴스 변수
    void print() {
        int number = 99; // 지역변수(섀도잉)
        System.out.println("local number = " + number);       // 99
        System.out.println("instance number = " + this.number); // 10
    }
    public static void main(String[] args) {
        new ShadowingDemo().print();
    }
}

