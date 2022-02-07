package ru.mail.senokosov.artem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @NotBlank(message = "Name may not be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
    private String name;
}
