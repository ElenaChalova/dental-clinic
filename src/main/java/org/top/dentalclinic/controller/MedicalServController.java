package org.top.dentalclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.dentalclinic.entity.Department;
import org.top.dentalclinic.entity.MedicalServ;
import org.top.dentalclinic.service.DepartmentService;
import org.top.dentalclinic.service.MedicalServService;

import java.util.Optional;

@Controller
@RequestMapping("medicalServ")
public class MedicalServController {
    // Сервисы
    private final DepartmentService departmentService;
    private final MedicalServService medicalServService;

    public MedicalServController(DepartmentService departmentService, MedicalServService medicalServService) {
        this.departmentService = departmentService;
        this.medicalServService = medicalServService;
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        MedicalServ medicalServ = new MedicalServ();
        Iterable<Department> departments = departmentService.findAll();
        model.addAttribute("medicalServ", medicalServ);
        model.addAttribute("departments", departments);
        return "medicalServ/add-medicalServ-form";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        MedicalServ medicalServ = new MedicalServ();
        Optional<Department> department = departmentService.findById(id);
        model.addAttribute("medicalServ", medicalServ);
        model.addAttribute("departments", new Department[]{department.get()});
        return "medicalServ/add-medicalServ-form";
    }

    @PostMapping("new")
    public String postAddForm(MedicalServ medicalServ) {
        medicalServService.save(medicalServ);
        return "redirect:/department/" + medicalServ.getDepartment().getId();
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<MedicalServ> deleted = medicalServService.deleteById(id);
        return deleted
                .map(medicalServ -> "redirect:/department/" + medicalServ.getDepartment().getId())
                .orElse("redirect:/department");
    }

    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<MedicalServ> updated = medicalServService.findById(id);
          Iterable<Department> departments = departmentService.findAll();
        if (updated.isPresent()) {
            model.addAttribute("medicalServ", updated.get());
            model.addAttribute("departments", departments);
            return "medicalServ/update-medicalServ-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Услуга с id " + id + " не найдена");
            return "redirect:/department";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(MedicalServ medicalServ, RedirectAttributes redirectAttributes) {
        Optional<MedicalServ> updated = medicalServService.update(medicalServ);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлен
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Услуга " + updated.get() + " успешно обновлена");
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
