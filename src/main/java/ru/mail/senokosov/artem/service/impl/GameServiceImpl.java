package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.repository.GameRepository;
import ru.mail.senokosov.artem.repository.entity.User;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatus;
import ru.mail.senokosov.artem.repository.entity.PlayerType;
import ru.mail.senokosov.artem.service.GameService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public Game create(User user, PlayerType startedBy,
                       GameStatus gameStatus, int initNumber) {
        Game game = new Game();
        game.setUser(user);
        game.setStartedBy(startedBy);
        game.setStatus(gameStatus);
        game.setInitNumber(initNumber);
        game.setCurrentNumber(initNumber);
        game.setStartDate(LocalDateTime.now());
        game = gameRepository.saveAndFlush(game);
        return game;
    }

    @Override
    public List<Game> findAllFinished() {
        return gameRepository.findAllByWinnerIsNotNull();
    }

    @Override
    public Game findActiveGameByUserName(String userName) {
        return gameRepository.findByUserNameAndWinnerIsNull(userName);
    }

    @Override
    public void update(Game game) {
        gameRepository.saveAndFlush(game);
    }
}