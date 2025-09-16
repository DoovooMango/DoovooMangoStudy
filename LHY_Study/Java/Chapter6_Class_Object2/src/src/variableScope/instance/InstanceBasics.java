package src.variableScope.instance;

class InstanceBasics {
    String name; // 인스턴스 변수(기본값 null)
    int age;     // 기본값 0
    public InstanceBasics(String name, int age) {
        this.name = name;
        this.age = age;
    }
    void introduce() {
        System.out.printf("I'm %s, %d years old.%n", name, age);
    }
    public static void main(String[] args) {
        InstanceBasics a = new InstanceBasics("Kim", 20);
        InstanceBasics b = new InstanceBasics("Lee", 25);
        a.introduce();
        b.introduce();
    }
}

