package app.hms.controller;

import app.hms.payload.MedicalRecordDto;
import app.hms.repository.MedicalRecordRepository;
import app.hms.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {

    @Autowired
   private MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDto> createMedicalRecord(
            @RequestBody MedicalRecordDto medicalRecordDto

            ){
        MedicalRecordDto mdto = medicalRecordService.createMedicalRecord(medicalRecordDto);
        return new ResponseEntity<>(mdto,HttpStatus.CREATED);
    }

    @GetMapping
    public List<MedicalRecordDto> listMedicalRecord(){
        List<MedicalRecordDto> list = medicalRecordService.list();
        return list;
    }

    @GetMapping("/{pid}/{mid}")
    public ResponseEntity<MedicalRecordDto> getMedicalRecordById(
            @PathVariable("pid") long patientId,
            @PathVariable("mid") long mId
    ){
        MedicalRecordDto medicalRecordById = medicalRecordService.getMedicalRecordById(patientId, mId);
        return new ResponseEntity<>(medicalRecordById,HttpStatus.OK);
    }

    @DeleteMapping("/{pid}/{mid}")
    public ResponseEntity<String> deleteMedicalRecord(
            @PathVariable("pid") long pId,
            @PathVariable("mid") long mId
    ){
        medicalRecordService.deleteMedicalRecord(pId,mId);
        return new ResponseEntity<>("Medical Record deleted succesfully!!",HttpStatus.OK);
    }

    @PutMapping("/{mid}")
    public ResponseEntity<MedicalRecordDto> updateMR(
            @RequestBody MedicalRecordDto medicalRecordDto,
            @PathVariable("mid") long mId                                        ){
        MedicalRecordDto medicalRecordDto1 = medicalRecordService.updateMR(medicalRecordDto,mId);
        return new ResponseEntity<>(medicalRecordDto1,HttpStatus.OK);
    }

}
