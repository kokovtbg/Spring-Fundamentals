package bg.softuni.heroes.web;

import bg.softuni.heroes.model.Hero;
import bg.softuni.heroes.service.HeroService;
import bg.softuni.heroes.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private HeroService heroService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(HeroService heroService, CurrentUser currentUser) {
        this.heroService = heroService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("heroList")) {
            List<Hero> heroList = this.heroService.findAllByCurrentUser();
            model.addAttribute("heroList", heroList);
        }
        return "home";
    }

    @GetMapping("/details")
    public String details(@RequestParam(name = "id") long id, Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("detailHero")) {
            Hero detailHero = this.heroService.findById(id);
            model.addAttribute("detailHero", detailHero);
        }

        return "details-hero";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") long id, Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("deleteHero")) {
            Hero deleteHero = this.heroService.findById(id);
            model.addAttribute("deleteHero", deleteHero);
        }

        return "delete-hero";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.heroService.deleteById(id);
        return "redirect:/home";
    }
}
