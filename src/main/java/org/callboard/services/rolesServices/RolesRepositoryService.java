package org.callboard.services.rolesServices;

import lombok.RequiredArgsConstructor;
import org.callboard.entities.Role;
import org.callboard.exceptions.NotFoundException;
import org.callboard.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesRepositoryService {
    private final RoleRepository roleRepository;

    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new NotFoundException(STR."Role: \{roleName} not found"));
    }
}
