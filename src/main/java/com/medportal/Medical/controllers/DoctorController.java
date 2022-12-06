package com.medportal.Medical.controllers;

import com.medportal.Medical.dtos.DoctorDTO;
import com.medportal.Medical.exceptions.UserExistsException;
import com.medportal.Medical.response.DoctorResponse;
import com.medportal.Medical.services.impl.DoctorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl doctorServiceImpl;


    private Logger logger = LoggerFactory.getLogger(DoctorController.class);


    @GetMapping("/")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return new ResponseEntity<List<DoctorDTO>>(doctorServiceImpl.getAllDoctors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable long id) {
        return new ResponseEntity<DoctorDTO>(doctorServiceImpl.findDoctorById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> deleteDoctorById(@PathVariable long id) {
        return new ResponseEntity<DoctorDTO>(doctorServiceImpl.deleteDoctorById(id), HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody DoctorDTO doctorDTO) throws UserExistsException {
//        return new ResponseEntity<DoctorDTO>(doctorServiceImpl.saveDoctor(doctorDTO), HttpStatus.CREATED);
//    }

    @PostMapping("/register")
    public List<String> saveDoctor(@RequestBody DoctorDTO doctorDTO) throws UserExistsException {


//        String passHash = passwordEncoder.encode(patientDto.getPassword());
//        System.out.println(passHash);
//        patientDto.setPassword(passHash);
//        logger.info(doctorDTO.toString());
      //  logger.info(doctorDTO.toString());
        return doctorServiceImpl.saveDoctor(doctorDTO);
    }

    @GetMapping("/appointment")
    public List<DoctorResponse> getDoctorForAppointment(){
        return doctorServiceImpl.getAllDoctorsForAppointments();
    }






}
