package fit.iuh.edu.vn.backend.enums;

public enum SkillLevel {
    MASTER(1), BEGINER(2), ADVANCED(3), PROFESSIONAL(4), IMTERMEDIATE(5);

    private int value;

    private SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
