package ru.mail.senokosov.artem.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.senokosov.artem.repository.UserRepository;
import ru.mail.senokosov.artem.repository.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.CREATED_DATE;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.USER_NAME;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService() {
                @Override
                public void create(String name) {

                }

                @Override
                public User findByName(String name) {
                    return null;
                }

                @Override
                public boolean isExistByName(String name) {
                    return false;
                }
            };
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = new User();
        user.setName(USER_NAME);
        user.setCreatedDate(CREATED_DATE);

        Mockito.when(userRepository.saveAndFlush(user)).thenReturn(user);
        Mockito.when(userRepository.findUserByName(USER_NAME)).thenReturn(user);
        Mockito.when(userRepository.existsUserByName(USER_NAME)).thenReturn(true);
    }

    @Test
    public void whenFindUserByNameThenReturnUser() {
        User found = userService.findByName(USER_NAME);
        assertEquals(USER_NAME, found.getName());
    }

    @Test
    public void whenCheckIsUserExistByNameThenReturnBoolean() {
        boolean found = userService.isExistByName(USER_NAME);
        assertTrue(found);
    }
}