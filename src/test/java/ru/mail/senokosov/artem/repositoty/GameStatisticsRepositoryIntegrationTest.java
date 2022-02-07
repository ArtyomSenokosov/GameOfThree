package ru.mail.senokosov.artem.repositoty;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mail.senokosov.artem.repository.*;
import ru.mail.senokosov.artem.repository.entity.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameStatisticsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameStatisticsRepository gameStatisticsRepository;
    @Autowired
    private GameStatusRepository gameStatusRepository;
    @Autowired
    private PlayerTypeRepository playerTypeRepository;

    @Test
    public void whenFindActiveGameByUserNameThenReturnGame() {
        User user = new User();
        user.setName(USER_NAME);
        user.setCreatedDate(CREATED_DATE);
        entityManager.persist(user);
        entityManager.flush();
        user = userRepository.findUserByName(USER_NAME);

        GameStatus gameStatus = gameStatusRepository.findByName(GAME_STATUS_STARTED);
        PlayerType playerType = playerTypeRepository.findByName(PLAYER_TYPE_HUMAN);

        Game game = new Game();
        game.setUser(user);
        game.setStartedBy(playerType);
        game.setStatus(gameStatus);
        game.setInitNumber(INIT_NUMBER);
        game.setCurrentNumber(CURRENT_NUMBER);
        game.setStartDate(CREATED_DATE);
        entityManager.persist(game);
        entityManager.flush();
        game = gameRepository.findByUserNameAndWinnerIsNull(USER_NAME);

        int count = 5;
        for (int i = 0; i < 5; i++) {
            GameStatistics gameStatistics = new GameStatistics();
            gameStatistics.setGame(game);
            gameStatistics.setMoveNumber(1);
            gameStatistics.setMoveBy(playerType);
            gameStatistics.setMoveDate(CREATED_DATE);
            entityManager.persist(gameStatistics);
            entityManager.flush();
        }

        List<GameStatistics> found = gameStatisticsRepository.findByGame(game);

        assertEquals(found.size(), count);
    }
}
