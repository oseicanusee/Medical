package com.medportal.Medical.services;


import com.medportal.Medical.dtos.DoctorDTO;
import com.medportal.Medical.exceptions.UserExistsException;
import com.medportal.Medical.response.DoctorResponse;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    DoctorDTO findDoctorById(long id);
    DoctorDTO deleteDoctorById(long id);
    List<String> saveDoctor(DoctorDTO doctorDTO) throws UserExistsException;
    List<DoctorResponse> getAllDoctorsForAppointments();
}
