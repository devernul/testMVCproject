package edu.devernul.project.model;


import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID", nullable = false)
    private Integer taskId;


    @Column(name = "NAME", nullable = false)
    @Size(min = 3, message = "{name.size.error}")
    private String name;

    @Column(name = "DESCRIPTION", nullable = false,length = 1000)
    @Size(min = 3,max =1000, message = "{description.size.error}")

    private String description;

    @Column(name = "DATE", nullable = false)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @Past
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_IS", nullable = false)
    private Status status;




    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
