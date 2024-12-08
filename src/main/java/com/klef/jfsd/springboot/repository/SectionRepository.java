package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Faculty;
import com.klef.jfsd.springboot.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    List<Section> findByFaculty(Faculty faculty);
}
