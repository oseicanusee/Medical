package com.medportal.Medical.controllers;

import com.medportal.Medical.dtos.AppointmentDTO;
import com.medportal.Medical.services.impl.AppointmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;
    private Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @GetMapping("/all")
    public List<AppointmentDTO> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByPatient(@PathVariable Long patientId){
        logger.info(patientId.toString());
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

    @PostMapping(value = "/patient/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDto,@PathVariable Long patientId) {
        System.out.println(patientId);
        logger.info(appointmentDto.toString());
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
