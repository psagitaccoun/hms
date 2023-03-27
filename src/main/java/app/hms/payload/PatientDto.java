package app.hms.payload;


import app.hms.entity.MedicalRecord;
import lombok.Data;

import java.util.List;

@Data
public class PatientDto {

    private Long id;

    private String name;

    private Integer age;

    private String gender;

    private String contactInfo;

    private String emergencyContact;



}
