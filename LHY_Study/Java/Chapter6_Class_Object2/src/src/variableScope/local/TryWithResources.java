package src.variableScope.local;

class TryWithResources {
    static class SimpleResource implements AutoCloseable {
        private final String name;
        SimpleResource(String name) { this.name = name; }
        @Override public void close() { System.out.println(name + " closed"); }
        void work() { System.out.println(name + " working"); }
    }
    public static void main(String[] args) {
        // r은 try-with-resources 블록 내 지역변수
        try (SimpleResource r = new SimpleResource("R1")) {
            r.work();
        }
        // System.out.println(r); // 컴파일 오류
    }
}

