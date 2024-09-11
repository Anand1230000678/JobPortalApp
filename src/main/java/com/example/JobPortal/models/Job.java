package com.example.JobPortal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private  String position;
    private  int experience;
    private  String location;
    private  String description;
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "employee_profile_id")
      @JsonIgnore
    private  EmployeeProfile employeeProfile;
     @ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.LAZY)
     @JsonIgnore
    private Set<JobSeekerProfile> applicants;

}
