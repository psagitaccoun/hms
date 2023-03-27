package app.hms.payload;

import lombok.Data;

import javax.persistence.Column;


@Data
public class DoctorDto {
    private Long id;

    private String name;

    private String specialization;

    private String contactInfo;

}
