package ru.study.simpleshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameError", "This user already exists");
            return "registr";
        }

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("password2Error", "Passwords are different");
            return "registr";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registr";
        } else {
            userService.save(user);
            return "redirect:/login";
        }
    }
}
