package com.example.JobPortal.repository;

import com.example.JobPortal.models.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile,Long> {
    EmployeeProfile findByUserId(long userId);
}
