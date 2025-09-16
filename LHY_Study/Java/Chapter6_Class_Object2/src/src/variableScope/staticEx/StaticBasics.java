package src.variableScope.staticEx;

class StaticBasics {
    static String appName = "DemoApp"; // 클래스 변수
    static void printApp() {
        System.out.println(appName);
    }
    public static void main(String[] args) {
        StaticBasics.printApp();
        StaticBasics.appName = "DemoApp v2";
        new StaticBasics().printApp(); // 인스턴스로도 호출 가능하지만 권장 X
    }
}

