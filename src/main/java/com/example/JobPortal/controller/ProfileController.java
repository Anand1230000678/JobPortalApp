package com.example.JobPortal.controller;

import com.example.JobPortal.models.EmployeeProfile;
import com.example.JobPortal.models.JobSeekerProfile;
import com.example.JobPortal.services.EmployeeProfileServiceImp;
import com.example.JobPortal.services.JobSeekerProfileImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    @Autowired
    private EmployeeProfileServiceImp employerProfileService;
    @Autowired
    private JobSeekerProfileImp jobSeekerProfileService;



    @PostMapping("company/profile/{userId}")
    public ResponseEntity<EmployeeProfile> createOrUpdateEmployerProfile(
            @PathVariable int userId,
            @RequestBody EmployeeProfile employeeProfile) {
        EmployeeProfile updatedProfile = employerProfileService.createOrUpdateEmployerProfile(userId, employeeProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @PostMapping("jobseeker/profile/{userId}")
    public ResponseEntity<JobSeekerProfile> createOrUpdatejobSeekerProfile(
            @PathVariable int userId,
            @RequestBody JobSeekerProfile jobSeekerProfile) {
        JobSeekerProfile updatedProfile = jobSeekerProfileService.CreateOrUpdateJobSeeker(userId,jobSeekerProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("company/profile/{userId}")
    public ResponseEntity<EmployeeProfile> getEmployerProfile(@PathVariable int userId){
        EmployeeProfile employerProfile=employerProfileService.getEmployerProfile(userId);
        return ResponseEntity.ok(employerProfile);
    }

    @GetMapping("jobseeker/profile/{userId}")
    public ResponseEntity<JobSeekerProfile> getJobSeekerProfile(@PathVariable int userId){
        JobSeekerProfile jobSeekerProfile=jobSeekerProfileService.getJobSeekerProfile(userId);
        return ResponseEntity.ok(jobSeekerProfile);
    }
}
