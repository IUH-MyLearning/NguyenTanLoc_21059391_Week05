package fit.iuh.edu.vn.backend.enums;

public enum Role {
    COMPANY(1), CANDIDATE(2), ADMIN(3);

    private int value;

    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
