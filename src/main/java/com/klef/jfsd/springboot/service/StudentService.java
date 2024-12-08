package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.models.FeePayments;
import com.klef.jfsd.springboot.models.Student;
import com.klef.jfsd.springboot.models.StudentCourseMapping;

import java.util.List;

public interface StudentService {

    public Student checkStudentLogin(String username, String password);
    public void addStudentCourseMapping(StudentCourseMapping studentCourseMapping);
    public List<StudentCourseMapping> viewStudentCourseMapping(String studentId);

    public List<FeePayments> viewFeePayments(int studentId);
    public void payFeePayments(FeePayments feePayments);
}
