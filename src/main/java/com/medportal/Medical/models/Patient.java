package com.medportal.Medical.models;

import com.medportal.Medical.dtos.PatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "Patients")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    // fields go here

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String patientName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column
    private String birthDate;

    @Column
    private String address;

    @Column
    private String photo;

    @Column
    private String phone;

    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor assignedDoctor;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Prescription> medicines;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Appointment> appointmentSet = new HashSet<>();

    public Patient(PatientDTO patientDto) {
        if (patientDto.getPatientName() != null) {
            this.patientName = patientDto.getPatientName();
        }
        if (patientDto.getFirstName() != null) {
            this.firstName = patientDto.getFirstName();
        }
        if (patientDto.getLastName() != null) {
            this.lastName = patientDto.getLastName();
        }
        if (patientDto.getEmail() != null) {
            this.email = patientDto.getEmail();
        }
        if (patientDto.getBirthDate() != null) {
            this.birthDate = patientDto.getBirthDate();
        }
        if (patientDto.getAddress() != null) {
            this.address = patientDto.getAddress();
        }
        if (patientDto.getPhoto() != null) {
            this.photo = patientDto.getPhoto();
        }
        if (patientDto.getPhone() != null) {
            this.phone = patientDto.getPhone();
        }
        if (patientDto.getPassword() != null) {
            this.password = patientDto.getPassword();
        }
    }
}
