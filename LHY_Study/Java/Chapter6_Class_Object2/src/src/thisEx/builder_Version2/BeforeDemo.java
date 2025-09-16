package src.thisEx.builder_Version2;

/**
 * 텔레스코핑 생성자의 위험성을 콘솔 출력으로 확인하는 간단한 데모
 */
public class BeforeDemo {
    public static void main(String[] args) {
        // 의도한 올바른 순서 (major, phone)
        Student s1 = new Student(12345, "김원상", 3, "컴퓨터공학", "010-2222-3333");
        s1.showStudentInfo();

        // 순서를 바꿨지만 컴파일 에러가 나지 않음 (phone, major)
        Student s2 = new Student(12345, "김원상", 3, "010-2222-3333", "컴퓨터공학");
        s2.showStudentInfo();

        // 체이닝 setter는 편하지만, 중간 상태 노출/불변성 미보장
        Student s3 = new Student(99999, "홍길동")
                .setGrade(2)
                .setMajor("수학")
                .setPhoneNumber("02-123-4567");
        s3.showStudentInfo();
    }
}
