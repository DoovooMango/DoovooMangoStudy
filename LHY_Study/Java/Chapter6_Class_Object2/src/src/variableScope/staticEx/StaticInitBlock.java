package src.variableScope.staticEx;

class StaticInitBlock {
    static int value;
    static {
        System.out.println("static block executed");
        value = 42;
    }
    public static void main(String[] args) {
        System.out.println("value = " + value);
    }
}

