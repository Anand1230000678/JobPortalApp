package com.example.JobPortal.repository;

import com.example.JobPortal.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job,Long> {
    List<Job> findByEmployerProfileId(int empId);
}
