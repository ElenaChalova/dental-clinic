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

import java.util.Optional;

//DepartmentController - контроллер для работы с отделениями
@Controller
@RequestMapping("department")
public class DepartmentController {
    //сервис для работы с отделениями
    private final DepartmentService departmentService;

    public DepartmentController (DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    // http://localhost:8080/department
    @GetMapping("")
    public String getAll(Model model) {
        Iterable <Department> departments =departmentService.findAll();
        model.addAttribute("departments",departments);
        return  "department/department-list";
    }

    // Обработчики добавления объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/department/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Department department = new Department(); // объект для заполнения в форме
        model.addAttribute("department", department);
        return "department/add-department-form";
    }

    @PostMapping("new")
    public String postAddForm(Department department, RedirectAttributes redirectAttributes) {
        Optional<Department> saved = departmentService.save(department);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Отделение " + saved.get() + " успешно добавлено");
        }
        // перенаправление запроса
        return "redirect:/department";
    }

    // Обработчик удаления объекта
    // http://localhost:8080/department/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Department> deleted = departmentService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение что успешно удален
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Отделение " + deleted.get() + " успешно удалено");
        } else {
            // запишем сообщение что не найден такой
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Отделение с id " + id + " не найдено"
            );
        }
        return "redirect:/department";
    }

    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Department> updated = departmentService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("department", updated.get());
            return "department/update-department-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Отделение с id " + id + " не найдено");
            return "redirect:/department";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Department department, RedirectAttributes redirectAttributes) {
        Optional<Department> updated = departmentService.update(department);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлен
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Отделение " + updated.get() + " успешно обновлено");
        } else {
            // запишем сообщение что не получилось обновить
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/department";
    }

    // Вывод информации об одном отделении (подробной)
    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Department> department = departmentService.findById(id);
        if (department.isPresent()) {
            MedicalServ medicalServ = new MedicalServ();
            medicalServ.setDepartment(department.get());
            model.addAttribute("department", department.get());
            model.addAttribute("medicalServ", medicalServ);
            return "department/department-details";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Отделение с id " + id + " не найдено");
            return "redirect:/department";
        }
    }
}
