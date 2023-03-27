package app.hms.service.impl;

import app.hms.entity.MedicalRecord;
import app.hms.entity.Patient;
import app.hms.exception.BlogApiException;
import app.hms.exception.BlogApiExceptionMRD;
import app.hms.exception.EntityNotFoundException;
import app.hms.exception.ResourceNotFoundException;
import app.hms.payload.MedicalRecordDto;
import app.hms.repository.MedicalRecordRepository;
import app.hms.repository.PatientRepository;
import app.hms.service.MedicalRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PatientRepository patientRepository;

    @Override//in json only iam supplying patient id
    public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto) {

        Patient patient = patientRepository.findById(medicalRecordDto.getPatient().getId()).orElseThrow(
                () -> new EntityNotFoundException("Patient is not with id:" + medicalRecordDto.getPatient().getId())
        );

        MedicalRecord medicalRecord = mapToEntity(medicalRecordDto);
        medicalRecord.setPatient(patient);
        MedicalRecord medicalRecord1 = medicalRecordRepository.save(medicalRecord);
        MedicalRecordDto medicalRecordDto1 = mapToDto(medicalRecord1);
        return medicalRecordDto1;
    }



    @Override
    public List<MedicalRecordDto> list() {

        List<MedicalRecord> all = medicalRecordRepository.findAll();
        List<MedicalRecordDto> collect = all.stream().map(a -> mapToDto(a)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public MedicalRecordDto getMedicalRecordById(long patientId, long mId) {
        Optional<Patient> byId = patientRepository.findById(patientId);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Patient is not present for this:"+patientId);
        }
        Patient patient = byId.get();


        Optional<MedicalRecord> byId1 = medicalRecordRepository.findById(mId);
        if (!byId1.isPresent()){
            throw new ResourceNotFoundException("MedicalRecord is not present for this:"+mId);
        }
        MedicalRecord medicalRecord = byId1.get();

        if (medicalRecord.getPatient().getId()!=patient.getId()){
            throw new BlogApiException("medicalrecord","patient",patientId);
        }

        MedicalRecordDto medicalRecordDto = mapToDto(medicalRecord);

        return medicalRecordDto;
    }

    @Override
    public void deleteMedicalRecord(long pId, long mId) {
        Patient patient = patientRepository.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Patient is not present for this id:" + pId));
        MedicalRecord medicalRecord = medicalRecordRepository.findById(mId).orElseThrow(() -> new ResourceNotFoundException("Medical Record is not present for this id:" + mId));
             if (medicalRecord.getPatient().getId()!=patient.getId()){
                 throw new BlogApiExceptionMRD("medicalrecord",mId,"patient",pId);
             }
             medicalRecordRepository.delete(medicalRecord);

    }

    @Override
    public MedicalRecordDto updateMR(MedicalRecordDto medicalRecordDto,long mId) {
        Patient patient = patientRepository.findById(medicalRecordDto.getPatient().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Patient is not present for this id:" + medicalRecordDto.getPatient().getId()));

        MedicalRecord medicalRecord = medicalRecordRepository.findById(mId).orElseThrow(
                () -> new ResourceNotFoundException("MedicalRecord is not present for this id:" + mId)
        );
        if (medicalRecord.getPatient().getId()!=patient.getId()){
            throw new BlogApiExceptionMRD("medicalrecord",medicalRecordDto.getId(),"patient",medicalRecordDto.getPatient().getId());
        }

        medicalRecord.setMedicalHistory(medicalRecordDto.getMedicalHistory());
        medicalRecord.setMedicalTestResults(medicalRecordDto.getMedicalTestResults());

        medicalRecord.setPatient(patient);
        MedicalRecord medicalRecord1 = medicalRecordRepository.save(medicalRecord);
        MedicalRecordDto medicalRecordDto1 = mapToDto(medicalRecord1);


        return medicalRecordDto1;
    }

    private MedicalRecordDto mapToDto(MedicalRecord medicalRecord1) {
        MedicalRecordDto map = modelMapper.map(medicalRecord1, MedicalRecordDto.class);
        return map;
    }


    private MedicalRecord mapToEntity(MedicalRecordDto medicalRecordDto) {
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDto, MedicalRecord.class);
        return medicalRecord;
    }
}
