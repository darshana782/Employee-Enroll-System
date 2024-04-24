package com.example.assessment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branch")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Branch {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String branchName;
}