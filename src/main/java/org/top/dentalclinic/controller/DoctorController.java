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
import org.top.dentalclinic.service.DepartmentService;
import org.top.dentalclinic.service.DoctorService;

import java.util.Optional;

@Controller
@RequestMapping("doctor")
public class DoctorController {
    // Сервисы
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    public DoctorController(DepartmentService departmentService, DoctorService doctorService) {
        this.departmentService = departmentService;
        this.doctorService =doctorService;
    }

    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Doctor> doctors = doctorService.findAll();
        model.addAttribute("doctors", doctors);
        return  "redirect:/department";
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        Doctor doctor = new Doctor();
        Iterable<Department> departments = departmentService.findAll();
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departments);
        return "doctor/add-doctor-form";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        Doctor doctor = new Doctor();
        Optional<Department> department = departmentService.findById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", new Department[]{department.get()});
        return "doctor/add-doctor-form";
    }

    @PostMapping("new")
    public String postAddForm(Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/department/" + doctor.getDepartment().getId();
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<Doctor> deleted = doctorService.deleteById(id);
        return deleted
                .map(doctor -> "redirect:/department/" + doctor.getDepartment().getId())
                .orElse("redirect:/department");
    }

    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Doctor> updated = doctorService.findById(id);
        Iterable<Department> departments = departmentService.findAll();
        if (updated.isPresent()) {
            model.addAttribute("doctor", updated.get());
            model.addAttribute("departments", departments);
            return "doctor/update-doctor-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Врач с id " + id + " не найден");
            return "redirect:/department";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Doctor doctor, RedirectAttributes redirectAttributes) {
        Optional<Doctor> updated = doctorService.update(doctor);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлен
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Информация о враче " + updated.get() + " успешно обновлена");
        } else {
            // запишем сообщение что не получилось обновить
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/department";
    }
}
