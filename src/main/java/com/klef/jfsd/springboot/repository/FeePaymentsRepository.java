package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.FeePayments;
import com.klef.jfsd.springboot.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeePaymentsRepository extends JpaRepository<FeePayments, Integer> {

    List<FeePayments> findByStudent(Student student);
    List<FeePayments> findByFeeType(String type);
}
