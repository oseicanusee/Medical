package com.medportal.Medical.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medportal.Medical.dtos.DoctorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Entity
@Getter
@Setter
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    // fields go here
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "username")
    private String username;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "doctor_id")
    private String doctor_id;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Patient> patientList;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Appointment> appointmentSet;


    public Doctor(DoctorDTO doctorDTO){
        this.username = doctorDTO.getUsername();
        this.firstName = doctorDTO.getFirstName();
        this.lastName = doctorDTO.getLastName();
        this.doctor_id = doctorDTO.getDoctor_id();
        this.patientList = doctorDTO.getPatientList();
    }


}
