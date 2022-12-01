package com.medportal.Medical.services;


import com.medportal.Medical.dtos.DoctorDTO;
import com.medportal.Medical.exceptions.UserExistsException;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    DoctorDTO findDoctorById(long id);
    DoctorDTO deleteDoctorById(long id);
    DoctorDTO saveDoctor(DoctorDTO doctorDTO) throws UserExistsException;
}