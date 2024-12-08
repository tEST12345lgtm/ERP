package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.models.*;
import com.klef.jfsd.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private FacultyLeaveRepository facultyLeaveRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseMappingRepository studentCourseMappingRepository;
    @Autowired
    private CourseContentRepository courseContentRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Faculty checkFacultyLogin(String email, String password) {
        return facultyRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<Course> ccCourses(int facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        return courseRepository.findByCc(faculty);
    }

    @Override
    public Course uploadHandout(int courseId, String handout) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        course.setHandout(handout);
        return courseRepository.save(course);
    }

    @Override
    public void applyLeave(FacultyLeave facultyLeave) {
        facultyLeaveRepository.save(facultyLeave);
    }

    @Override
    public List<FacultyLeave> viewFacultyLeaves(int facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        assert faculty != null;
        return facultyLeaveRepository.findByFaculty(faculty);
    }

    @Override
    public void uploadCourseContent(CourseContent courseContent) {

        courseContentRepository.save(courseContent);
    }



    @Override
    public List<CourseContent> viewCoursesBySectionId(int sectionId) {
        Section section = sectionRepository.findById(sectionId).orElse(null);
        assert section != null;
        return courseContentRepository.findBySection(section);
    }

    @Override
    public StudentCourseMapping studentCourseMapping(int courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        return studentCourseMappingRepository.findByCourse(course);
    }

    @Override
    public List<Section> viewALlSections(int facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElse(null);
        return sectionRepository.findByFaculty(faculty);
    }


}
