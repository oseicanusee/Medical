package com.medportal.Medical.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medportal.Medical.dtos.DoctorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor {
    // fields go here
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;


    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "doctor_id", unique = true)
    private String doctor_id;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Patient> patientList;


    @JsonIgnore
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Appointment> appointmentSet;


    public Doctor(DoctorDTO doctorDTO){
        this.firstName = doctorDTO.getFirstName();
        this.lastName = doctorDTO.getLastName();
        this.doctor_id = doctorDTO.getDoctor_id();
        this.patientList = doctorDTO.getPatientList();
        this.password = doctorDTO.getPassword();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", email='" + email + '\'' +
                ", patientList=" + patientList +
                ", appointmentSet=" + appointmentSet +
                '}';
    }
}
