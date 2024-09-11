package com.example.JobPortal.services;

import com.example.JobPortal.models.Users;
import com.example.JobPortal.models.UsersProfileImage;
import com.example.JobPortal.repository.UsersProfileImgRepo;
import com.example.JobPortal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class UsersServiceImp implements UserDetailsService {
    @Autowired
    private UsersRepo userRepository;

    @Autowired
    private UsersProfileImgRepo userProfileImageRepository;

    private String username;


    public void saveProfileImage(long userId, MultipartFile file) throws IOException {
        userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UsersProfileImage userProfileImage = userProfileImageRepository.findByUserId(userId);
        if (userProfileImage == null) {
            userProfileImage = new UsersProfileImage();
            userProfileImage.setUsersId(userId);
        }

        userProfileImage.setImage(file.getBytes());
        userProfileImageRepository.save(userProfileImage);
    }

    public UsersProfileImage getProfileImage(long userId) {
        return userProfileImageRepository.findByUserId(userId);
    }

    public List<Users> allUser(){
        return userRepository.findAll();
    }

    public Users AddUser( Users user){
        userRepository.save(user);
        return user;
    }
    public Users findUser(String userid,String password){
        Users u=  userRepository.findByUseridAndPassword(userid,password);
        return u;
    }
    public String changePassword(long id ,String password){
        Optional<Users> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setPassword(password);
            userRepository.save(user);
            return "password changed";
            // Save the updated user object back to the repository
        } else {
            // Handle the case where the user with the given id is not found
            return "something went wrong";
        }


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);

    }
}
