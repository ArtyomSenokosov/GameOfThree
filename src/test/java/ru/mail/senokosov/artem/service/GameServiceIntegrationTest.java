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
import ru.mail.senokosov.artem.repository.GameRepository;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatus;
import ru.mail.senokosov.artem.repository.entity.PlayerType;
import ru.mail.senokosov.artem.repository.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mail.senokosov.artem.repositoty.constantTest.Constant.USER_NAME;

@RunWith(SpringRunner.class)
public class GameServiceIntegrationTest {

    @TestConfiguration
    static class GameServiceTestContextConfiguration {

        @Bean
        public GameService gameService() {
            return new GameService() {
                @Override
                public Game create(User user, PlayerType startedBy, GameStatus gameStatus, int initNumber) {
                    return null;
                }

                @Override
                public List<Game> findAllFinished() {
                    return null;
                }

                @Override
                public Game findActiveGameByUserName(String userName) {
                    return null;
                }

                @Override
                public void update(Game game) {

                }
            };
        }
    }

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;

    @Before
    public void setUp() {
        Game game = new Game();
        User user = new User();
        user.setName(USER_NAME);
        game.setUser(user);

        Mockito.when(gameRepository.findByUserNameAndWinnerIsNull(USER_NAME)).thenReturn(game);
    }

    @Test
    public void whenFindUserByNameThenReturnUser() {
        Game found = gameService.findActiveGameByUserName(USER_NAME);
        assertEquals(USER_NAME, found.getUser().getName());
    }
}