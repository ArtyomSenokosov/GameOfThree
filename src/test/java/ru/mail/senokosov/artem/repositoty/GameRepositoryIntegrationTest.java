package ru.mail.senokosov.artem.repositoty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.senokosov.artem.repository.*;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatus;
import ru.mail.senokosov.artem.repository.entity.PlayerType;
import ru.mail.senokosov.artem.repository.entity.User;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameStatusRepository gameStatusRepository;
    @Autowired
    private PlayerTypeRepository playerTypeRepository;

    @Test
    public void whenFindActiveGameByUserNameThenReturnGame() {
        User userEntity = new User();
        userEntity.setName(USER_NAME);
        userEntity.setCreatedDate(CREATED_DATE);
        entityManager.persist(userEntity);
        entityManager.flush();
        userEntity = userRepository.findUserByName(USER_NAME);

        GameStatus gameStatus = gameStatusRepository.findByName(GAME_STATUS_STARTED);
        PlayerType playerType = playerTypeRepository.findByName(PLAYER_TYPE_HUMAN);

        Game game = new Game();
        game.setUser(userEntity);
        game.setStartedBy(playerType);
        game.setStatus(gameStatus);
        game.setInitNumber(INIT_NUMBER);
        game.setCurrentNumber(CURRENT_NUMBER);
        game.setStartDate(CREATED_DATE);
        entityManager.persist(game);
        entityManager.flush();

        Game found = gameRepository.findByUserNameAndWinnerIsNull(USER_NAME);

        assertEquals(USER_NAME, found.getUser().getName());
        assertEquals(PLAYER_TYPE_HUMAN, found.getStartedBy().getName());
        assertEquals(GAME_STATUS_STARTED, found.getStatus().getName());
        assertEquals(INIT_NUMBER, (int) found.getInitNumber());
        assertEquals(CURRENT_NUMBER, (int) found.getCurrentNumber());
        assertTrue(CREATED_DATE.isEqual(found.getStartDate()));
        assertNull(found.getWinner());
        assertNull(found.getFinishDate());
    }
}