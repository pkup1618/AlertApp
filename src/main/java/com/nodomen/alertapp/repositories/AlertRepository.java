package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("alertRepository_custom")
public interface AlertRepository extends JpaRepository<Alert, Long> {
}

