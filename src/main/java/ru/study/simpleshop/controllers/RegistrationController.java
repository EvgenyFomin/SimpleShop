package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.study.simpleshop.models.User;
import ru.study.simpleshop.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registr";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String password2,
                          @ModelAttribute @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameError", "This user already exists");
            return "registr";
        }

        if (StringUtils.isEmpty(password2)) {
            model.addAttribute("password2Error", "You must confirm your pass");
            return "registr";
        }

        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            model.addAttribute("password2Error", "Passwords are different");
            return "registr";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registr";
        } else {
            userService.addNewUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        if (userService.activateUser(code)) {
            model.addAttribute("message", "User successfully activated");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Activation code is not found!");
            model.addAttribute("messageType", "danger");
        }

        return "login";
    }
}
