package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String studentId;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String gender;

    private LocalDate birthDate;

    private String password;

    @Lob
    private String image;
    private boolean approved;
    private boolean registered;
    private LocalDateTime createdAt;
    public Student() {
        this.password = "klu@123";
        this.createdAt = LocalDateTime.now();
    }
}
