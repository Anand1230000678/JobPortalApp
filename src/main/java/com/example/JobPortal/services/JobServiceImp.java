package com.example.JobPortal.services;

import com.example.JobPortal.models.EmployeeProfile;
import com.example.JobPortal.models.Job;
import com.example.JobPortal.models.JobSeekerProfile;
import com.example.JobPortal.repository.EmployeeProfileRepo;
import com.example.JobPortal.repository.JobRepo;
import com.example.JobPortal.repository.JobSeekerProfileRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JobServiceImp{
    @Autowired
    private JobRepo jobRepository;
    @Autowired
    private EmployeeProfileRepo employerRepository;
    @Autowired
    private JobSeekerProfileRepo jobSeekerRepository;
    public Job createJob(long id,Job job) {
        Optional<EmployeeProfile> optionalEmployerProfile=employerRepository.findById(id);
        EmployeeProfile emp=optionalEmployerProfile.get();
        job.setEmployeeProfile(emp);

        Job jb=jobRepository.save(job);
        System.out.println(jb);
        return jb;



    }

    @Transactional
    public void applyForJob(long jobSeekerProfileId, long jobId) {
        Optional<JobSeekerProfile> jobSeekerProfileOpt = jobSeekerRepository.findById(jobSeekerProfileId);
        Optional<Job> jobOpt = jobRepository.findById(jobId);

        if (jobSeekerProfileOpt.isPresent() && jobOpt.isPresent()) {
            JobSeekerProfile jobSeekerProfile = jobSeekerProfileOpt.get();
            Job job = jobOpt.get();

            jobSeekerProfile.getAppliedJobs().add(job);


            job.getApplicants().add(jobSeekerProfile);

            jobSeekerRepository.save(jobSeekerProfile);
            jobRepository.save(job);
        } else {

            throw new RuntimeException("Job or Job Seeker Profile not found");
        }
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    public List<Job> getPostedJobs(int empId){
        return jobRepository.findByEmployerProfileId(empId);
    }
    public String removeJob(long jobId){
        try{
            jobRepository.deleteById(jobId);
            return "Successfull";
        }
        catch(Exception e){
            return "Something went wrong";
        }
    }

}
