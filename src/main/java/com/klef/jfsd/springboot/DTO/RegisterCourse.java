package com.klef.jfsd.springboot.DTO;


import com.klef.jfsd.springboot.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCourse {

    private String studentId;
    private List<Integer> courseId;  // 1 ,2
    private List<Integer> sectionId;//
}
