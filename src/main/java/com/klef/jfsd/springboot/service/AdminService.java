package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.models.*;

import java.util.List;

public interface AdminService {

    public Admin checkAdmin(String username, String password);
    public Admin createAdmin(Admin admin);

    public Student addStudent(Student student);
    public List<Student> viewAllStudents();
    public Student viewStudentById(String studentId);
    public String generateStudentId();


    public Faculty addFaculty(Faculty faculty);
    public List<Faculty> viewAllFaculty();
    public Faculty viewFacultyById(int facultyId);
    public Course addCourse(Course course);
    public List<Course> viewAllCourse();
    public Course viewCourseById(int courseId);

    public CourseFacultyMapping addCourseFacultyMapping(CourseFacultyMapping mapping);
    public List<CourseFacultyMapping> viewAllCourseFacultyMapping();
    public List<CourseFacultyMapping> viewCourseFacultyMapping(int facultyId);


    public Section addSection(Section section);
    public List<Section> viewAllSection();
    public Section viewSectionById(int sectionId);

    public Section courseSectionMapping(int courseId, Section section);
    public List<CourseFeedback> viewCourseFeedback(int courseId, int sectionId);
    public List<CourseFeedback> viewAllCourseFeedback();


    public List<FeePayments> viewAllFeePayments();
    public List<FeePayments> viewAllFeePaymentsByStudentId(int studentId);
    public List<FeePayments> viewAllFeePaymentsByFeeType(String type);

    public List<FacultyLeave> viewAllFacultyLeave();
    public List<FacultyLeave> viewAllFacultyLeaveByFacultyId(int facultyId);
    public FacultyLeave viewFacultyLeaveById(int facultyLeaveId);
    public FacultyLeave viewFacultyLeaveUpdateStatus(int facultyLeaveId,String  status);



    public  List<StudentCourseMapping> viewAllStudentCourseMapping();

    public void approveStudentRegistration(int studentId, boolean value);



}