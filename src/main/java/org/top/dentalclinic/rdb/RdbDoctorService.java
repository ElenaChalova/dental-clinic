package org.top.dentalclinic.rdb;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Department;
import org.top.dentalclinic.entity.Doctor;
import org.top.dentalclinic.rdb.repository.DoctorRepository;
import org.top.dentalclinic.service.DoctorService;

import java.util.Optional;

@Service
public class RdbDoctorService implements DoctorService {
    private final DoctorRepository doctorRepository;

    public RdbDoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Iterable<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> update(Doctor doctor) {
        // 1. проверить, есть ли объект с таким id
        Optional<Doctor> updated = findById(doctor.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(doctorRepository.save(doctor));
        }
        return updated;
    }

    @Override
    public Optional<Doctor> deleteById(Integer id) {
        Optional<Doctor> deleted = doctorRepository.findById(id);
        if (deleted.isPresent()) {
           doctorRepository.deleteById(id);
        }
        return deleted;
    }
}
