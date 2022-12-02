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
    private String firstName;
    private String lastName;
    private String password;
    private List<Patient> patientList;
    private String email;
    private String doctor_id;

    public DoctorDTO(Doctor doctor){
        this.id = doctor.getId();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.doctor_id = doctor.getDoctor_id();
        this.patientList = doctor.getPatientList();
        this.password = doctor.getPassword();
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", patientList=" + patientList +
                ", email='" + email + '\'' +
                '}';
    }
}
