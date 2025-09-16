package src.variableScope.local;

import java.util.concurrent.Callable;

class LambdaCapture {
    public static void main(String[] args) throws Exception {
        int base = 10; // 지역변수는 람다에서 '사실상 final' 이어야 함
        Callable<Integer> c = () -> {
            // base++; // 컴파일 오류: 변경 불가
            return base * 2;
        };
        System.out.println(c.call()); // 20
    }
}
