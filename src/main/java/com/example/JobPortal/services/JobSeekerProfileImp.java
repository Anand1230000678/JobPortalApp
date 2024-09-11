package com.example.JobPortal.services;

import com.example.JobPortal.models.JobSeekerProfile;
import com.example.JobPortal.models.Users;
import com.example.JobPortal.repository.AdminDashBoardRepo;
import com.example.JobPortal.repository.JobRepo;
import com.example.JobPortal.repository.JobSeekerProfileRepo;
import com.example.JobPortal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JobSeekerProfileImp {
    @Autowired
    private JobSeekerProfileRepo jobSeekerRepository;
    @Autowired
    private UsersRepo userRepository;
    @Autowired
    private AdminDashBoardRepo adminRepository;


    public JobSeekerProfile CreateOrUpdateJobSeeker(long userId,JobSeekerProfile jobSeekerProfile){
        Optional<Users> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }
        Users user = optionalUser.get();


        JobSeekerProfile existingProfile = jobSeekerRepository.findByUserId(userId);

        if (existingProfile != null) {
            existingProfile.setSkills(jobSeekerProfile.getSkills());
            existingProfile.setResume(jobSeekerProfile.getResume());
            // Handle jobs if necessary
            return jobSeekerRepository.save(existingProfile);
        } else {
            jobSeekerProfile.setUser(user);
            return jobSeekerRepository.save(jobSeekerProfile);
        }
    }
    public JobSeekerProfile getJobSeekerProfile(int userId){
        JobSeekerProfile jobSeekerProfile=jobSeekerRepository.findByUserId(userId);
        return  jobSeekerProfile;
    }

}
