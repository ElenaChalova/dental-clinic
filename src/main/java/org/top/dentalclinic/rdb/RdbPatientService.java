package org.top.dentalclinic.rdb;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Patient;
import org.top.dentalclinic.form.PatientFilterForm;
import org.top.dentalclinic.rdb.repository.PatientRepository;
import org.top.dentalclinic.service.PatientService;

import java.util.List;
import java.util.Optional;

@Service
public class RdbPatientService implements PatientService {

    private final PatientRepository patientRepository;

    public RdbPatientService (PatientRepository patientRepository) {
        this.patientRepository=patientRepository;
    }

    @Override
    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Integer id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> update(Patient patient) {
        // 1. проверить, есть ли объект с таким id
        Optional<Patient> updated = findById(patient.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(patientRepository.save(patient));
        }
        return updated;
    }

    @Override
    public Iterable<Patient> filter(PatientFilterForm form) {
        // Каскадная фильтрация:
        // Возьмем всех пациентов и начнем фильтравать их
        List<Patient> patients = (List<Patient>)findAll();
        if (!form.getName().equals("")) {
            // отфильтровать по ФИО
            String pattern = form.getName().toLowerCase();
            patients = patients.stream()
                    .filter(p -> p.getName().toLowerCase().contains(pattern)
                    )
                    .toList();
        }
        return patients;
    }

}
