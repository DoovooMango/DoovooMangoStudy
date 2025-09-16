package src.thisEx;

///  자신의 메모리를 가리키는 this
class BirthDay{

    int day;
    int month;
    int year;

    /// 태어난 연도를지정하는 메서드
    public void setYear(int year) {
        this.year = year;          // bDay.year = year; 와 같음
    }
    // setYear(int year)
    //매개변수 year와 필드 year의 이름이 같아서,
    //this.year = year;
    //왼쪽 this.year는 필드, 오른쪽 year는 매개변수를 뜻합니다.
    //만약 this를 빼고 year = year;라 쓰면 매개변수끼리 대입이라 아무 일도 안 일어나요.

    /**
     * this는 “지금 이 코드(인스턴스 메서드)를 실행 중인 그 객체”를 가리키는 참조예요.
     * 그래서 System.out.println(bDay);와 System.out.println(this);는 같은 객체를 찍게 됩니다.
     * */

    ///  this 출력 메서드
    public void printThis() {
        System.out.println(this);           // this.toString()과 동일
                                            // System.out.printLn(bDay); 와 같음
    }

    @Override
    public String toString() {
        // 사람 눈에 더 쉬운 출력 + identityHashCode로 개체 식별
        return "BirthDay{year=" + year + ", month=" + month + ", day=" + day +
                ", id=" + System.identityHashCode(this) + "}";
    }
}

public class ThisExample {

    public static void main(String[] args) {

        BirthDay bDay = new BirthDay();     // 힙에 BirthDay 객체 하나 생성, bDay가 그 객체를 참조
        bDay.setYear(2000);                 // 태어난 연도를 2000으로지정
        System.out.println(bDay);           // 참조변수 출력
        bDay.printThis();                   // this 출력 메서드 호출, 내부에서 System.out.println(this);

        BirthDay b1 = new BirthDay();
        b1.setYear(2000);

        System.out.println(b1);             // ex) BirthDay{year=2000, month=0, day=0, id=460141958}
        b1.printThis();                     // 위와 동일한 줄이 한 번 더 출력

        BirthDay b2 = new BirthDay();
        b2.setYear(2001);
        System.out.println(b2);             // id가 b1과 다르게 나옴 (다른 객체니까)
    }
}

/**
 * this가 가리키는 것
 * 의미: this는 “현재 메서드를 실행하고 있는 그 객체 자신”을 가리키는 참조(레퍼런스) 입니다.
 * 메모리 주소 숫자 자체가 아니라, 그 객체를 가리키는 참조값이라고 이해하면 정확해요.
 * 타입: 인스턴스 메서드 안에서 this의 타입은 그 클래스 타입입니다. (여기서는 BirthDay)
 *
 *
 * “메모리를 가리킨다”의 정확한 해석
 * 흔히 “자신의 메모리를 가리킨다”고 말하지만 엄밀히는 현재 객체의 참조예요.
 * Object.toString()의 @16진수 값은 메모리 주소 그 자체가 아니라 hashCode()를 16진수로 바꾼 값이며, 기본 구현은 객체의 아이덴티티를 기준으로 계산됩니다. (즉, 주소랑 같다고 단정하면 X)
 * */

/**
 * System.out.println(bDay);
 * 객체를 println에 넘기면 자동으로 bDay.toString()을 호출합니다.
 * BirthDay가 toString()을 오버라이드하지 않았으므로 Object.toString() 결과인
 * 클래스이름@해시코드(16진수) 형태가 찍혀요. 예: BirthDay@6d06d69c
 * */

/**
 * bDay.printThis();
 * 메서드 안에서 System.out.println(this); → 결국 그 객체 자신을 출력.
 * 그래서 ②의 출력은 ①과 완전히 동일합니다.
 * (둘 다 같은 객체 참조를 println한 것이니까요.)
 *
 *
 * 정리: bDay(참조변수)와 this(현재 객체 참조)는 같은 객체를 가리키는 상황이라 출력이 같게 나옵니다.
 * */
