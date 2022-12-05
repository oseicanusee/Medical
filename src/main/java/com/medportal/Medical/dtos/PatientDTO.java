package com.medportal.Medical.dtos;


import com.medportal.Medical.models.Appointment;
import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import com.medportal.Medical.models.Prescription;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String patientName;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String address;
    private String photo;
    private String phone;
    private String password;
    private Doctor assignedDoctor;
    private Set<Prescription> medicines = new HashSet<>();
    private Set<Appointment> appointments = new HashSet<>();

    public PatientDTO(Patient patient) {
        if (patient.getId() != null) {
            this.id = patient.getId();
        }
        if (patient.getPatientName() != null) {
            this.patientName = patient.getPatientName();
        }
        if (patient.getFirstName() != null) {
            this.firstName = patient.getFirstName();
        }
        if (patient.getLastName() != null) {
            this.lastName = patient.getLastName();
        }
        if (patient.getEmail() != null) {
            this.email = patient.getEmail();
        }
        if (patient.getBirthDate() != null) {
            this.birthDate = patient.getBirthDate();
        }
        if (patient.getAddress() != null) {
            this.address = patient.getAddress();
        }
        if (patient.getPhoto() != null) {
            this.photo = patient.getPhoto();
        }
        if (patient.getPhone() != null) {
            this.phone = patient.getPhone();
        }
        if (patient.getPassword() != null) {
            this.password = patient.getPassword();
        }
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", assignedDoctor=" + assignedDoctor +
                ", medicines=" + medicines +
                ", appointments=" + appointments +
                '}';
    }
}
