package org.top.dentalclinic.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.dentalclinic.entity.User;

import java.util.Optional;

// UserRepository - репозиторий пользователей
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
    Iterable<User> findByRole(String role);

}
