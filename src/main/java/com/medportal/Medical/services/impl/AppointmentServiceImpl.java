package com.medportal.Medical.services.impl;

import com.medportal.Medical.dtos.AppointmentDTO;
import com.medportal.Medical.exceptions.ResourceNotFoundException;
import com.medportal.Medical.models.Appointment;
import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import com.medportal.Medical.repository.AppointmentRepository;
import com.medportal.Medical.repository.DoctorRepository;
import com.medportal.Medical.repository.PatientRepository;
import com.medportal.Medical.services.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public List<AppointmentDTO> getAllAppointments(){
        List<Appointment> appointmentOptional = appointmentRepository.findAll();
        return appointmentOptional.stream().map(appointment -> new AppointmentDTO(appointment)).collect(Collectors.toList());
    }
    @Override
    public List<AppointmentDTO> getAllAppointmentsByPatientId(Long patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if (patientOptional.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByPatientEquals(patientOptional.get());
            return appointmentList.stream().map(appointment -> new AppointmentDTO(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDoctorId(Long doctorId){
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByDoctorEquals(doctorOptional.get());
            return appointmentList.stream().map(appointment -> new AppointmentDTO(appointment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDto, Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        Optional<Doctor> doctorOptional = doctorRepository.findById((long) 5);

        if(patientOptional.isPresent()){
            Patient patient = patientOptional.get();
            Appointment appointment = new Appointment(appointmentDto);
            appointment.setDoctor(doctorOptional.get());
            appointment.setPatient(patientOptional.get());
            patient.getAppointmentSet().add(appointment);
            doctorOptional.get().getAppointmentSet().add(appointment);
            doctorRepository.save(doctorOptional.get());
            appointmentRepository.save(appointment);
            patientRepository.save(patient);
            return new AppointmentDTO(appointment);
        }

        throw new ResourceNotFoundException("Patient", "id", patientId);
    }

    @Override
    public AppointmentDTO getAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);

        if (appointmentOptional.isPresent()){
            return new AppointmentDTO(appointmentOptional.get());
        }
        throw new ResourceNotFoundException("Appointment", "id", appointmentId);
    }

    @Override
    @Transactional
    public void deleteAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        appointmentOptional.ifPresent(appointment -> appointmentRepository.delete(appointment));
    }

    @Override
    @Transactional
    public void changeStatusAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        appointmentOptional.ifPresent(appointment -> {
            appointment.setStatus("confirmed");
            appointmentRepository.saveAndFlush(appointment);
        });
    }

    @Override
    @Transactional
    public void updateAppointmentById(AppointmentDTO appointmentDto) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());
        appointmentOptional.ifPresent(appointment -> {

            appointment.setDepartment(appointmentDto.getDepartment());
            appointment.setDate(appointmentDto.getDate());
            appointment.setTime(appointmentDto.getTime());
            appointment.setTime(appointmentDto.getTime());
            appointment.setAppcategory(appointmentDto.getAppcategory());
            appointmentRepository.saveAndFlush(appointment);
        });
    }
}
