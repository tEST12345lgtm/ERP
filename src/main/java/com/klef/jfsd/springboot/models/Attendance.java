package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    private LocalDate attendanceDate; // The date of attendance

    private Boolean isPresent;
}
