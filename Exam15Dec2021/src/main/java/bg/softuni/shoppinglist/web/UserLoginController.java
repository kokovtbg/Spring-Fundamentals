package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.model.dto.UserLoginDTO;
import bg.softuni.shoppinglist.service.UserService;
import bg.softuni.shoppinglist.user.CurrentUser;
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
    public String getLogin(Model model) {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("userLogin")) {
            model.addAttribute("userLogin", new UserLoginDTO());
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("userLogin") UserLoginDTO userLogin,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLogin", userLogin);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin", bindingResult);
            return "redirect:/login";
        }
        boolean userByUsernameAndPassword = this.userService
                .findUserByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        if (!userByUsernameAndPassword) {
            redirectAttributes.addFlashAttribute("incorrectData", 1);
            return "redirect:/login";
        }
        this.userService.login(userLogin);
        return "redirect:/home";
    }
}
