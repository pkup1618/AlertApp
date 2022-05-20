package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
