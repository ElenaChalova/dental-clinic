package org.top.dentalclinic.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.dentalclinic.entity.FeedbackForm;
import org.top.dentalclinic.entity.Patient;

@Repository
public interface FeedbackFormRepository extends CrudRepository<FeedbackForm, Integer> {
}
