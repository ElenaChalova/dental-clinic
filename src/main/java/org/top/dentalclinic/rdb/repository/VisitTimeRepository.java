package org.top.dentalclinic.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.dentalclinic.entity.VisitTime;

@Repository
public interface VisitTimeRepository extends CrudRepository<VisitTime, Integer> {

}
