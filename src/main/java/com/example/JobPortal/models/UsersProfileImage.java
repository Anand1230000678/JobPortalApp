package com.example.JobPortal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
   @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;
   @Column(name = "users_id")
    private long usersId;
}
