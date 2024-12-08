package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class FacultyLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(columnDefinition = "faculty_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Faculty faculty;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status="applied";

    private LocalDateTime createdAt= LocalDateTime.now();
}
