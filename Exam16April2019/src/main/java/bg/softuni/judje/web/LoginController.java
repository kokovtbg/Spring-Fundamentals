package bg.softuni.judje.web;

import bg.softuni.judje.model.dto.UserLoginDTO;
import bg.softuni.judje.service.UserService;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public LoginController(UserService userService, CurrentUser currentUser) {
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
    public String login(@ModelAttribute("userLogin") UserLoginDTO userLogin) {

        this.userService.login(userLogin);
        return "redirect:/home";
    }
}
