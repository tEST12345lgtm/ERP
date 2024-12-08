package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Course;
import com.klef.jfsd.springboot.models.Section;
import com.klef.jfsd.springboot.models.Student;
import com.klef.jfsd.springboot.models.StudentCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseMappingRepository extends JpaRepository<StudentCourseMapping, Integer> {
    List<StudentCourseMapping> findByStudent(Student student);

    StudentCourseMapping findByCourse(Course course);

    StudentCourseMapping findBySection(Section section);
}
