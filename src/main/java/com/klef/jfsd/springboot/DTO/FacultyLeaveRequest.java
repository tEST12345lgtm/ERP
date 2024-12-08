package com.klef.jfsd.springboot.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyLeaveRequest {
    private int facultyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
