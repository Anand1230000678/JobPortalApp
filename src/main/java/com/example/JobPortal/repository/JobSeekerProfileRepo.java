package com.example.JobPortal.repository;

import com.example.JobPortal.models.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepo extends JpaRepository<JobSeekerProfile,Long> {
    JobSeekerProfile findByUserId(long userId);
}
