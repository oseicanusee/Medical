package com.medportal.Medical.dtos;

import com.medportal.Medical.models.Appointment;
import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AppointmentDTO {

    private Long id;
    private Doctor doctor;
    private Patient patient;
    private String department;
    private String date;
    private String time;
    private String appcategory;
    private String status;

    public AppointmentDTO(Appointment appointment) {
        if (appointment.getId() != null) {
            this.id = appointment.getId();
        }
        if (appointment.getDoctor() != null) {
            this.doctor = appointment.getDoctor();
        }
        if (appointment.getPatient() != null) {
            this.patient = appointment.getPatient();
        }
        if (appointment.getDepartment() != null) {
            this.department = appointment.getDepartment();
        }
        if (appointment.getDate() != null) {
            this.date = appointment.getDate();
        }
        if (appointment.getTime() != null) {
            this.time = appointment.getTime();
        }
        if (appointment.getAppcategory() != null) {
            this.appcategory = appointment.getAppcategory();
        }
        if (appointment.getStatus() != null) {
            this.status = appointment.getStatus();
        }
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", department='" + department + '\'' +
                ", rDate=" + date +
                ", Time=" + time +
                ", appcategory='" + appcategory + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
