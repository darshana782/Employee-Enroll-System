package com.example.assessment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.Date;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "mobile", nullable = false, length = 20)
    private String mobile;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "enrolledDate", nullable = false, length = 20)
    private String enrolledDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userType", referencedColumnName = "id", nullable = false)
    private UserType userType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch", referencedColumnName = "id", nullable = false)
    private Branch branch;

}