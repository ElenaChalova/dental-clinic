package org.top.dentalclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.dentalclinic.entity.Patient;
import org.top.dentalclinic.form.PatientFilterForm;
import org.top.dentalclinic.service.PatientService;

import java.util.Optional;

@Controller
@RequestMapping("patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController (PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping("")
    public String getAll(PatientFilterForm filter, Model model) {
        if (filter.isFormEmpty()) {
            Iterable<Patient> patients = patientService.findAll();
            model.addAttribute("patients", patients);
        } else {
            // сделать фильтрацию
            Iterable<Patient> filteredPatients = patientService.filter(filter);
            model.addAttribute("patients", filteredPatients);
        }
        model.addAttribute("filter", filter);
        return  "patient/patient-list";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        Patient patient = new Patient(); // объект для заполнения в форме
        model.addAttribute("patient", patient);
        return "patient/add-patient-form";
    }

    @PostMapping("new")
    public String postAddForm(Patient patient, RedirectAttributes redirectAttributes) {
        Patient saved = patientService.save(patient);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Пациент " + saved + " успешно добавлен");
        // перенаправление запроса
        return "redirect:/patient";
    }

    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Patient> updated = patientService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("patient", updated.get());
            return "patient/update-patient-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Пациент с id " + id + " не найден");
            return "redirect:/patient";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Patient patient, RedirectAttributes redirectAttributes) {
        Optional<Patient> updated = patientService.update(patient);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлен
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Пациент " + updated.get() + " успешно обновлен");
        } else {
            // запишем сообщение что не получилось обновить
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/patient";
    }
}
