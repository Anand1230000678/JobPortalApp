package com.example.JobPortal.repository;

import com.example.JobPortal.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepo extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Users findByUseridAndPassword(@Param("email") String userid, @Param("password") String password);
    Users findByEmail(String email);

}
