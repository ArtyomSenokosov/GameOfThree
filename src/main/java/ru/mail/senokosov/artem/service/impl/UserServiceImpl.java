package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.repository.UserRepository;
import ru.mail.senokosov.artem.repository.entity.User;
import ru.mail.senokosov.artem.service.UserService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(String name) {
        User user = new User();
        user.setName(name);
        user.setCreatedDate(LocalDateTime.now());
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public boolean isExistByName(String name) {
        return userRepository.existsUserByName(name);
    }
}
