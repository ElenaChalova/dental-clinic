package org.top.dentalclinic.service;

import org.springframework.stereotype.Service;
import org.top.dentalclinic.entity.User;
import org.top.dentalclinic.form.UserRegistrationForm;

// UserService - сервис для работы с пользователями
@Service
public interface UserService {
    boolean register(UserRegistrationForm userRegistrationForm);

    Iterable<User> findUsersByRole(String role);
}
