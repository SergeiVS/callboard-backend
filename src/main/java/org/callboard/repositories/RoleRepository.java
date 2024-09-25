package org.callboard.repositories;

import org.callboard.dto.PostDto.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
