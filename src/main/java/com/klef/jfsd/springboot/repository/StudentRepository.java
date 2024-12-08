package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByStudentId(String studentId);

    Optional<Student> findTopByOrderByStudentIdDesc();

    Student findByStudentIdOrEmail(String studentId, String email);
}
