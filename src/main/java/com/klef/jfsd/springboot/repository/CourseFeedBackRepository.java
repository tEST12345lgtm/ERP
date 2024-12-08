package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Course;
import com.klef.jfsd.springboot.models.CourseFeedback;
import com.klef.jfsd.springboot.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseFeedBackRepository extends JpaRepository<CourseFeedback,Integer> {
    List<CourseFeedback> findCourseFeedbacksByCourseAndSection(Course course, Section section);
}
