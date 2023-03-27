package app.hms.payload;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MedicalRecordDto {
    private Long id;

    private String medicalHistory;

    private String vitalSigns;

    private String diagnosis;

    private String treatmentPlan;

    private String medicalTestResults;

private PatientDto patient;
}
