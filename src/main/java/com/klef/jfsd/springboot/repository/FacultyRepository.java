package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Faculty findByEmailAndPassword(String email, String password);
}
