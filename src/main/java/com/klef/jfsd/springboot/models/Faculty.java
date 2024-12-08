package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String department;
    private String gender;
    private LocalDate birthDate;
    private String password="klu@123";
    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String image;
    private LocalDateTime createdAt=LocalDateTime.now();
}
