package dto;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Role findRole(String role) {
        return Role.valueOf(role);
    }
}
