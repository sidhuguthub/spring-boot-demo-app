package com.department.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_head")
    private String departmentHead;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "status")
    private String status;

}
