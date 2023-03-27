package app.hms.service;

import app.hms.payload.PatientDto;

import java.util.List;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);

    List<PatientDto> getAllPatients();

    PatientDto getPatientById(Long id);

    void deletePatient(Long id);

    PatientDto updatePatient(Long id,PatientDto patientDto);
}
