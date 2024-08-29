package com.loc.identity.constant;

import com.loc.identity.entity.Role;

public class PredefinedRoles {
    public static Role ADMIN = new Role("ADMIN", "Admin role", null);
    public static Role USER = new Role("USER", "User role", null);

    private PredefinedRoles() {}
}
