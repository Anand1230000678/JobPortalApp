package com.example.JobPortal.services;

import com.example.JobPortal.models.EmployeeProfile;
import com.example.JobPortal.models.Users;
import com.example.JobPortal.repository.EmployeeProfileRepo;
import com.example.JobPortal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProfileServiceImp {
    @Autowired
    private  EmployeeProfileRepo employeeProfileRepository;
    @Autowired
    private UsersRepo userRepository;



    public EmployeeProfile createOrUpdateEmployerProfile(long userId, EmployeeProfile employeeProfile) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        Users user = optionalUser.get();
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }


        EmployeeProfile existingProfile = employeeProfileRepository.findByUserId(userId);

        if (existingProfile != null) {
            existingProfile.setWebSiteUrl(employeeProfile.getWebSiteUrl());
            existingProfile.setAddress(employeeProfile.getAddress());
            existingProfile.setDescription(employeeProfile.getDescription());
            existingProfile.setEstablished(employeeProfile.getEstablished());
            // Handle jobs if necessary
            return employeeProfileRepository.save(existingProfile);
        } else {
            employeeProfile.setUser(user);
            return employeeProfileRepository.save(employeeProfile);
        }
    }

    public EmployeeProfile getEmployerProfile(int userId){
        EmployeeProfile employerProfile=employeeProfileRepository.findByUserId(userId);
        return  employerProfile;
    }
}
