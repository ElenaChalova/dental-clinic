package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Patient;
import org.top.dentalclinic.entity.VisitTime;
import org.top.dentalclinic.form.DoctorFilterForm;
import org.top.dentalclinic.form.PatientFilterForm;

import java.util.Optional;

@Service
public interface VisitTimeService {

    // 1. получить все расписание
    Iterable<VisitTime> findAll();

    // 2. Получение записи по id
    Optional<VisitTime> findById(Integer id);

    // 3. Добавление записи к врачу
    VisitTime save(VisitTime visitTime);

    // 4. Редактирование
    Optional<VisitTime> update(VisitTime visitTime);

    // 5. Удаление записи
    Optional<VisitTime> deleteById(Integer id);

    // метод получения всех врачей, соответствующих форме
    Iterable<VisitTime> filter(DoctorFilterForm form);
}

