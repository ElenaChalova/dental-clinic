package org.top.dentalclinic.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.dentalclinic.entity.Department;

// DepartmentRepository - репозиторий для работы с отделениями
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
