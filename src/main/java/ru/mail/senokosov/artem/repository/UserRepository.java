package ru.mail.senokosov.artem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mail.senokosov.artem.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByName(String name);

    User findUserByName(String name);
}
