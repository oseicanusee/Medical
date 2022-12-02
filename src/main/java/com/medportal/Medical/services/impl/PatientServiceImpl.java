package com.medportal.Medical.services.impl;

import com.medportal.Medical.dtos.DoctorDTO;
import com.medportal.Medical.dtos.PatientDTO;
import com.medportal.Medical.exceptions.ResourceNotFoundException;
import com.medportal.Medical.exceptions.UserExistsException;
import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import com.medportal.Medical.repository.PatientRepository;
import com.medportal.Medical.services.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;


    @Override
    @Transactional
    public List<String> addPatient(PatientDTO patientDto) throws UserExistsException {
            List<String> response = new ArrayList<>();
            Patient patient = new Patient(patientDto);
            patientRepository.save(patient);
            response.add("http://localhost:8080/loginpatient.html");
            return response;
    }

    @Override
    public List<String> patientLogin(PatientDTO patientDto) {
        return null;
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream().map(patient -> new PatientDTO(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDTO deletePatient(long patient_id) {
        Optional<Patient> patientOptional = patientRepository.findById(patient_id);
        if(patientOptional.isPresent()){
            PatientDTO patientDTO = new PatientDTO(patientOptional.get());
            patientRepository.deleteById(patient_id);
            return patientDTO;
        }

        throw new ResourceNotFoundException("Patient", "id", patient_id);
    }


}
