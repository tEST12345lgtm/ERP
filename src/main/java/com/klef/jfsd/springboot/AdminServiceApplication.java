package com.klef.jfsd.springboot;

import com.klef.jfsd.springboot.models.Admin;
import com.klef.jfsd.springboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminServiceApplication {
    @Autowired
    public static AdminRepository  adminRepository;

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);

    }

}
