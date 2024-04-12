package org.top.dentalclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.dentalclinic.form.UserRegistrationForm;
import org.top.dentalclinic.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // регистрация пользователя
    @GetMapping("register")
    public String getRegistrationForm(Model model) {
        UserRegistrationForm userRegistrationForm = new UserRegistrationForm();
        model.addAttribute("userRegistrationForm", userRegistrationForm);
        return "registration-form";
    }

    @PostMapping("register")
    public String postRegistrationForm(UserRegistrationForm userRegistrationForm, RedirectAttributes ra) {
        if (userService.register(userRegistrationForm)) {
            return "redirect:/login";
        }
        ra.addFlashAttribute(ViewMessageKeys.DANGER_MESSAGE, "Not registered, check correctness");
        return "redirect:/user/register";
    }
}
