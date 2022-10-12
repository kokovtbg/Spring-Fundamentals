package bg.softuni.coffeshop.web;

import bg.softuni.coffeshop.model.dto.UserRegisterDTO;
import bg.softuni.coffeshop.service.UserService;
import bg.softuni.coffeshop.user.CurrentUser;
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
    public String register(@Valid @ModelAttribute("userRegister") UserRegisterDTO userRegister,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (this.currentUser.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || this.userService.findByUsernameAndEmail(userRegister)) {
            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);
            return "redirect:/register";
        }
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegister", userRegister);
            return "redirect:/register";
        }

        this.userService.register(userRegister);
        return "redirect:/login";

    }
}
