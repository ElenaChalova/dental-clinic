package org.top.dentalclinic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.dentalclinic.entity.*;
import org.top.dentalclinic.form.DoctorFilterForm;
import org.top.dentalclinic.form.PatientFilterForm;
import org.top.dentalclinic.service.DoctorService;
import org.top.dentalclinic.service.PatientService;
import org.top.dentalclinic.service.VisitTimeService;

import java.util.Optional;


@Controller
@RequestMapping("visitTime")
public class VisitTimeController {
    private final VisitTimeService visitTimeService;

    private final DoctorService doctorService;
    private final PatientService patientService;


    public VisitTimeController (VisitTimeService visitTimeService, DoctorService doctorService, PatientService patientService){
        this.visitTimeService=visitTimeService;
        this.doctorService=doctorService;
        this.patientService=patientService;
    }


    @GetMapping("")
    public String getAll(DoctorFilterForm filter, Model model) {
        if (filter.isFormDoctorEmpty()) {
            Iterable<VisitTime> visitTimes = visitTimeService.findAll();
            model.addAttribute("visitTimes", visitTimes);
        } else {
            // сделать фильтрацию
            Iterable<VisitTime> filteredVisitTimes = visitTimeService.filter(filter);
            model.addAttribute("visitTimes", filteredVisitTimes);
        }
        model.addAttribute("filter", filter);
        return  "visitTime/visitTime-list";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        VisitTime visitTime = new VisitTime();
        Iterable<Doctor> doctors = doctorService.findAll();
        Iterable<Patient> patients = patientService.findAll();
        model.addAttribute("visitTime", visitTime);
        model.addAttribute("doctors", doctors);
        model.addAttribute("patients", patients);
        return "visitTime/add-visitTime-form";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        VisitTime visitTime = new VisitTime();
        Optional<Doctor> doctor = doctorService.findById(id);
        Optional<Patient> patient = patientService.findById(id);
        model.addAttribute("visitTime", visitTime);
        model.addAttribute("doctors", new Doctor[]{doctor.get()});
        model.addAttribute("patients", new Patient[]{patient.get()});
        return "visitTime/add-visitTime-form";
    }

    @PostMapping("new")
    public String postAddForm(VisitTime visitTime) {
        visitTimeService.save(visitTime);
        return "redirect:/visitTime";
    }

    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<VisitTime> updated = visitTimeService.findById(id);
        Iterable<Doctor> doctors = doctorService.findAll();
        Iterable<Patient> patients = patientService.findAll();
        if (updated.isPresent()) {
            model.addAttribute("visitTime", updated.get());
            model.addAttribute("doctors", doctors);
            model.addAttribute("patients", patients);
            return "visitTime/update-visitTime-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Запись к врачу с id " + id + " не найдена");
            return "redirect:/visitTime";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(VisitTime visitTime, RedirectAttributes redirectAttributes) {
        Optional<VisitTime> updated = visitTimeService.update(visitTime);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлен
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Расписание " + updated.get() + " успешно обновлено");
        } else {
            // запишем сообщение что не получилось обновить
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/visitTime";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<VisitTime> deleted = visitTimeService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение что успешно удален
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Запись к врачу " + deleted.get() + " успешно удалена");
        } else {
            // запишем сообщение что не найден такой
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Запись с id " + id + " не найдена"
            );
        }
        return "redirect:/visitTime";
    }

}
