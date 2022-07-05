package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.Alert;
import com.nodomen.alertapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("userRepository_custom")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
