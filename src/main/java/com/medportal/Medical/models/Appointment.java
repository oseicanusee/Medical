package com.medportal.Medical.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medportal.Medical.dtos.AppointmentDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")

    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private String department;

    @Column
    private String date;

    @Column
    private String time;

    @Column
    private String appcategory;

    @Column
    private String status;
    

    public Appointment(AppointmentDTO appointmentDto){

        if (appointmentDto.getDepartment() != null){
            this.department = appointmentDto.getDepartment();
        }
        if (appointmentDto.getDate() != null){
            this.date = appointmentDto.getDate();
        }

        if (appointmentDto.getTime() != null){
            this.time = appointmentDto.getTime();
        }

        if (appointmentDto.getAppcategory() != null){
            this.appcategory = appointmentDto.getAppcategory();
        }

        if (appointmentDto.getStatus() != null){
            this.status = appointmentDto.getStatus();
        }
    }

//    @Override
//    public String toString() {
//        return "Appointment{" +
//                "id=" + id +
//                ", doctor=" + doctor +
//                ", patient=" + patient +
//                ", department='" + department + '\'' +
//                ", date='" + date + '\'' +
//                ", time='" + time + '\'' +
//                ", appcategory='" + appcategory + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
