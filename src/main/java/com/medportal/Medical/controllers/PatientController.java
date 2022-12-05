package com.medportal.Medical.controllers;

import com.medportal.Medical.dtos.PatientDTO;
import com.medportal.Medical.exceptions.UserExistsException;
import com.medportal.Medical.models.Patient;
import com.medportal.Medical.services.impl.PatientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientServiceImpl patientService;
    private Logger logger = LoggerFactory.getLogger(PatientController.class);


    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return new ResponseEntity<List<PatientDTO>>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public List<String>addPatient(@RequestBody PatientDTO patientDto) throws UserExistsException {
        logger.info(patientDto.toString());
        return patientService.addPatient(patientDto);
    }

    @DeleteMapping("delete/{patient_id}")
    public ResponseEntity<PatientDTO> deletePatient(@PathVariable long patient_id){
        return new ResponseEntity<PatientDTO>(patientService.deletePatient(patient_id), HttpStatus.OK);
    }

    @PostMapping("/login")
    public List<String> patientLogin(@RequestBody PatientDTO patientDto){
        logger.info(patientDto.toString());
        return patientService.patientLogin(patientDto);
    }




}
