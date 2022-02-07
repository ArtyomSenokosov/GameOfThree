package ru.mail.senokosov.artem.service;

import ru.mail.senokosov.artem.dto.GameDTO;

public interface ManagePlayService {

    GameDTO getGameInProgress(String userName);

    GameDTO play(String userName, String moveByName, Integer moveNumber);
}
