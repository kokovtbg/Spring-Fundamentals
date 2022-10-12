package bg.softuni.andrey.web;

import bg.softuni.andrey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    private UserService userService;

    @Autowired
    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();
        return "redirect:/";
    }
}
