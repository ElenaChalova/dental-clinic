package org.top.dentalclinic.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.dentalclinic.entity.MedicalServ;



@Repository
public interface MedicalServRepository extends CrudRepository<MedicalServ, Integer> {

}
