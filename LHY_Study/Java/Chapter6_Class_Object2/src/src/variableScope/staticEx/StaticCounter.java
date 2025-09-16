package src.variableScope.staticEx;

class StaticCounter {
    private static int totalCreated = 0; // 모든 인스턴스가 공유
    public StaticCounter() { totalCreated++; }
    public static int getTotalCreated() { return totalCreated; }
    public static void main(String[] args) {
        new StaticCounter();
        new StaticCounter();
        System.out.println(StaticCounter.getTotalCreated()); // 2
    }
}

