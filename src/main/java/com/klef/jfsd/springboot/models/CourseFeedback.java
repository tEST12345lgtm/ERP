package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "course_id")
    private Course course;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "student_id")
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "section_id")
    private Section section;
    private String feedback;

}
