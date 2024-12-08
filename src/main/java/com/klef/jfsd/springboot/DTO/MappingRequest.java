package com.klef.jfsd.springboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappingRequest {
    private int facultyId;
    private int courseId;
    private String section;
}
