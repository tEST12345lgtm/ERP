package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.models.*;
import com.klef.jfsd.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StudentRepository  studentRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseFacultyMappingRepository courseFacultyMappingRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private  CourseFeedBackRepository courseFeedBackRepository;
    @Autowired
    private FeePaymentsRepository feePaymentsRepository;
    @Autowired
    private FacultyLeaveRepository facultyLeaveRepository;
    @Autowired
    private StudentCourseMappingRepository studentCourseMappingRepository;

    @Override
    public Admin checkAdmin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return  adminRepository.save(admin);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> viewAllStudents() {
        return  studentRepository.findAll();
    }


    @Override
    public Student viewStudentById(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public String generateStudentId() {
        Optional<Student> student = studentRepository.findTopByOrderByStudentIdDesc();
        String studentId = student.map(Student::getStudentId).orElse(null);

        if (studentId == null) {
            return "2200030000";
        }

        long nextId = Long.parseLong(studentId) + 1;
        return String.format("%010d", nextId);
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> viewAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty viewFacultyById(int facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> viewAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course viewCourseById(int courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public CourseFacultyMapping addCourseFacultyMapping(CourseFacultyMapping mapping) {
        return courseFacultyMappingRepository.save(mapping);
    }


    @Override
    public List<CourseFacultyMapping> viewAllCourseFacultyMapping() {
        return courseFacultyMappingRepository.findAll();
    }

    @Override
    public List<CourseFacultyMapping> viewCourseFacultyMapping(int facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        return courseFacultyMappingRepository.findByFaculty(faculty);
    }

    @Override
    public Section addSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> viewAllSection() {
        return sectionRepository.findAll();
    }

    @Override
    public Section viewSectionById(int sectionId) {
        return sectionRepository.findById(sectionId).orElse(null);
    }

    @Override
    public Section courseSectionMapping(int courseId, Section  section) {
        Course course = courseRepository.findById(courseId).orElse(null);
        section.setCourse(course);
        return sectionRepository.save(section);
    }

    @Override
    public List<CourseFeedback> viewCourseFeedback(int courseId, int sectionId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        Section section = sectionRepository.findById(sectionId).orElse(null);
        return courseFeedBackRepository.findCourseFeedbacksByCourseAndSection(course,section);
    }

    @Override
    public List<CourseFeedback> viewAllCourseFeedback() {
        return courseFeedBackRepository.findAll();
    }

    @Override
    public List<FeePayments> viewAllFeePayments() {
        return feePaymentsRepository.findAll();
    }

    @Override
    public List<FeePayments> viewAllFeePaymentsByStudentId(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        return feePaymentsRepository.findByStudent(student);
    }

    @Override
    public List<FeePayments> viewAllFeePaymentsByFeeType( String type) {
        return  feePaymentsRepository.findByFeeType(type);
    }

    @Override
    public List<FacultyLeave> viewAllFacultyLeave() {
        return facultyLeaveRepository.findAll();
    }

    @Override
    public List<FacultyLeave> viewAllFacultyLeaveByFacultyId(int facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        return facultyLeaveRepository.findByFaculty(faculty);
    }

    @Override
    public FacultyLeave viewFacultyLeaveById(int facultyLeaveId) {
        return facultyLeaveRepository.findById(facultyLeaveId).orElse(null);
    }

    @Override
    public FacultyLeave viewFacultyLeaveUpdateStatus(int facultyLeaveId, String status) {
        FacultyLeave leave = facultyLeaveRepository.findById(facultyLeaveId).orElse(null);
        assert  leave!=null;
        leave.setStatus(status);
        return facultyLeaveRepository.save(leave);
    }

    @Override
    public List<StudentCourseMapping> viewAllStudentCourseMapping() {
        return studentCourseMappingRepository.findAll();
    }

    @Override
    public void approveStudentRegistration(int studentId, boolean value) {
        Student student = studentRepository.findById(studentId).orElse(null);
        assert student != null;
        student.setApproved(value);
        studentRepository.save(student);
    }


}
