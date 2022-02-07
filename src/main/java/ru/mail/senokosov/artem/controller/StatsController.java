package ru.mail.senokosov.artem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mail.senokosov.artem.repository.entity.Game;
import ru.mail.senokosov.artem.service.impl.GameServiceImpl;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StatsController {

    private final GameServiceImpl gameServiceImpl;

    @GetMapping(value = "/stats/all")
    public String stats(Model model) {
        List<Game> gameEntities = gameServiceImpl.findAllFinished();
        model.addAttribute("statsType", "all");
        model.addAttribute("games", gameEntities);
        return "stats";
    }
}