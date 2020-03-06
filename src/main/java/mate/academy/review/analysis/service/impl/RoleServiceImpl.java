package mate.academy.review.analysis.service.impl;

import mate.academy.review.analysis.entity.Role;
import mate.academy.review.analysis.repository.RoleRepository;
import mate.academy.review.analysis.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.getByRole(role);
    }
}
