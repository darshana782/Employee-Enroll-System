package com.example.assessment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "salary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salary {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "month", length = 100)
    private String month;

    @Column(name = "amount", length = 100)
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userType", referencedColumnName = "id", nullable = false)
    private UserType userType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee", referencedColumnName = "id", nullable = false)
    private Employee employee;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "employee", referencedColumnName = "id", nullable = false)
//    private List<Employee> employees;



}