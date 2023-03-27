package app.hms.payload;

import lombok.Data;

import java.time.LocalDateTime;
@Data


public class AppointmentDto {
    private Long id;
    private LocalDateTime dateTime;
    private PatientDto patient;
    private DoctorDto doctor;

}
