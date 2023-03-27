package app.hms.service;

import app.hms.payload.MedicalRecordDto;

import java.util.List;

public interface MedicalRecordService {


    MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto);

    List< MedicalRecordDto> list();

    MedicalRecordDto getMedicalRecordById(long patientId, long mId);

    void deleteMedicalRecord(long pId, long mId);

    MedicalRecordDto updateMR(MedicalRecordDto medicalRecordDto,long mId );
}
