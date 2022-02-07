package ru.mail.senokosov.artem.repositoty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.senokosov.artem.repository.PlayerTypeRepository;
import ru.mail.senokosov.artem.repository.entity.PlayerType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.PLAYER_TYPE_COMPUTER;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.PLAYER_TYPE_HUMAN;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerTypeRepositoryIntegrationTest {

    @Autowired
    private PlayerTypeRepository playerTypeRepository;

    @Test
    public void whenFindHumanThenReturnPlayerType() {

        PlayerType found = playerTypeRepository.findByName(PLAYER_TYPE_HUMAN);

        assertEquals(PLAYER_TYPE_HUMAN, found.getName());
    }

    @Test
    public void whenFindComputerThenReturnPlayerType() {

        PlayerType found = playerTypeRepository.findByName(PLAYER_TYPE_COMPUTER);

        assertEquals(PLAYER_TYPE_COMPUTER, found.getName());
    }

    @Test
    public void whenFindPlayerTypeThenReturnList() {

        List<PlayerType> found = playerTypeRepository.findAll();

        assertEquals(2, found.size());
    }
}