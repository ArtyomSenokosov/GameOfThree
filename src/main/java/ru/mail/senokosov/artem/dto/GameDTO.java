package ru.mail.senokosov.artem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameDTO {

    private String infoMessage;

    @JsonProperty
    private boolean isStarted;

    @JsonProperty
    private boolean isFinished;
}