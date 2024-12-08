package com.klef.jfsd.springboot.controller;

import com.klef.jfsd.springboot.DTO.FeePaymentRequest;
import com.klef.jfsd.springboot.DTO.RegisterCourse;
import com.klef.jfsd.springboot.models.FeePayments;
import com.klef.jfsd.springboot.models.Student;
import com.klef.jfsd.springboot.models.StudentCourseMapping;
import com.klef.jfsd.springboot.repository.StudentRepository;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;

    @PostMapping("checkStudentLogin")
    public ResponseEntity<?> checkStudentLogin(@RequestBody Student student) {
       Student s= studentService.checkStudentLogin(student.getStudentId(), student.getPassword());
       if(s!=null){
           return  ResponseEntity.ok(s);
       }
       return  ResponseEntity.notFound().build();
    }

    @PostMapping("studentRegister")
    public ResponseEntity<String> studentRegister(@RequestBody RegisterCourse registerCourse) {
        Student student = adminService.viewStudentById(registerCourse.getStudentId());
        student.setRegistered(true);
        studentRepository.save(student);
        List<Integer> courses = registerCourse.getCourseId();
        List<Integer> sections = registerCourse.getSectionId();
        for (int i=0; i<courses.size();i++) {
            int courseId = courses.get(i);
            int sectionId = sections.get(i);
            StudentCourseMapping studentCourseMapping = new StudentCourseMapping();
            studentCourseMapping.setStudent(student);
            studentCourseMapping.setCourse(adminService.viewCourseById(courseId));
            studentCourseMapping.setSection(adminService.viewSectionById(sectionId));

            studentService.addStudentCourseMapping(studentCourseMapping);
        }
        return ResponseEntity.ok("Success");
    }

    @GetMapping("viewAllRegisteredCourses")
    public ResponseEntity<?> viewAllRegisteredCourses(@RequestParam String studentId){
       List<StudentCourseMapping> mappings= studentService.viewStudentCourseMapping(studentId);
       if(!mappings.isEmpty()){
           return ResponseEntity.ok(mappings);
       }
       return ResponseEntity.notFound().build();

    }


    @GetMapping("viewStudentById")
    public ResponseEntity<?> viewStudentById(@RequestParam String studentId) {
        Student student = adminService.viewStudentById(studentId);
        if(student!=null){
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("payFee")
    public ResponseEntity<?> payFee(@RequestBody FeePaymentRequest request) {

        FeePayments feePayments = new FeePayments();
        feePayments.setFeeType(request.getFeeType());
        feePayments.setAmount(request.getAmount());
        feePayments.setTransactionId(request.getTransactionId());
        feePayments.setStudent(studentRepository.findById(request.getStudentId()).orElse(null));
        feePayments.setPaymentStatus(request.getPaymentStatus());
        studentService.payFeePayments(feePayments);
        return  ResponseEntity.ok("Fee Payment Success");
    }

    @GetMapping("viewAllPayments")
    public ResponseEntity<?> viewPayments(@RequestParam int studentId) {
        List<FeePayments> payments = studentService.viewFeePayments(studentId);
        if(!payments.isEmpty()){
            return ResponseEntity.ok(payments);
        }
        return ResponseEntity.notFound().build();
    }
}
