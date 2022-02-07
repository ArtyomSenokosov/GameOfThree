package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.PlayerType;

public interface GameStatisticsService {

    void create(Game game, PlayerType moveBy, Integer moveNumber);
}
