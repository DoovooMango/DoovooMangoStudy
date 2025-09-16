package src.hiding;

public class MyDate {
    // 정보 개방
    public int day;
    public int month;
    public int year;

    // 정보 은닉
//    private int day;
//    private int month;
//    private int year;

    public void setDay(int day) {
        if (month== 2) {
            if (day < 1 || day > 31) {
                System.out.println("오류입니다.");
            } else {
                this.day = day;
            }
        }
    }
}
