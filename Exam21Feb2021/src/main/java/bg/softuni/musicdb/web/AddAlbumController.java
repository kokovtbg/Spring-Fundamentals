package bg.softuni.musicdb.web;

import bg.softuni.musicdb.model.dto.AlbumAddDTO;
import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.user.CurrentUser;
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
public class AddAlbumController {
    private AlbumService albumService;
    private CurrentUser currentUser;

    @Autowired
    public AddAlbumController(AlbumService albumService, CurrentUser currentUser) {
        this.albumService = albumService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add-album")
    public String album(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("addAlbum")) {
            model.addAttribute("addAlbum", new AlbumAddDTO());
        }
        return "add-album";
    }

    @PostMapping("/add-album")
    public String album(@Valid @ModelAttribute("addAlbum") AlbumAddDTO addAlbum,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addAlbum", addAlbum);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAlbum", bindingResult);
            return "redirect:/add-album";
        }

        this.albumService.addAlbum(addAlbum);
        return "redirect:/home";

    }
}
