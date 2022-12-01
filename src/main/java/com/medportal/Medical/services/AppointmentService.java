package com.medportal.Medical.services;

import com.medportal.Medical.dtos.AppointmentDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<AppointmentDTO> getAllAppointments();
    List<AppointmentDTO> getAllAppointmentsByPatientId(Long patientId);

    List<AppointmentDTO> getAllAppointmentsByDoctorId(Long doctorId);

    @Transactional
    AppointmentDTO addAppointment(AppointmentDTO appointmentDto, Long patientId);

    @Transactional
    AppointmentDTO getAppointmentById(Long appointmentId);

    @Transactional
    void deleteAppointmentById(Long appointmentId);

    @Transactional
    void changeStatusAppointmentById(Long appointmentId);

    @Transactional
    void updateAppointmentById(AppointmentDTO appointmentDto);
}
