package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Patient;
import org.top.dentalclinic.entity.VisitTime;
import org.top.dentalclinic.form.PatientFilterForm;

import java.util.Optional;

@Service
public interface PatientService {

    // 1. получить список всех пациентов
    Iterable<Patient> findAll();

    // 2. Получение записи по id
    Optional<Patient> findById(Integer id);

    // 3. Добавление записи к врачу
    Patient save(Patient patient);

    // 4. Редактирование
    Optional<Patient> update(Patient patient);

    // метод получения всех пациентов, соответствующих форме
    Iterable<Patient> filter(PatientFilterForm form);
}
