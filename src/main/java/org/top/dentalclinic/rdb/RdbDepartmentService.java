package org.top.dentalclinic.rdb;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Department;
import org.top.dentalclinic.rdb.repository.DepartmentRepository;
import org.top.dentalclinic.service.DepartmentService;

import java.util.Optional;

// RdbDepartmentService - имплементация DepartmentService, работающая с СУБД
@Service
public class RdbDepartmentService implements DepartmentService{
    // репозиторий для выполнения операций
    private final DepartmentRepository departmentRepository;


    public RdbDepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Iterable<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> save(Department department) {
        return Optional.of(departmentRepository.save(department));
    }

    @Override
    public Optional<Department> deleteById(Integer id) {
        Optional<Department> deleted = findById(id);
        if (deleted.isPresent()) {
            departmentRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Department> update(Department department) {
        // 1. проверить, есть ли объект с таким id
        Optional<Department> updated = findById(department.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(departmentRepository.save(department));
        }
        return updated;
    }


}
