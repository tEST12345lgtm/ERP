package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.CourseContent;
import com.klef.jfsd.springboot.models.Faculty;
import com.klef.jfsd.springboot.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseContentRepository extends JpaRepository<CourseContent, Integer> {


    List<CourseContent> findBySection(Section section);
}