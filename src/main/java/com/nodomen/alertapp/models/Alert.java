package com.nodomen.alertapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "alert")
public class Alert {

    @Id
    public Long id;
    public String text;
    public Date creationTime;

    public Alert() {

    }

    public Alert(Long id, String text, Date creationTime) {
        this.id = id;
        this.text = text;
        this.creationTime = creationTime;
    }
}
