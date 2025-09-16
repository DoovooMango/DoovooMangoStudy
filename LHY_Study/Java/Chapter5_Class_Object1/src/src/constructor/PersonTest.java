package src.constructor;

public class PersonTest {

    public static void main(String[] args) {

        // 디폴트 생성자로 클래스를 생성한 후 인스턴스 변수값을 따로 초기화
        Person personHong = new Person();        // personHong: "참조 변수"
        /// new Person(); 에서 Person() 같은 함수는 생성자

        personHong.name = "홍길동";               // name, height, weight: "인스턴스 변수"
        personHong.weight = 85.5F;
        personHong.height = 180.0F;

        /**
         * Person personHong: 참조 변수
         * → 힙(Heap)에 만들어진 Person 객체를 가리키는(참조하는) 변수예요.
         * “주소(참조값)”를 들고 있다고 이해하면 편해요.
         *
         * name, weight, height: 인스턴스 변수(필드)
         * → 그 객체 안에 저장된 데이터예요.
         * personHong.name은 Hong 객체 안의 name 칸을 가리키고,
         * personLee.name은 Lee 객체 안의 name 칸을 가리킵니다. 서로 독립적이죠.
         * */

        // 인스턴스 변수를 초기화하는 동시에 클래스 생성
        Person personLee = new Person("홍길동", 175, 75);

        // 서로 독립!
        personHong.gainWeight(5);   // Hong의 weight만 90.5F가 됨
        System.out.println(personHong.weight);
        System.out.println(personLee.weight); // 여전히 75.0
    }
}
