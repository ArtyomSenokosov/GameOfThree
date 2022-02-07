package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.repository.GameStatisticsRepository;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatistics;
import ru.mail.senokosov.artem.repository.entity.PlayerType;
import ru.mail.senokosov.artem.service.GameStatisticsService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class GameStatisticsServiceImpl implements GameStatisticsService {

    private final GameStatisticsRepository gameStatisticsRepository;

    @Override
    public void create(Game game, PlayerType moveBy, Integer moveNumber) {
        GameStatistics gameStatistics = new GameStatistics();
        gameStatistics.setGame(game);
        gameStatistics.setMoveBy(moveBy);
        gameStatistics.setMoveNumber(moveNumber);
        gameStatistics.setMoveDate(LocalDateTime.now());
        gameStatistics = gameStatisticsRepository.saveAndFlush(gameStatistics);
    }
}