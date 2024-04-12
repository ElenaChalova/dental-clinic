package org.top.dentalclinic.rdb;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.MedicalServ;
import org.top.dentalclinic.rdb.repository.MedicalServRepository;
import org.top.dentalclinic.service.MedicalServService;

import java.util.Optional;

@Service
public class RdbMedicalServService implements MedicalServService {

    private final MedicalServRepository medicalServRepository;

    public RdbMedicalServService(MedicalServRepository medicalServRepository) {
        this.medicalServRepository = medicalServRepository;
    }

    @Override
    public Optional<MedicalServ> findById(Integer id) {
        return medicalServRepository.findById(id);
    }

    @Override
    public MedicalServ save(MedicalServ medicalServ) {
        return medicalServRepository.save(medicalServ);
    }

    @Override
    public Optional<MedicalServ> update(MedicalServ medicalServ) {
        // 1. проверить, есть ли объект с таким id
        Optional<MedicalServ> updated = findById(medicalServ.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(medicalServRepository.save(medicalServ));
        }
        return updated;
    }

    @Override
    public Optional<MedicalServ> deleteById(Integer id) {
        Optional<MedicalServ> deleted = medicalServRepository.findById(id);
        if (deleted.isPresent()) {
            medicalServRepository.deleteById(id);
        }
        return deleted;
    }
}
