package src.variableScope.instance;

class InstanceVsParam {
    int x = 5;
    void setX(int x) { // 파라미터 x가 섀도잉
        this.x = x;    // this로 인스턴스 변수에 대입
    }
    public static void main(String[] args) {
        InstanceVsParam obj = new InstanceVsParam();
        obj.setX(100);
        System.out.println(obj.x); // 100
    }
}

