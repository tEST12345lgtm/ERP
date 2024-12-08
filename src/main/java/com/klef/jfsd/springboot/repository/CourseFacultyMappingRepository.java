package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.CourseFacultyMapping;
import com.klef.jfsd.springboot.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseFacultyMappingRepository extends JpaRepository<CourseFacultyMapping, Integer> {
    List<CourseFacultyMapping> findByFaculty(Faculty faculty);
}
