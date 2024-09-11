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
public class JobSeekerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String resume;
    private String skills;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Users user; // Field name is "user", matching 'mappedBy' in Users

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "job_applications",
            joinColumns = @JoinColumn(name = "job_seeker_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private Set<Job> appliedJobs;
}
