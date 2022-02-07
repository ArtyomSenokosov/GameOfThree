package ru.mail.senokosov.artem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;
import ru.mail.senokosov.artem.dto.GameDTO;
import ru.mail.senokosov.artem.dto.UserDTO;
import ru.mail.senokosov.artem.service.impl.ManagePlayServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@SessionAttributes({"user", "game"})
public class GameController {

    private final ManagePlayServiceImpl managePlayServiceImpl;

    @ModelAttribute("game")
    public GameDTO setUpGame() {
        return new GameDTO();
    }

    @GetMapping(value = "/game")
    public String move(@SessionAttribute("user") UserDTO userDTO, Model model) {
        String userName = userDTO.getName();
        if (StringUtils.isBlank(userName)) {
            return "redirect:/";
        } else {
            model.addAttribute("welcome_message", "Welcome " + userName + "!");
            GameDTO gameDTOInProgress = managePlayServiceImpl.getGameInProgress(userName);
            if (gameDTOInProgress != null) {
                model.addAttribute("game", gameDTOInProgress);
                model.addAttribute("isGameStarted", gameDTOInProgress.isStarted());
                model.addAttribute("info_message", gameDTOInProgress.getInfoMessage());
            }
            return "game";
        }
    }

    @PostMapping(value = "/game/play")
    public String play(@Valid @ModelAttribute("user") UserDTO userDTO,
                       @Valid @ModelAttribute("game") GameDTO gameDTO,
                       final RedirectAttributes redirectAttributes,
                       final HttpServletRequest req) {
        String userName = userDTO.getName();
        String moveByName = req.getParameter("moveByName");
        String startNewGameString = req.getParameter("startNewGame");
        boolean startNewGame = Boolean.parseBoolean(startNewGameString);

        if (startNewGame) {
            gameDTO = managePlayServiceImpl.play(userName, moveByName, null);
        } else {
            String moveNumberString = req.getParameter("moveNumber");
            try {
                int moveNumber = Integer.parseInt(moveNumberString);
                if (moveNumber > 1 || moveNumber < -1)
                    throw new IllegalArgumentException();
                gameDTO = managePlayServiceImpl.play(userName, moveByName, moveNumber);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                redirectAttributes.addFlashAttribute("error_message",
                        "Invalid number. Please set value in range : {-1, 0, 1}");
            }
        }

        if (gameDTO.isFinished()) {
            redirectAttributes.addFlashAttribute("isGameFinished", gameDTO.isFinished());
            redirectAttributes.addFlashAttribute("info_message", gameDTO.getInfoMessage());
        }

        return "redirect:/game";
    }
}