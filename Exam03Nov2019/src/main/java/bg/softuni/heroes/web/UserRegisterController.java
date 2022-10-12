package bg.softuni.heroes.web;

import bg.softuni.heroes.model.dto.UserRegisterDTO;
import bg.softuni.heroes.service.UserService;
import bg.softuni.heroes.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController {
    private UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public UserRegisterController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (this.currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userRegister")) {
            model.addAttribute("userRegister", new UserRegisterDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userRegister") UserRegisterDTO userRegister) {

        this.userService.register(userRegister);
        return "redirect:/login";

    }
}
