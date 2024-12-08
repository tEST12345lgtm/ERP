package com.klef.jfsd.springboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRequest {
    private String courseCode;
    private  String courseName;
    private int credits;
    private String Ltps;
    private String year;
    private  String semester;
    private int facultyId;

}

