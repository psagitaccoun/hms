package app.hms.controller;

import app.hms.payload.PatientDto;
import app.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/get")
    public List<PatientDto> getAllPatients() {
        List<PatientDto> allPatients = patientService.getAllPatients();
        return allPatients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        PatientDto patientById = patientService.getPatientById(id);
        return new ResponseEntity<>(patientById, HttpStatus.OK);
        // return patientRepository.findById(id)
        //.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        PatientDto patient = patientService.createPatient(patientDto);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) {
        PatientDto patientDto1 = patientService.updatePatient(id, patientDto);
        return new ResponseEntity<>(patientDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return new ResponseEntity<>("Patient deleted successfully!!!",HttpStatus.OK);
    }
}

