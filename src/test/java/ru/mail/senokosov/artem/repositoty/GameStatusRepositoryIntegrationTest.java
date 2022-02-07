package ru.mail.senokosov.artem.repositoty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.senokosov.artem.repository.GameStatusRepository;
import ru.mail.senokosov.artem.repository.entity.GameStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.GAME_STATUS_FINISHED;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.GAME_STATUS_STARTED;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameStatusRepositoryIntegrationTest {

    @Autowired
    private GameStatusRepository gameStatusRepository;

    @Test
    public void whenFindGameStatusStartedThenReturnGameStatus() {

        GameStatus found = gameStatusRepository.findByName(GAME_STATUS_STARTED);

        assertEquals(GAME_STATUS_STARTED, found.getName());
    }

    @Test
    public void whenFindGameStatusFinishedThenReturnGameStatus() {

        GameStatus found = gameStatusRepository.findByName(GAME_STATUS_FINISHED);

        assertEquals(GAME_STATUS_FINISHED, found.getName());
    }

    @Test
    public void whenFindFindGameStatus_thenReturnList() {

        List<GameStatus> found = gameStatusRepository.findAll();

        assertEquals(2, found.size());
    }
}