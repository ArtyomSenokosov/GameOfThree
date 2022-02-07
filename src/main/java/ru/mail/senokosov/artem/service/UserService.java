package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.repository.entity.User;

public interface UserService {

    void create(String name);

    User findByName(String name);

    boolean isExistByName(String name);
}
