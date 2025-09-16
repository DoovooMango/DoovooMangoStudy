package src.thisEx;

///  생성자에서 다른 생성자를 호출하는 this
class Person{
    String name;
    int age;

    ///  Person() 디폴트 생성자
    // no-arg 생성자(직접 정의한 매개변수 없는 생성자)
    Person(){
        this("이름 없음", 1);               // Person(String, int) 생성자 호출
                                                    // 다른 생성자에 위임(중복 제거)
        System.out.println("no-arg 생성자 마무리 코드 실행");
    }

    /**
     * version 1 설명
     * this 로 다른 생성자를 호출할 때 주의할 점
     * Person(){
     *         this.name = "noname";
     *         this("이름 없음", 1);               // Person(String, int) 생성자 호출
     *     }
     *
     * this 를 사용하여 생성자를 호출하는 코드 이전에는 다른 코드를 넣을 수 없다.
     * 오류문 Call to 'this()' must be first statement in constructor body
     * 생성자는 클래스가 생성될 때 호출되므로 클래스 생성이 완료하지 않은 시점에 다른 코드가 있다면 오류가 발생.
     * 즉 디폴트 생성자에서 생성이 완료되는 것이 아니라 this를 사용해 다른 생성자를 호출하므로,
     * 이 때는 this를 활용한 문장이 가장 먼저 와야한다.
     * */

    //생성자는 클래스가 생성될 때 호출되므로 클래스 생성이 완료하지 않은 시점에 다른 코드가 있다면 오류가 발생.
    // 보완 설명
    // 정확히는 언어 규칙 때문입니다.
    // 객체 초기화 순서를 일관되게 강제하려고 “this()/super() 첫 문장” 규칙이 있는 거예요
    // “완료 전 코드라서”라기보다는 “규칙상 금지”.

    /**
     * version 2 설명
     * this(...)로 다른 생성자를 호출할 때 주의할 점
     *
     * Person(){
     *     // this(...)는 생성자 몸통의 '첫 번째 문장'이어야 함
     *     // this.name = "noname";  // (컴파일 에러)
     *     this("이름 없음", 1);     // Person(String, int) 생성자 호출
     *     // <-- 여기 이후에는 일반 코드 작성 가능
     * }
     *
     * 오류 메시지 예: Call to 'this()' must be first statement in constructor body
     *
     * 왜? 자바는 '생성자 체이닝'의 정확한 초기화 순서를 보장하기 위해
     * this(...) 또는 super(...)가 반드시 첫 문장이어야 한다고 규정함.
     * no-arg 생성자가 직접 초기화 코드를 반복하기보다
     * 이미 작성된 다른 생성자에 위임하면(중복 제거) 안전하고 간결함.
     * */

    /**
     * 핵심 정리
     *
     * this(...)는 반드시 생성자 몸통의 첫 번째 문장이어야 합니다.
     * → 그 이전엔 어떤 코드도 둘 수 없지만, 이후에는 일반 코드가 올 수 있어요.
     *
     * 하나의 생성자에서는 this(...)와 super(...)를 동시에 호출할 수 없습니다.
     * → 둘 중 하나만 첫 문장으로 가능. (둘 다 없으면 컴파일러가 super()를 암묵적으로 넣음)
     *
     * this(...)로 순환 호출이 생기면 컴파일 에러(recursive constructor invocation)가 납니다.
     * → 체인의 끝은 결국 super(...)(암묵적 포함 가능)로 끝나야 해요.
     *
     * 용어 정리:
     * 기본 생성자(default constructor) = 컴파일러가 자동으로 만들어주는 매개변수 없는 생성자(클래스에 어떤 생성자도 없을 때만).
     *
     * 매개변수 없는 생성자(no-arg constructor) = 개발자가 직접 작성한 Person(){...} 포함.
     * → 지금 코드의 Person()은 기본 생성자가 아니라 직접 정의한 no-arg 생성자예요.
     *
     * return this;는 생성자 안에서는 불가(생성자는 반환형이 없고 return 값 사용 불가).
     * → 대신 일반 인스턴스 메서드(returnItSelf)에서는 this를 반환할 수 있어요. 이건 자기 자신 참조를 돌려주는 것.
     * */

    /**
     * 추가 팁
     *
     * 생성자 체이닝을 쓰면 중복 초기화 코드를 없앨 수 있어 유지보수에 유리합니다.
     *
     * 필드를 private으로 두고, 필요한 경우 생성자 검증(null/음수 방지 등)과 게터를 제공하는 것이 캡슐화에 좋아요.
     *
     * 디버깅을 위해 toString()을 오버라이드하면 출력/로그가 훨씬 읽기 쉬워집니다.
     * */

    /// 매개변수가 포함된 Person(String, int) 생성자
    Person(String name, int age){
        // super(); // 컴파일러가 자동 삽입(명시하지 않아도 됨)
        this.name = name;
        this.age = age;
    }
    // 클래스가 생성될 때 Person(String, int)가 호출되어 이름과 나이를 전달받고, Person() 디폴트가 생성자가 호출되는 경우에는 초기값으로
    // "이름 없음"과 1 값을 대입하고자 한다. 물론 디폴트 생성자 코드 안에서 직접써도 되지만, 이미 다른 생성자에 이 코드가 작성되어 있으므로
    // 9행 처럼 this를 활용하여 다른 생성자를 호출할 수 있다.

    Person returnItSelf() {
        return this;            // this 자기 자신 참조 반환
    }
    // Person 반환형은 클래스형

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    // 필요시 getter
    public String getName() { return name; }
    public int getAge() { return age; }
}

public class CallAnotherConst {

    public static void main(String[] args) {

        Person noName = new Person();
        System.out.println(noName.name);            // 출력 결과 : 이름 없음
        System.out.println(noName.age);             // 출력 결과 : 1

        // toString() 오버라이드 했으므로 사람이 읽기 쉬움
        System.out.println(noName);           // Person{name='이름 없음', age=1}

		/*Person p = noName.returnItSelf();         // this 값을 참조 변수에 대입
		System.out.println(p);                      // noName.returnItSelf() 의 반환값 출력
		System.out.println(noName);*/               // 참조변수 출력 // true (동일 객체 참조)
    }
}
