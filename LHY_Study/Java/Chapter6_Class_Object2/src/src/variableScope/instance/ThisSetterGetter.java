package src.variableScope.instance;

class ThisSetterGetter {
    private int value; // 캡슐화
    public int getValue() { return value; }
    public void setValue(int value) { // 파라미터와 필드 이름 동일 → this 사용
        this.value = value;
    }
    public static void main(String[] args) {
        ThisSetterGetter obj = new ThisSetterGetter();
        obj.setValue(42);
        System.out.println(obj.getValue());
    }
}

