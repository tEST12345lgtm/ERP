package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository  extends JpaRepository<Attendance,Integer>{

}
