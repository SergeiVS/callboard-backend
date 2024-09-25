package org.callboard.repositories;

import org.callboard.dto.PostDto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
