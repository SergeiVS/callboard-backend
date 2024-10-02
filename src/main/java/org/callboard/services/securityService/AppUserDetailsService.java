package org.callboard.services.securityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.callboard.exceptions.NotFoundException;
import org.callboard.services.userServices.UserRepositoryService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

private final UserRepositoryService userRepositoryService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       log.info(STR."Loading user by email: \{email}");
UserDetails details = userRepositoryService.loadUserByEmailForAuth(email);
log.info(STR."Details: \{details}");
        return details;
    }
}
