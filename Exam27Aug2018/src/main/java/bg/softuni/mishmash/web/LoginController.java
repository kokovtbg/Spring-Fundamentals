package bg.softuni.mishmash.web;

import bg.softuni.mishmash.model.dto.UserLoginDTO;
import bg.softuni.mishmash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
//        if (currentUser.isLoggedIn()) {
//            return "redirect:/home";
//        }

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
