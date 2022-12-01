package com.medportal.Medical.repository;

import com.medportal.Medical.models.Appointment;
import com.medportal.Medical.models.Doctor;
import com.medportal.Medical.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByDoctorEquals(Doctor doctor);
    List<Appointment> findAllByPatientEquals(Patient patient);
}
