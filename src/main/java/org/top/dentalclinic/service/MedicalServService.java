package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.MedicalServ;

import java.util.Optional;

// Сервис для работы с медицинскими услугами
@Service
public interface MedicalServService {

    // 1. Получение медицинской услуги по id
    Optional<MedicalServ> findById(Integer id);

    // 2. Добавление медицинской услуги
    MedicalServ save(MedicalServ medicalServ);

    // 3. Редактирование
    Optional<MedicalServ> update(MedicalServ medicalServ);

    // 4. Удаление медицинской услуги
    Optional<MedicalServ> deleteById(Integer id);
}

