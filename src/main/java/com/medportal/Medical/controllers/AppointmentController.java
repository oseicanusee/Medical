package com.medportal.Medical.controllers;

import com.medportal.Medical.dtos.AppointmentDTO;
import com.medportal.Medical.services.impl.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @GetMapping("/all")
    public List<AppointmentDTO> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByPatient(@PathVariable Long patientId){
        return new ResponseEntity<List<AppointmentDTO>>(appointmentService.getAllAppointmentsByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByDoctor(@PathVariable Long doctorId){
        return new ResponseEntity<List<AppointmentDTO>>(appointmentService.getAllAppointmentsByDoctorId(doctorId), HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long appointmentId){
        return new ResponseEntity<AppointmentDTO>(appointmentService.getAppointmentById(appointmentId), HttpStatus.OK);
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDto,@PathVariable Long patientId) {
        return new ResponseEntity<AppointmentDTO>(appointmentService.addAppointment(appointmentDto, patientId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointmentById(@PathVariable Long appointmentId){
        appointmentService.deleteAppointmentById(appointmentId);
    }

    @PutMapping("/{appointmentId}")
    public void changeStatusAppointmentById(@PathVariable Long appointmentId){
        appointmentService.changeStatusAppointmentById(appointmentId);
    }

    @PutMapping
    public void updateAppointment(@RequestBody AppointmentDTO appointmentDto){
        appointmentService.updateAppointmentById(appointmentDto);
    }
}
