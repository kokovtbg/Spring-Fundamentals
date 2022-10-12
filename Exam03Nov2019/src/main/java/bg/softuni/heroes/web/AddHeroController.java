package bg.softuni.heroes.web;

import bg.softuni.heroes.model.dto.HeroAddDTO;
import bg.softuni.heroes.service.HeroService;
import bg.softuni.heroes.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddHeroController {
    private HeroService heroService;
    private CurrentUser currentUser;

    @Autowired
    public AddHeroController(HeroService heroService, CurrentUser currentUser) {
        this.heroService = heroService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add-hero")
    public String product(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("addHero")) {
            model.addAttribute("addHero", new HeroAddDTO());
        }
        return "create-hero";
    }

    @PostMapping("/add-hero")
    public String product(@ModelAttribute("addHero") HeroAddDTO addHero) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        this.heroService.addHero(addHero);
        return "redirect:/home";

    }
}
