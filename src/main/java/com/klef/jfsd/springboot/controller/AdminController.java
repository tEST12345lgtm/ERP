package com.klef.jfsd.springboot.controller;

import com.klef.jfsd.springboot.DTO.CourseRequest;
import com.klef.jfsd.springboot.DTO.CourseSectionRequest;
import com.klef.jfsd.springboot.models.*;
import com.klef.jfsd.springboot.DTO.MappingRequest;
import com.klef.jfsd.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
   private AdminService adminService;

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        admin.setUsername(username);
        admin.setPassword(password);

        if (image != null && !image.isEmpty()) {
            String fileType = image.getContentType();
            byte[] fileContent = image.getBytes();
            String imageData = "data:" + fileType + ";base64," + Base64.getEncoder().encodeToString(fileContent);
            admin.setImage(imageData);
        }

        Admin createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }
    @PostMapping("checkLogin")
    public ResponseEntity<?> checkAdminLogin(@RequestBody Admin admin) {
        Admin a = adminService.checkAdmin(admin.getUsername(), admin.getPassword());
        if (a == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin Not Found");
        return ResponseEntity.status(HttpStatus.OK).body(a);
    }

    @PostMapping("/addstudent")
    public ResponseEntity<?> createStudent(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("gender") String gender,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("image") MultipartFile image) {

        try {
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setAddress(address);
            student.setGender(gender);
            student.setBirthDate(LocalDate.parse(birthDate));

            if (image != null && !image.isEmpty()) {
                String fileType = image.getContentType();
                byte[] fileContent = image.getBytes();
                String base64Image = "data:" + fileType + ";base64," + Base64.getEncoder().encodeToString(fileContent);
                student.setImage(base64Image);
            }

            student.setStudentId(adminService.generateStudentId());
            Student savedStudent = adminService.addStudent(student);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while creating the student: " + e.getMessage());
        }
    }


    @GetMapping("viewAllStudents")
    public ResponseEntity<?> viewAllStudents() {
        List<Student> students = adminService.viewAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Students Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("viewStudentById")
    public ResponseEntity<?> viewStudentById(@RequestParam("studentId") String studentId) {
        Student student = adminService.viewStudentById(studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping("/addFaculty")
    public ResponseEntity<?> addFaculty(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("department") String department,
            @RequestParam("address") String address,
            @RequestParam("gender") String gender,
            @RequestParam("birthDate") String birthDate,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setEmail(email);
            faculty.setPhone(phone);
            faculty.setAddress(address);
            faculty.setDepartment(department);
            faculty.setGender(gender);
            faculty.setBirthDate(LocalDate.parse(birthDate));

            if (image != null && !image.isEmpty()) {
                String fileType = image.getContentType();
                byte[] fileContent = image.getBytes();
                String base64Image = "data:" + fileType + ";base64," + Base64.getEncoder().encodeToString(fileContent);
                faculty.setImage(base64Image);
            }

            Faculty createdFaculty = adminService.addFaculty(faculty);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFaculty);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while adding faculty: " + e.getMessage());
        }
    }

    @GetMapping("viewAllFaculty")
    public ResponseEntity<?> viewAllFaculty() {
        List<Faculty> faculties = adminService.viewAllFaculty();
        if (faculties.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Faculty Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(faculties);
    }

    @GetMapping("viewFacultyById")
    public ResponseEntity<?> viewFacultyById(@RequestParam("facultyId") Integer facultyId) {
        Faculty faculty = adminService.viewFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(faculty);
    }

    @PostMapping("addCourse")
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest  courseRequest) {
        Course course = new Course();
        course.setCourseCode(courseRequest.getCourseCode());
        course.setCourseName(courseRequest.getCourseName());
        course.setLTPS(courseRequest.getLtps());
        course.setCredits(courseRequest.getCredits());
        course.setSemester(courseRequest.getSemester());
        course.setCc(adminService.viewFacultyById(courseRequest.getFacultyId()));
        course.setYear(courseRequest.getYear());
        Course Savedcourse = adminService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(Savedcourse);
    }

    @GetMapping("viewAllCourses")
    public ResponseEntity<?> viewAllCourses() {
        List<Course> courses = adminService.viewAllCourse();
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Courses Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @GetMapping("viewCourseById")
    public ResponseEntity<?> viewCourseById(@RequestParam("courseId") Integer courseId) {
        Course course = adminService.viewCourseById(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @PostMapping("addCourseFacultyMapping")
    public ResponseEntity<?> addCourseFacultyMapping(@RequestBody MappingRequest request) {
        Faculty faculty = adminService.viewFacultyById(request.getFacultyId());
        Course course = adminService.viewCourseById(request.getCourseId());
        CourseFacultyMapping mapping = new CourseFacultyMapping();
        mapping.setFaculty(faculty);
        mapping.setCourse(course);
        CourseFacultyMapping savedMapping = adminService.addCourseFacultyMapping(mapping);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMapping);
    }

    @GetMapping("viewAllCourseFacultyMapping")
    public ResponseEntity<?> viewAllCourseFacultyMapping() {
        List<CourseFacultyMapping> mappings = adminService.viewAllCourseFacultyMapping();
        if (mappings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Courses Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mappings);
    }

    @GetMapping("viewCourseFacultyMapping")
    public ResponseEntity<?> viewCourseFacultyMapping(@RequestParam int facultyId) {
        List<CourseFacultyMapping> mappings = adminService.viewCourseFacultyMapping(facultyId);
        if (mappings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Faculty Course Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mappings);
    }

    @PostMapping("courseSectionMapping")
    public ResponseEntity<?> courseSectionMapping(@RequestBody CourseSectionRequest request) {
        int courseId = request.getCourseId();
        Section section = new Section();
        section.setSectionNo(request.getSectionNo());
        section.setCapacity(request.getCapacity());
        section.setFaculty(adminService.viewFacultyById(request.getFacultyId()));
        Section savedSection = adminService.courseSectionMapping(courseId, section);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSection);
    }

    @GetMapping("viewAllSection")
    public ResponseEntity<?> viewAllSection() {
        List<Section> sections = adminService.viewAllSection();
        if (sections.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Sections Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(sections);
    }

    @GetMapping("viewCourseFeedback")
    public ResponseEntity<?> viewCourseFeedback(@RequestParam Integer courseId, @RequestParam Integer sectionId) {
        List<CourseFeedback> feedback = adminService.viewCourseFeedback(courseId, sectionId);
        if (feedback.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Feedback Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(feedback);
    }

    @GetMapping("viewAllCourseFeedback")
    public ResponseEntity<?> viewAllCourseFeedback() {
        List<CourseFeedback> feedback = adminService.viewAllCourseFeedback();
        if (feedback.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Feedback Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(feedback);
    }

    @GetMapping("viewAllFeePayments")
    public ResponseEntity<?> viewAllFeePayments() {
        List<FeePayments> feePayments = adminService.viewAllFeePayments();
        if (feePayments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No FeePayments Found");
        }
        return ResponseEntity.ok(feePayments);
    }

    @GetMapping("viewAllFeePaymentsByStudentId")
    public ResponseEntity<?> viewAllFeePaymentsByStudentId(@RequestParam Integer studentId) {
        List<FeePayments> feePayments = adminService.viewAllFeePaymentsByStudentId(studentId);
        if (feePayments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No FeePayments Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(feePayments);
    }

    @GetMapping("viewAllFeePaymentsByFeeType")
    public ResponseEntity<?> viewAllFeePaymentsByFeeType(@RequestParam String feeType) {
        List<FeePayments> feePayments = adminService.viewAllFeePaymentsByFeeType(feeType);
        if (feePayments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No FeePayments Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(feePayments);
    }

    @GetMapping("viewAllFacultyLeave")
    public ResponseEntity<?> viewAllFacultyLeave() {
        List<FacultyLeave> facultyLeaves = adminService.viewAllFacultyLeave();
        if (facultyLeaves.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Faculty Leave Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(facultyLeaves);
    }

    @GetMapping("viewFacultyLeaveById")
    public ResponseEntity<?> viewFacultyLeaveById(@RequestParam Integer facultyLeaveId) {
        FacultyLeave leave = adminService.viewFacultyLeaveById(facultyLeaveId);
        return ResponseEntity.status(HttpStatus.OK).body(leave);
    }

    @PostMapping("viewFacultyLeaveUpdateStatus")
    public ResponseEntity<?> viewFacultyLeaveUpdateStatus(@RequestParam Integer facultyLeaveId, @RequestParam String status) {
        FacultyLeave leave = adminService.viewFacultyLeaveUpdateStatus(facultyLeaveId, status);
        return ResponseEntity.status(HttpStatus.OK).body(leave);
    }

    @GetMapping("viewAllFacultyLeaveByFacultyId")
    public ResponseEntity<?> viewAllFacultyLeaveByFacultyId(@RequestParam Integer facultyId) {
        List<FacultyLeave>  facultyLeaves =  adminService.viewAllFacultyLeaveByFacultyId(facultyId);
        if (facultyLeaves.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Faculty Leave Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(facultyLeaves);
    }

    @GetMapping("studentRegisteredCourses")
    public ResponseEntity<?> studentRegisteredCourses() {
        List<StudentCourseMapping> studentCourses = adminService.viewAllStudentCourseMapping();
        if (studentCourses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Student Courses Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentCourses);
    }


    @PostMapping("approveStudentRegistration")
    public ResponseEntity<?> approveStudentRegistration(@RequestParam Integer studentId,@RequestParam Boolean value) {
        adminService.approveStudentRegistration(studentId, value);
        return ResponseEntity.status(HttpStatus.OK).body("Approved Student Registration");
    }


}
