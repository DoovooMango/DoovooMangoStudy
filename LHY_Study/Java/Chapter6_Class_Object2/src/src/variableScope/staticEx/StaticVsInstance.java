package src.variableScope.staticEx;

class StaticVsInstance {
    private static int shared = 0;
    private int own = 0;
    public StaticVsInstance() { shared++; own++; }
    public String toString() { return "shared=" + shared + ", own=" + own; }
    public static void main(String[] args) {
        StaticVsInstance a = new StaticVsInstance();
        StaticVsInstance b = new StaticVsInstance();
        System.out.println(a); // shared=2, own=1
        System.out.println(b); // shared=2, own=1
        // System.out.println(own); // 컴파일 오류: static 컨텍스트에서 인스턴스 필드 접근 불가
    }
}

