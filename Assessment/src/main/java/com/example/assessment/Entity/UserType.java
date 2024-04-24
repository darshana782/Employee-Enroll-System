package com.example.assessment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userType")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "type", nullable = false, length = 100)
    private String type;
}