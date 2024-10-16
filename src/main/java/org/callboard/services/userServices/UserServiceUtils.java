package org.callboard.services.userServices;

import org.callboard.entities.Role;
import org.callboard.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceUtils {
    public static List<String> getUserRoles(User user) {
        return user.getRoles().stream().map(Role::getRoleName).toList();
    }
}
