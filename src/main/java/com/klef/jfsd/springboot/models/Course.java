package com.klef.jfsd.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
      private String courseCode;
      private String courseName;
    private int credits;
      private String LTPS;
      private String year;
      private String semester;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"email", "phone", "address", "department", "gender", "birthDate", "password", "image", "createdAt"})
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty cc;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String handout;

    private LocalDateTime createdAt = LocalDateTime.now();
}
