package com.klef.jfsd.springboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSectionRequest {
    int courseId;
    private String sectionNo;
    private int capacity;
    private int facultyId;
}
