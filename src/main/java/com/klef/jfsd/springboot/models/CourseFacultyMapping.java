package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseFacultyMapping {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "faculty_id")
    private Faculty faculty;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "course_id")
    private Course course;
}
