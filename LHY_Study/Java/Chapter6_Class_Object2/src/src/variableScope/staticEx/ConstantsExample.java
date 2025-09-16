package src.variableScope.staticEx;

class ConstantsExample {
    public static final double TAX_RATE = 0.1; // 상수
    public static final String APP_TITLE = "MY_APP";
    public static void main(String[] args) {
        System.out.println(APP_TITLE + " / TAX=" + TAX_RATE);
        // TAX_RATE = 0.2; // 컴파일 오류: final
    }
}

