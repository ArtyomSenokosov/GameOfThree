package ru.mail.senokosov.artem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testcontainers.shaded.org.apache.commons.lang.StringUtils;
import ru.mail.senokosov.artem.dto.UserDTO;
import ru.mail.senokosov.artem.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@SessionAttributes("user")
public class RootController {

    private final UserServiceImpl userServiceImpl;

    @ModelAttribute("user")
    public UserDTO setUpUserForm() {
        return new UserDTO();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/register")
    public String login(@Valid @ModelAttribute("user") UserDTO userDTO,
                        Errors errors,
                        RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            List<String> errorMessagesList = new ArrayList<>();
            for (ObjectError e : errors.getAllErrors()) {
                errorMessagesList.add(e.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("errorMessage",
                    StringUtils.join(errorMessagesList, " ::: "));
            return "redirect:/";
        } else {
            String userName = userDTO.getName();
            boolean isAccountExist = userServiceImpl.isExistByName(userName);
            if (!isAccountExist) {
                try {
                    userServiceImpl.create(userName);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "Something wrong while creating user. Check and try another name please.");
                    log.error(e.getMessage(), e);
                    return "redirect:/";
                }
            }
            return "redirect:/game";
        }
    }
}