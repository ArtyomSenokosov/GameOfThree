package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatus;
import ru.mail.senokosov.artem.repository.entity.PlayerType;
import ru.mail.senokosov.artem.repository.entity.User;

import java.util.List;

public interface GameService {

    Game create(User user, PlayerType startedBy, GameStatus gameStatus, int initNumber);

    List<Game> findAllFinished();

    Game findActiveGameByUserName(String userName);

    void update(Game game);
}