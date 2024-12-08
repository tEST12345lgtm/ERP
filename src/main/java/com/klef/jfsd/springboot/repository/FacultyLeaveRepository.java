package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Faculty;
import com.klef.jfsd.springboot.models.FacultyLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyLeaveRepository extends JpaRepository<FacultyLeave, Integer> {
    List<FacultyLeave> findByFaculty(Faculty faculty);
}
