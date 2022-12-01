package com.medportal.Medical.models;


import com.medportal.Medical.dtos.AppointmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private String department;

    @Column
    private java.sql.Date rDate;

    @Column
    private java.sql.Time rTime;

    @Column
    //Could be new patient or following up appointment
    private String appcategory;

    @Column
    //confirmed or not confirmed
    private String status;
    

    public Appointment(AppointmentDTO appointmentDto){

        if (appointmentDto.getDepartment() != null){
            this.department = appointmentDto.getDepartment();
        }
        if (appointmentDto.getRDate() != null){
            this.rDate = appointmentDto.getRDate();
        }

        if (appointmentDto.getRTime() != null){
            this.rTime = appointmentDto.getRTime();
        }

        if (appointmentDto.getAppcategory() != null){
            this.appcategory = appointmentDto.getAppcategory();
        }

        if (appointmentDto.getStatus() != null){
            this.status = appointmentDto.getStatus();
        }
    }
}
