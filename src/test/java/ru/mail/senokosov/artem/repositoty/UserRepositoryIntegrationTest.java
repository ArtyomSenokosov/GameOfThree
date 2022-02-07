package ru.mail.senokosov.artem.repositoty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.senokosov.artem.repository.UserRepository;
import ru.mail.senokosov.artem.repository.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.CREATED_DATE;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.USER_NAME;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindUserByNameThenReturnAccount() {

        User user = new User();
        user.setName(USER_NAME);
        user.setCreatedDate(CREATED_DATE);
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findUserByName(USER_NAME);

        assertEquals(USER_NAME, found.getName());
        assertTrue(CREATED_DATE.isEqual(found.getCreatedDate()));
    }

    @Test
    public void whenCheckIsUserExistByNameThenReturnBoolean() {

        User user = new User();
        user.setName(USER_NAME);
        user.setCreatedDate(CREATED_DATE);
        entityManager.persist(user);
        entityManager.flush();

        boolean isFound = userRepository.existsUserByName(USER_NAME);

        assertTrue(isFound);
    }
}