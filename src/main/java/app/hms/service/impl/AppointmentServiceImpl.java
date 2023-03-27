package app.hms.service.impl;

import app.hms.entity.Appointment;
import app.hms.entity.Doctor;
import app.hms.entity.Patient;
import app.hms.exception.EntityNotFoundException;
import app.hms.exception.ResourceNotFoundException;
import app.hms.payload.AppointmentDto;
import app.hms.repository.AppointmentRepository;
import app.hms.repository.DoctorRepository;
import app.hms.repository.PatientRepository;
import app.hms.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
                                  DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

   // @Override
  //  public AppointmentDto getAppointmentById(Long id) {
   //     Appointment appointment = appointmentRepository.findById(id)
  //              .orElseThrow(() -> new EntityNotFoundException("Appointment with id " + id + " not found"));

  //      return modelMapper.map(appointment, AppointmentDto.class);
  //  }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientRepository.findById(appointmentDto.getPatient().getId())
                .orElseThrow(() -> new EntityNotFoundException("Patient is not found with this id:"+appointmentDto.getPatient().getId()));

        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor is not found with this id:"+appointmentDto.getDoctor().getId()));

        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return modelMapper.map(savedAppointment, AppointmentDto.class);
    }

  //  @Override
 //   public AppointmentDto updateAppointment(AppointmentDto appointmentDto) {
    //       Appointment appointment = appointmentRepository.findById(appointmentDto.getId())
    //               .orElseThrow(() -> new EntityNotFoundException("Appointment with id " + appointmentDto.getId() + " not found"));

    //       Patient patient = patientRepository.findById(appointmentDto.getPatient().getId())
    //              .orElseThrow(() -> new EntityNotFoundException("Patient with id " + appointmentDto.getPatient().getId() + " not found"));

    //     Doctor doctor = doctorRepository.findById(appointmentDto.getDoctor().getId())
    //            .orElseThrow(() -> new EntityNotFoundException("Doctor with id " + appointmentDto.getDoctor().getId() + " not found"));

    //     modelMapper.map(appointmentDto, appointment);
    //     appointment.setPatient(patient);
    //    appointment.setDoctor(doctor);

    //     Appointment updatedAppointment = appointmentRepository.save(appointment);

    //    return modelMapper.map(updatedAppointment, AppointmentDto.class);
    //   }

    //    @Override
    //   public void deleteAppointmentById(Long id) {
    //     if (!appointmentRepository.existsById(id)) {
    //         throw new EntityNotFoundException("Appointment with id " + id + " not found");
    //     }

    //     appointmentRepository.deleteById(id);
    //   }
}

