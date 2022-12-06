package com.medportal.Medical.services.impl;

import com.medportal.Medical.configuration.MapStructMapper;
import com.medportal.Medical.dtos.DoctorDTO;
import com.medportal.Medical.exceptions.ResourceNotFoundException;
import com.medportal.Medical.exceptions.UserExistsException;
import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.repository.DoctorRepository;
import com.medportal.Medical.response.DoctorResponse;
import com.medportal.Medical.services.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private MapStructMapper mapStructMapper;
    private Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);


    @Override
    public List<DoctorDTO> getAllDoctors() {
        //gets all the doctors from the database and maps them to DTOs.
        return doctorRepository.findAll().stream().map(doctor ->
                        mapStructMapper.doctorEntityToDoctorDTO(doctor))
                .collect(Collectors.toList());
    }

    @Override
    public  List<DoctorResponse> getAllDoctorsForAppointments() {
        //gets all the doctors from the database and maps them to DTOs.
        List<DoctorResponse> doctorResponses = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.findAll();
        for(Doctor doctor : doctors){
            DoctorResponse doctorResponse = new DoctorResponse();
            doctorResponse.setId(doctor.getId());
            doctorResponse.setFirstName(doctor.getFirstName());
            doctorResponse.setLastName(doctor.getLastName());
            doctorResponses.add(doctorResponse);
        }

        return doctorResponses;
    }



    @Override
    public DoctorDTO findDoctorById(long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);


        if(doctorOptional.isPresent()){
            Doctor doctor = doctorOptional.get();
            return mapStructMapper.doctorEntityToDoctorDTO(doctor);

            //find the doctor or else throw an exception that resource not found.
        } else throw new ResourceNotFoundException("Doctor", "id", id);
    }

    @Override
    public DoctorDTO deleteDoctorById(long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);


        if(doctorOptional.isPresent()){
            Doctor doctor = doctorOptional.get();
            doctorRepository.deleteById(id);
            return mapStructMapper.doctorEntityToDoctorDTO(doctor);

            //find the doctor or else throw an exception that resource not found.
        } else throw new ResourceNotFoundException("Doctor", "id", id);
    }

    @Override
    public List<String> saveDoctor(DoctorDTO doctorDTO) throws UserExistsException {
           Doctor doctor = mapStructMapper.DoctorDTOToToDoctorEntity(doctorDTO);
            doctorRepository.save(doctor);
            List<String> response = new ArrayList<>();
            response.add("http://localhost:8080/logindoctor.html");
            return response;
    }

}

