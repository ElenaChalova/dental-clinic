package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Department;
import org.top.dentalclinic.entity.Doctor;

import java.util.Optional;

// Сервис для работы с врачами
@Service
public interface DoctorService {
    // получить всех врачей
    Iterable<Doctor> findAll();

    // получить врача по id
    Optional<Doctor> findById(Integer id);

    // добавить врача
    Doctor save(Doctor doctor);

    // 3. Редактирование
    Optional<Doctor> update(Doctor doctor);

    // 4. Удаление врача из базы
    Optional<Doctor> deleteById(Integer id);
}
