package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository_custom")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
