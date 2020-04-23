package com.epam.semeniuk.enums;

import java.util.Arrays;

public enum Role {
    ADMIN("admin", 1), USER("user", 2);

    private final String role;
    private final int id;

    Role(String role, int id) {
        this.role = role;
        this.id = id;
    }

    public static Role getRole(int id) {
        return Arrays.stream(Role.values()).filter(i -> i.id == id).findFirst().orElse(null);
    }

    public int getRoleId() {
        return this.id;
    }

    public String getName() {
        return this.role;
    }
}
