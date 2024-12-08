package com.klef.jfsd.springboot.repository;

import com.klef.jfsd.springboot.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUsernameAndPassword(String username, String password);
}
