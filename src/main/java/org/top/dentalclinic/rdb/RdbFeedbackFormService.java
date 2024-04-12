package org.top.dentalclinic.rdb;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.FeedbackForm;
import org.top.dentalclinic.rdb.repository.FeedbackFormRepository;
import org.top.dentalclinic.service.FeedbackFormService;

import java.util.Optional;


@Service
public class RdbFeedbackFormService implements FeedbackFormService {

    private final FeedbackFormRepository feedbackFormRepository;


    public RdbFeedbackFormService(FeedbackFormRepository feedbackFormRepository) {
        this.feedbackFormRepository = feedbackFormRepository;
    }

    @Override
    public Iterable<FeedbackForm> findAll() {
        return feedbackFormRepository.findAll();
    }

    @Override
    public Optional<FeedbackForm> save(FeedbackForm feedbackForm) {
        return Optional.of(feedbackFormRepository.save(feedbackForm));
    }

    @Override
    public Optional<FeedbackForm> deleteById(Integer id) {
        Optional<FeedbackForm> deleted = feedbackFormRepository.findById(id);
        if (deleted.isPresent()) {
            feedbackFormRepository.deleteById(id);
        }
        return deleted;
    }

}
