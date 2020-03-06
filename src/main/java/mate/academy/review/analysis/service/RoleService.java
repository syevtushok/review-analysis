package mate.academy.review.analysis.service;

import mate.academy.review.analysis.entity.Role;

public interface RoleService {
    Role add(Role role);

    Role getRole(String role);
}
