package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sectionNo;
    private int capacity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id",referencedColumnName = "id")
    private Faculty faculty;

    private LocalDateTime createdAt = LocalDateTime.now();
}
