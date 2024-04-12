package org.top.dentalclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.dentalclinic.entity.Department;
import org.top.dentalclinic.entity.Doctor;
import org.top.dentalclinic.entity.FeedbackForm;
import org.top.dentalclinic.service.DepartmentService;
import org.top.dentalclinic.service.DoctorService;
import org.top.dentalclinic.service.FeedbackFormService;

import java.util.Optional;

//FeedbackFormController - контроллер для работы с формой обратной связи
@Controller
@RequestMapping("feedbackForm")
public class FeedbackFormController {
    // Обработчики добавления объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/feedbackForm

    private final FeedbackFormService feedbackFormService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    public FeedbackFormController (FeedbackFormService feedbackFormService, DepartmentService departmentService, DoctorService doctorService){
        this.feedbackFormService=feedbackFormService;
        this.departmentService = departmentService;
        this.doctorService =doctorService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        Iterable <FeedbackForm> feedbackForms = feedbackFormService.findAll();
        model.addAttribute("feedbackForms",feedbackForms);
        return  "feedbackForm/feedback-list";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        FeedbackForm feedbackForm = new FeedbackForm(); // объект для заполнения в форме
        Iterable<Department> departments = departmentService.findAll();
        Iterable<Doctor> doctors = doctorService.findAll();
        model.addAttribute("feedbackForm", feedbackForm);
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
        return "feedbackForm/add-feedback-form";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        FeedbackForm feedbackForm = new FeedbackForm();
        Optional<Department> department = departmentService.findById(id);
        Optional<Doctor> doctor = doctorService.findById(id);
        model.addAttribute("feedbackForm", feedbackForm);
        model.addAttribute("departments", new Department[]{department.get()});
        model.addAttribute("doctors", new Doctor[]{doctor.get()});
        return "feedbackForm/add-feedback-form";
    }

    @PostMapping("new")
    public String postAddForm(FeedbackForm feedbackForm, RedirectAttributes redirectAttributes) {
        Optional<FeedbackForm> saved = feedbackFormService.save(feedbackForm);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Спасибо за обратную связь. Мы перезвоним Вам, чтобы уточнить время приема");
        }
        // перенаправление запроса
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<FeedbackForm> deleted = feedbackFormService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение что успешно удален
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Пациент " + deleted.get() + " успешно удален");
        } else {
            // запишем сообщение что не найден такой
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Пациент с id " + id + " не найден"
            );
        }
        return  "redirect:/feedbackForm";
    }
}
