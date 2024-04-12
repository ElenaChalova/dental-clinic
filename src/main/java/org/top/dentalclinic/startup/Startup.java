package org.top.dentalclinic.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.top.dentalclinic.form.UserRegistrationForm;
import org.top.dentalclinic.service.UserService;

@Service
public class Startup {
    private final UserService userService;

    public Startup(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        // если есть суперпользователь, то ничего не делать, иначе создать
        if (userService.findUsersByRole("SUPER_ADMIN").iterator().hasNext()) {
            System.out.println("Superuser is exists");
            return;
        }
        UserRegistrationForm superUserForm = new UserRegistrationForm();
        superUserForm.setLogin("SuperAdmin");
        superUserForm.setPassword("qwerty");
        superUserForm.setPasswordConfirmation("qwerty");
        superUserForm.setRole("SUPER_ADMIN");
        if (userService.register(superUserForm)) {
            System.out.println("Created default superuser");
        } else {
            System.out.println("Can`t create default superuser");
        }
    }
}
