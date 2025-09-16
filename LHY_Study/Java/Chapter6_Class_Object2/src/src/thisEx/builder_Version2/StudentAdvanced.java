package src.thisEx.builder_Version2;

import java.util.Objects;

/**
 * 빌더 패턴 적용 후 - 불변 객체 + 선택적 파라미터 + 최종 검증
 */
public final class StudentAdvanced {
    private final int id;              // 필수
    private final String name;         // 필수
    private final Integer grade;       // 선택
    private final String major;        // 선택(기본값 "미정")
    private final String phoneNumber;  // 선택(간단 형식검증 예시)

    private StudentAdvanced(Builder b) {
        this.id = b.id;
        this.name = b.name;
        this.grade = b.grade;
        this.major = b.major;
        this.phoneNumber = b.phoneNumber;
    }

    public static Builder builder(int id, String name) {
        return new Builder(id, name);
    }

    public static final class Builder {
        private final int id;
        private final String name;

        private Integer grade;
        private String major = "미정";
        private String phoneNumber;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder grade(int grade) {
            if (grade < 1 || grade > 12) {
                throw new IllegalArgumentException("grade는 1~12 사이여야 합니다.");
            }
            this.grade = grade;
            return this;
        }

        public Builder major(String major) {
            this.major = Objects.requireNonNullElse(major, "미정");
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            if (phoneNumber != null && !phoneNumber.matches("\\d{2,3}-\\d{3,4}-\\d{4}")) {
                throw new IllegalArgumentException("전화번호 형식이 올바르지 않습니다. 예) 010-1234-5678");
            }
            this.phoneNumber = phoneNumber;
            return this;
        }

        public StudentAdvanced build() {
            if (id <= 0) throw new IllegalArgumentException("id는 양수여야 합니다.");
            if (name == null || name.isBlank()) throw new IllegalArgumentException("name은 필수입니다.");
            return new StudentAdvanced(this);
        }
    }

    @Override
    public String toString() {
        return "StudentAdvanced{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Integer getGrade() { return grade; }
    public String getMajor() { return major; }
    public String getPhoneNumber() { return phoneNumber; }

    public static void main(String[] args) {
        StudentAdvanced s = StudentAdvanced.builder(1, "John Doe")
                .grade(12)
                .major("Computer Science")
                .phoneNumber("010-2222-3333")
                .build();
        System.out.println(s);

        // 순서가 바뀌어도 의미가 메서드 이름으로 명확
        StudentAdvanced s2 = StudentAdvanced.builder(2, "김원상")
                .phoneNumber("02-123-4567")
                .major("컴퓨터공학")
                .build();
        System.out.println(s2);
    }
}
