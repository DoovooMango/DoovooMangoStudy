package src.variableScope.instance;

class ObjectCountWrong {
    // 잘못된 예: 인스턴스마다 따로 존재하므로 전체 개수 카운터로 부적절
    int count = 0;
    public ObjectCountWrong() { count++; }
    public static void main(String[] args) {
        ObjectCountWrong a = new ObjectCountWrong();
        ObjectCountWrong b = new ObjectCountWrong();
        System.out.println("a.count=" + a.count); // 1
        System.out.println("b.count=" + b.count); // 1 (원했던 2가 아님)
        System.out.println("(올바른 방식은 static 필드를 사용)");
    }
}

