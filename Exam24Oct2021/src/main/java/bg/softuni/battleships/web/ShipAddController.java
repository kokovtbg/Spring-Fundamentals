package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.ShipAddDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.user.CurrentUser;
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
public class ShipAddController {
    private ShipService shipService;
    private CurrentUser currentUser;

    @Autowired
    public ShipAddController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @GetMapping("/ship-add")
    public String getShipAdd(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("shipAdd")) {
            model.addAttribute("shipAdd", new ShipAddDTO());
        }
        return "ship-add";
    }

    @PostMapping("/ship-add")
    public String postShipAdd(@Valid @ModelAttribute("shipAdd") ShipAddDTO shipAdd,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipAdd", shipAdd);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAdd", bindingResult);
            return "redirect:/ship-add";
        }
        boolean shipExists = this.shipService.findByName(shipAdd.getName());
        if (shipExists) {
            redirectAttributes.addFlashAttribute("shipAdd", shipAdd);
            return "redirect:/ship-add";
        }
        this.shipService.addShip(shipAdd);
        return "redirect:/home";
    }
}
