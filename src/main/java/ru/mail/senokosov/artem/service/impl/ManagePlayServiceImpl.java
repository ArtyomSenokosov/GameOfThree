package ru.mail.senokosov.artem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mail.senokosov.artem.dto.GameDTO;
import ru.mail.senokosov.artem.repository.GameStatusRepository;
import ru.mail.senokosov.artem.repository.PlayerTypeRepository;
import ru.mail.senokosov.artem.repository.entity.User;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.repository.entity.GameStatus;
import ru.mail.senokosov.artem.repository.entity.PlayerType;
import ru.mail.senokosov.artem.service.ManagePlayService;
import ru.mail.senokosov.artem.util.Util;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static ru.mail.senokosov.artem.constant.GameStatusConstant.*;
import static ru.mail.senokosov.artem.constant.PlayerTypeConstant.*;
import static ru.mail.senokosov.artem.constant.ServiceConstant.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class ManagePlayServiceImpl implements ManagePlayService {

    private final UserServiceImpl userServiceImpl;
    private final GameServiceImpl gameServiceImpl;
    private final GameStatisticsServiceImpl gameStatisticsServiceImpl;

    private final GameStatusRepository gameStatusRepository;
    private final PlayerTypeRepository playerTypeRepository;

    private final Util util;

    @Override
    public GameDTO getGameInProgress(String userName) {
        GameDTO gameDTO = null;
        Game game = gameServiceImpl.findActiveGameByUserName(userName);
        if (game != null) {
            gameDTO = new GameDTO();
            gameDTO.setInfoMessage("Current number is : " + game.getCurrentNumber());
            gameDTO.setStarted(true);
        }
        return gameDTO;
    }

    @Override
    @Transactional
    public GameDTO play(String userName, String moveByName, Integer moveNumber) {
        Game game = gameServiceImpl.findActiveGameByUserName(userName);

        int currentNumber;
        if (game == null) {
            User account = userServiceImpl.findByName(userName);
            PlayerType playerType = playerTypeRepository.findByName(moveByName);
            GameStatus gameStatusStarted = gameStatusRepository.findByName(GAME_STATUS_STARTED);
            currentNumber = util.generateRandomNumberInRange(RANDOM_MIN, RANDOM_MAX);
            game = gameServiceImpl.create(account, playerType, gameStatusStarted, currentNumber);
            if (MOVE_BY_HUMAN.equalsIgnoreCase(moveByName))
                moveNumber = util.generateRandomNumberInRange(MOVE_MIN, MOVE_MAX);
        } else {
            currentNumber = game.getCurrentNumber();
        }

        boolean isSuccess = false;
        PlayerType winner = null;
        if (MOVE_BY_HUMAN.equalsIgnoreCase(moveByName)) {
            PlayerType humanPlayerType = playerTypeRepository.findByName(MOVE_BY_HUMAN);
            gameStatisticsServiceImpl.create(game, humanPlayerType, moveNumber);
            currentNumber = currentNumber + moveNumber;
            isSuccess = isDivisibleBy(currentNumber);
            if (isSuccess) {
                winner = humanPlayerType;
            }
        }
        if (!isSuccess) {
            PlayerType computerPlayerType = playerTypeRepository.findByName(MOVE_BY_COMPUTER);
            moveNumber = util.generateRandomNumberInRange(MOVE_MIN, MOVE_MAX);
            gameStatisticsServiceImpl.create(game, computerPlayerType, moveNumber);
            currentNumber = currentNumber + moveNumber;
            isSuccess = isDivisibleBy(currentNumber);
            if (isSuccess) {
                winner = computerPlayerType;
            }
        }

        GameDTO gameDTO = new GameDTO();
        gameDTO.setStarted(true);
        game.setCurrentNumber(currentNumber);
        if (isSuccess) {
            GameStatus gameStatusFinished = gameStatusRepository.findByName(GAME_STATUS_FINISHED);
            game.setStatus(gameStatusFinished);
            game.setFinishDate(LocalDateTime.now());
            game.setWinner(winner);
            gameDTO.setInfoMessage(winner.getName().toUpperCase() + " win!");
            gameDTO.setFinished(true);
        }
        gameServiceImpl.update(game);

        return gameDTO;
    }

    private boolean isDivisibleBy(int number) {
        String numberAsString = String.valueOf(number);
        int digitSum = 0;
        for (int i = 0; i < numberAsString.length(); i++) {
            digitSum = digitSum + numberAsString.charAt(i) - '0';
        }
        return (digitSum % DIVISIBLE_BY == 0);
    }
}