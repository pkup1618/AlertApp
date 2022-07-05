package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository_custom")
public interface RoleRepository extends JpaRepository<Role, Long> {
}
