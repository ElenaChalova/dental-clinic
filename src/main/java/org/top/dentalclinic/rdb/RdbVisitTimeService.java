package org.top.dentalclinic.rdb;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.Patient;
import org.top.dentalclinic.entity.VisitTime;
import org.top.dentalclinic.form.DoctorFilterForm;
import org.top.dentalclinic.form.PatientFilterForm;
import org.top.dentalclinic.rdb.repository.VisitTimeRepository;
import org.top.dentalclinic.service.VisitTimeService;

import java.util.List;
import java.util.Optional;

@Service
public class RdbVisitTimeService implements VisitTimeService {
    // репозиторий для выполнения операций
    private final VisitTimeRepository visitTimeRepository;


    public RdbVisitTimeService (VisitTimeRepository visitTimeRepository) {
        this.visitTimeRepository = visitTimeRepository;
    }

    @Override
    public Iterable<VisitTime> findAll() {
        return visitTimeRepository.findAll();
    }

    @Override
    public Optional<VisitTime> findById(Integer id) {
        return visitTimeRepository.findById(id);
    }

    @Override
    public VisitTime save(VisitTime visitTime) {
        return visitTimeRepository.save(visitTime);
    }

    @Override
    public Optional<VisitTime> update(VisitTime visitTime) {
        // 1. проверить, есть ли объект с таким id
        Optional<VisitTime> updated = findById(visitTime.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(visitTimeRepository.save(visitTime));
        }
        return updated;
    }

    @Override
    public Optional<VisitTime> deleteById(Integer id) {
        Optional<VisitTime> deleted = visitTimeRepository.findById(id);
        if (deleted.isPresent()) {
            visitTimeRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Iterable<VisitTime> filter(DoctorFilterForm form) {
        // Каскадная фильтрация:
        // Возьмем всех врачей и начнем фильтравать их
        List<VisitTime> visitTimes = (List<VisitTime>)findAll();
        if (!form.getDoctor().equals("")) {
            // отфильтровать по ФИО
            String pattern = form.getDoctor().toLowerCase();
            visitTimes = visitTimes.stream()
                    .filter(v -> v.getDoctor().getName().toLowerCase().contains(pattern)
                    )
                    .toList();
        }
        return visitTimes;
    }

}
