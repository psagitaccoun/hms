package app.hms.service.impl;

import app.hms.entity.Patient;
import app.hms.exception.ResourceNotFoundException;
import app.hms.payload.PatientDto;
import app.hms.repository.PatientRepository;
import app.hms.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = mapToEntity(patientDto);
        Patient patient1 = patientRepository.save(patient);
        PatientDto patientDto1 = mapToDto(patient1);
        return patientDto1;
    }



    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> all = patientRepository.findAll();
        List<PatientDto> dtos = all.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Optional<Patient> byId = patientRepository.findById(id);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Patient is not present for this id:"+id);
        }
        Patient patient = byId.get();
        PatientDto patientDto = mapToDto(patient);
        return patientDto;
    }

    @Override
    public void deletePatient(Long id) {
        Optional<Patient> byId = patientRepository.findById(id);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Patient is not present for this id:"+id);
        }
        Patient patient = byId.get();
        patientRepository.delete(patient);

    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Optional<Patient> byId = patientRepository.findById(id);
        if (!byId.isPresent()){
            throw new ResourceNotFoundException("Patient is not present for this id:"+id);
        }
        Patient patient = byId.get();
       patient.setName(patientDto.getName());
       patient.setAge(patientDto.getAge());
       patient.setGender(patientDto.getGender());
       patient.setContactInfo(patientDto.getContactInfo());
       patient.setEmergencyContact(patientDto.getEmergencyContact());
       Patient patient1 = patientRepository.save(patient);
       PatientDto patientDto1 = mapToDto(patient1);
       return patientDto1;
    }


    private PatientDto mapToDto(Patient patient1) {
        PatientDto PatientDto = modelMapper.map(patient1, PatientDto.class);
        return PatientDto;
    }

    private Patient mapToEntity(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }


}
