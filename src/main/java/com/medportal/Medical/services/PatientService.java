package com.medportal.Medical.services;

import com.medportal.Medical.dtos.PatientDTO;
import com.medportal.Medical.exceptions.UserExistsException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PatientService {

    @Transactional
    List<String> addPatient(PatientDTO patientDto) throws UserExistsException;

    List<String> patientLogin(PatientDTO patientDto);
    List<PatientDTO> getAllPatients();
    PatientDTO deletePatient(long patient_id);


}
