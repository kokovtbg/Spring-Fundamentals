package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.ShipViewDTO;
import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private ShipService shipService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("startBattle", new StartBattleDTO());
        List<ShipViewDTO> ownedShips = this.shipService.getAllOwned(currentUser.getUsername());
        model.addAttribute("ownedShips", ownedShips);
        List<ShipViewDTO> theirShips = this.shipService.getAllTheir(currentUser.getUsername());
        model.addAttribute("theirShips", theirShips);
        return "home";
    }

    @PostMapping("/battle")
    public String postBattle(StartBattleDTO startBattleDTO) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.shipService.battle(startBattleDTO.getAttackerId(), startBattleDTO.getDefenderId());
        return "redirect:/home";
    }
}
