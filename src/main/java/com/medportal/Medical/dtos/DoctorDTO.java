package com.medportal.Medical.dtos;


import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String doctor_id;
    private List<Patient> patientList;
    private String email;

    public DoctorDTO(Doctor doctor){
        this.id = doctor.getId();
        this.username = doctor.getUsername();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.doctor_id = doctor.getDoctor_id();
        this.patientList = doctor.getPatientList();
    }


}
