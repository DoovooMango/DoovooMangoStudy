package src.variableScope.local;

class ForLoopCounter {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) { // i는 for 블록의 지역변수
            System.out.println("i = " + i);
        }
        // System.out.println(i); // 컴파일 오류: i는 for 블록 밖에서 접근 불가
    }
}

