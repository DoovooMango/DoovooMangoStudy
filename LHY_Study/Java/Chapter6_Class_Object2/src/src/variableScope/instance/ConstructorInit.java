package src.variableScope.instance;

class ConstructorInit {
    private final String id;
    private int level; // 기본값 0
    public ConstructorInit(String id) {
        this.id = id; // final 필드는 생성자에서 초기화
        this.level = 1;
    }
    public String toString() { return "id=" + id + ", level=" + level; }
    public static void main(String[] args) {
        System.out.println(new ConstructorInit("A1"));
    }
}

