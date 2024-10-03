package org.callboard.services.rolesServices;

import lombok.RequiredArgsConstructor;
import org.callboard.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesRepositoryService {
    private final RoleRepository roleRepository;
}
