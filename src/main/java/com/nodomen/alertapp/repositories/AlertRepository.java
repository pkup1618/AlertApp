package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlertRepository extends JpaRepository<Alert, Long> {
}

