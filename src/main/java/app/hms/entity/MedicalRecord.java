package app.hms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_history")
    private String medicalHistory;

    @Column(name = "vital_signs")
    private String vitalSigns;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment_plan")
    private String treatmentPlan;

    @Column(name = "medical_test_results")
    private String medicalTestResults;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // getters and setters
}

