package mate.academy.review.analysis.repository;

import mate.academy.review.analysis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByRole(String role);
}
