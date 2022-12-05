package com.medportal.Medical.models;


import com.medportal.Medical.dtos.PrescriptionDTO;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Prescriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private String pharmacyName;

    @Column
    private String drugName;

    @Column
    private java.sql.Date rStartDate;

    @Column
    private java.sql.Date rRefillDate;

    @Column
    private Integer numberRefills;

    @Column
    //expired or not confirmed
    private String status;

    public Prescription(PrescriptionDTO prescriptionDto){

        if (prescriptionDto.getPharmacyName() != null){
            this.pharmacyName = prescriptionDto.getPharmacyName();
        }

        if (prescriptionDto.getDrugName() != null){
            this.drugName = prescriptionDto.getDrugName();
        }

        if (prescriptionDto.getRStartDate() != null){
            this.rStartDate = prescriptionDto.getRStartDate();
        }

        if (prescriptionDto.getRRefillDate() != null){
            this.rRefillDate = prescriptionDto.getRRefillDate();
        }

        if (prescriptionDto.getNumberRefills() != null){
            this.numberRefills = prescriptionDto.getNumberRefills();
        }

        if (prescriptionDto.getStatus() != null){
            this.status = prescriptionDto.getStatus();
        }
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", pharmacyName='" + pharmacyName + '\'' +
                ", drugName='" + drugName + '\'' +
                ", rStartDate=" + rStartDate +
                ", rRefillDate=" + rRefillDate +
                ", numberRefills=" + numberRefills +
                ", status='" + status + '\'' +
                '}';
    }
}
