package com.example.JobPortal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String webSiteUrl;
    private String address;
    private String description;
    private int established;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Users user; // Field name is "user", matching 'mappedBy' in Users

    @OneToMany(mappedBy = "employeeProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Job> jobs;
}
