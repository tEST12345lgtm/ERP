package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.models.*;

import java.util.List;

public interface FacultyService {

    public Faculty checkFacultyLogin(String email, String password);
    public List<Course>  ccCourses(int facultyId);
    public Course uploadHandout(int courseId,String handout);

    public void applyLeave(FacultyLeave facultyLeave);

    public List<FacultyLeave> viewFacultyLeaves(int facultyId);


    public void uploadCourseContent(CourseContent courseContent);
    public List<CourseContent>    viewCoursesBySectionId(int sectionId);

    public StudentCourseMapping studentCourseMapping(int courseId);
    public List<Section> viewALlSections(int facultyId);

}
