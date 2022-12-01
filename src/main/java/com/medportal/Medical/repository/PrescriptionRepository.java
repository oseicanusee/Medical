package com.medportal.Medical.repository;

import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import com.medportal.Medical.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByDoctorEquals(Doctor doctor);
    List<Prescription> findAllByPatientEquals(Patient patient);
}
