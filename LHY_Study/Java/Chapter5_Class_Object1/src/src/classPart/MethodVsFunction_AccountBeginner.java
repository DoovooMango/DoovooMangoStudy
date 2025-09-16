package src.classPart;

import java.util.function.BiFunction;

public class MethodVsFunction_AccountBeginner {

    // [1] 인스턴스 메서드: 이 객체의 잔액(balance)을 직접 변경
    static class Account {
        private double balance;
        public Account(double balance) { this.balance = balance; }
        public void withdraw(double amount) {
            if (balance < amount) {
                System.out.println("잔액이 부족합니다.");
            } else {
                balance -= amount; // ▶ 이 객체의 상태가 바뀜
            }
        }
        public double getBalance() { return balance; }
    }

    // [2] 함수(정적 유틸): 값을 받아 "새 값"을 반환(원본 값은 안 바꿈)
    static class BankFn {
        static double withdraw(double balance, double amount) {
            if (balance < amount) {
                System.out.println("잔액이 부족합니다.");
                return balance;           // ▶ 원본 유지, 새 값만 반환
            }
            return balance - amount;
        }
    }

    public static void main(String[] args) {
        line();
        demoMethod();     // 메서드

        line();
        demoFunction();   // 정적 유틸 함수

        line();
        demoLambda();     // 람다(함수 값을 변수에 담아 전달)

        line();
        System.out.println("끝! 1) 메서드(객체 버튼)  2) 함수(값→값)  3) 람다(동작을 값)만 구분하면 충분합니다 🙂");
        line();
    }

    static void line() {
        System.out.println("--------------------------------------------------");
    }

    static void demoMethod() {
        System.out.println("[1] 메서드: 객체 상태를 직접 바꿈");
        Account acc = new Account(10_000);
        acc.withdraw(3_000); // 이 객체(acc)의 balance가 7000으로 변경
        System.out.println("acc.getBalance() = " + acc.getBalance()); // 7000
        acc.withdraw(8_000); // 부족 → 잔액 그대로
        System.out.println("acc.getBalance() = " + acc.getBalance()); // 7000
        System.out.println("→ 메서드는 'acc.withdraw()'처럼 특정 객체에 소속된 동작입니다.");
    }

    static void demoFunction() {
        System.out.println("[2] 함수(정적 유틸): 입력값을 받아 새 값 반환");
        double bal = 10_000;
        double newBal = BankFn.withdraw(bal, 3_000); // 원본 bal은 그대로
        System.out.println("원본 bal = " + bal);       // 10000
        System.out.println("계산 결과 newBal = " + newBal); // 7000
        System.out.println("→ 함수는 값을 바꾸지 않고 '계산 결과'만 돌려줍니다.");
    }

    static void demoLambda() {
        System.out.println("[3] 함수(람다): 동작을 값으로 변수에 담아 전달");
        // BiFunction<T,U,R>: (T,U) -> R
        BiFunction<Double,Double,Double> withdraw =
                (balance, amount) -> balance < amount ? balance : balance - amount;

        double result = withdraw.apply(10_000.0, 3_000.0);
        System.out.println("withdraw.apply(10000, 3000) = " + result); // 7000.0
        System.out.println("→ 람다는 메서드처럼 보이지만, 이렇게 변수에 담아 전달/조합할 수 있어요.");
    }
}
