package com.klef.jfsd.springboot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private Section section;
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;
}
