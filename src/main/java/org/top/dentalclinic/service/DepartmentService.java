package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Department;

import java.util.Optional;

// Сервис для работы с отделениями
@Service
public interface DepartmentService {
    // получить все отделения
    Iterable<Department> findAll();

    // получить по id
    Optional<Department> findById(Integer id);

    // добавить отделение
    Optional<Department> save(Department department);

    // удалить отделение
    Optional<Department> deleteById(Integer id);

    // обновить информацию об отделении
    Optional<Department> update(Department department);

}
