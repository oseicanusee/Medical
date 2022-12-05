package com.medportal.Medical.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medportal.Medical.dtos.PatientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Patients")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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


    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> medicines;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL})
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

    @Override
    public String toString() {
        return "Patient{" +
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
                ", appointmentSet=" + appointmentSet +
                '}';
    }
}
