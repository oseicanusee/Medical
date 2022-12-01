package com.medportal.Medical.repository;

import com.medportal.Medical.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    Optional<Doctor> findByUsername(String username);
}
