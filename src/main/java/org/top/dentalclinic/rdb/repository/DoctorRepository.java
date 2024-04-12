package org.top.dentalclinic.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.dentalclinic.entity.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
}
