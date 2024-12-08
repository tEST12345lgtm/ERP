package com.klef.jfsd.springboot.controller;

import com.klef.jfsd.springboot.DTO.LoginRequest;
import com.klef.jfsd.springboot.models.Admin;
import com.klef.jfsd.springboot.models.Faculty;
import com.klef.jfsd.springboot.models.Student;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.FacultyService;
import com.klef.jfsd.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ERPController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private FacultyService facultyService;
    @PostMapping("checkLogin")
    public ResponseEntity<?> checkLogin(@RequestBody LoginRequest loginRequest) {
        Admin admin = adminService.checkAdmin(loginRequest.getUsername(), loginRequest.getPassword());
        Student student = studentService.checkStudentLogin(loginRequest.getUsername(), loginRequest.getPassword());
        Faculty faculty = facultyService.checkFacultyLogin(loginRequest.getUsername(), loginRequest.getPassword());
        if (admin != null) {
            return ResponseEntity.ok(Map.of("role", "admin", "data", admin));
        }
        if (student != null) {
            return ResponseEntity.ok(Map.of("role", "student", "data", student));
        }
        if (faculty != null) {
            return ResponseEntity.ok(Map.of("role", "faculty", "data", faculty));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

}
