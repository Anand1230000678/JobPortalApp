package com.example.JobPortal.controller;

import com.example.JobPortal.DTO.Login;
import com.example.JobPortal.models.Users;
import com.example.JobPortal.models.UsersProfileImage;
import com.example.JobPortal.services.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersServiceImp userService;



    @PostMapping("/changePassword/{id}")
    public String changePassword(@PathVariable long id, @RequestBody Login password){
        return userService.changePassword(id,password.getPassword());
    }

    @PostMapping("/{id}/uploadProfileImage")
    public ResponseEntity<String> uploadProfileImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            userService.saveProfileImage(id, file);
            return ResponseEntity.ok("Profile image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile image");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/{id}/profileImage")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable int id) {
        UsersProfileImage profileImage = userService.getProfileImage(id);
        if (profileImage != null && profileImage.getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(profileImage.getImage());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("admin/AllUser")
    public ResponseEntity<List<Users>> getAllUser(){
        List<Users> users=userService.allUser();
        return ResponseEntity.ok(users);
    }
}
