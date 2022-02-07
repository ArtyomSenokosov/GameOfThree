package ru.mail.senokosov.artem.repositoty.constantTest;

import java.time.LocalDateTime;

public interface Constant {

    String USER_NAME = "TestUserName";
    String GAME_STATUS_STARTED = "STARTED";
    String GAME_STATUS_FINISHED = "FINISHED";
    String PLAYER_TYPE_HUMAN = "HUMAN";
    String PLAYER_TYPE_COMPUTER = "COMPUTER";
    LocalDateTime CREATED_DATE = LocalDateTime.now();
    int INIT_NUMBER = 2846;
    int CURRENT_NUMBER = 384;
}