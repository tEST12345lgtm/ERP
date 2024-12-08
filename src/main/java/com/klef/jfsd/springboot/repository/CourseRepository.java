package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Course;
import com.klef.jfsd.springboot.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByCc(Faculty cc);
}
