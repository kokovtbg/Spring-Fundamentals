package bg.softuni.exam17august.web;

import bg.softuni.exam17august.model.dto.UserLoginDTO;
import bg.softuni.exam17august.service.UserService;
import bg.softuni.exam17august.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserLoginController {
    private UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public UserLoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userLogin")) {
            model.addAttribute("userLogin", new UserLoginDTO());
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userLogin") UserLoginDTO userLogin,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLogin", userLogin);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin", bindingResult);
            return "redirect:/login";
        }

        boolean userByUsernameAndPassword = this.userService
                .findUserByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        if (!userByUsernameAndPassword) {
            redirectAttributes.addFlashAttribute("userLogin", userLogin);
            redirectAttributes.addFlashAttribute("incorrectData", 1);
            return "redirect:/login";
        }

        this.userService.login(userLogin);
        return "redirect:/home";
    }
}
