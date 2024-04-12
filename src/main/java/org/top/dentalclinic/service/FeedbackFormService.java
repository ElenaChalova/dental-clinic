package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.FeedbackForm;

import java.util.Optional;

@Service
public interface FeedbackFormService {
    // получить все записи
    Iterable<FeedbackForm> findAll();

    // добавить запись
    Optional<FeedbackForm> save(FeedbackForm feedbackForm);

    // 4. Удаление записи
    Optional<FeedbackForm> deleteById(Integer id);
}
