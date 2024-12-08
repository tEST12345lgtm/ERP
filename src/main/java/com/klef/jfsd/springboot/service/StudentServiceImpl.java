package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.models.FeePayments;
import com.klef.jfsd.springboot.models.Student;
import com.klef.jfsd.springboot.models.StudentCourseMapping;
import com.klef.jfsd.springboot.repository.FeePaymentsRepository;
import com.klef.jfsd.springboot.repository.StudentCourseMappingRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService{
    @Autowired
    private StudentCourseMappingRepository studentCourseMappingRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FeePaymentsRepository feePaymentsRepository;

    @Override
    public Student checkStudentLogin(String username, String password) {
        Student student = studentRepository.findByStudentIdOrEmail(username, username);
        if (student == null) return null;
        return student.getPassword().equals(password) ? student : null;
    }

    @Override
    public void addStudentCourseMapping(StudentCourseMapping studentCourseMapping) {
        studentCourseMappingRepository.save(studentCourseMapping);
    }

    @Override
    public List<StudentCourseMapping> viewStudentCourseMapping(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        return studentCourseMappingRepository.findByStudent(student);
    }

    @Override
    public List<FeePayments> viewFeePayments(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return feePaymentsRepository.findByStudent(student);
    }

    @Override
    public void payFeePayments(FeePayments feePayments) {
        feePaymentsRepository.save(feePayments);
    }
}
