package com.example.JobPortal.repository;

import com.example.JobPortal.models.UsersProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersProfileImgRepo extends JpaRepository<UsersProfileImage,Long> {
    UsersProfileImage findByUserId(long userId);
}
