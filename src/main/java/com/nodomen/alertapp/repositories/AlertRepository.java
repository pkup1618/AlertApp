package com.nodomen.alertapp.repositories;

import com.nodomen.alertapp.models.Alert;
import com.nodomen.alertapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("alertRepository_custom")
public interface AlertRepository extends JpaRepository<Alert, Long> {

    Collection<Alert> getAllByUser(User user);

}

