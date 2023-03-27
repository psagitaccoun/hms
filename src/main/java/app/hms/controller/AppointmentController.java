package app.hms.controller;

import app.hms.entity.Appointment;
import app.hms.payload.AppointmentDto;
import app.hms.repository.DoctorRepository;
import app.hms.repository.PatientRepository;
import app.hms.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

  //  @GetMapping("/{id}")
  //  public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) {
  //      AppointmentDto appointmentDto = appointmentService.getAppointmentById(id);
   //     return ResponseEntity.ok(appointmentDto);
  //  }

    @PostMapping
    public ResponseEntity<AppointmentDto> saveAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto savedAppointmentDto = appointmentService.saveAppointment(appointmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointmentDto);
    }

 // @PutMapping("/{id}")
 //   public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
//        appointmentDto.setId(id);
 //       AppointmentDto updatedAppointmentDto = appointmentService.updateAppointment(appointmentDto);
//        return ResponseEntity.ok(updatedAppointmentDto);
//    }

  //  @DeleteMapping("/{id}")
  //  public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
  //      appointmentService.deleteAppointment(id);
 //       return ResponseEntity.noContent().build();
  //  }
}
