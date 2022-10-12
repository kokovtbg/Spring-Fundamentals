package bg.softuni.exam25june.web;

import bg.softuni.exam25june.model.dto.SongAddDTO;
import bg.softuni.exam25june.service.SongService;
import bg.softuni.exam25june.user.CurrentUser;
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
public class SongAddController {
    private SongService songService;
    private CurrentUser currentUser;

    @Autowired
    public SongAddController(SongService songService, CurrentUser currentUser) {
        this.songService = songService;
        this.currentUser = currentUser;
    }

    @GetMapping("/song-add")
    public String addShip(Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("addSong")) {
            model.addAttribute("addSong", new SongAddDTO());
        }
        return "song-add";
    }

    @PostMapping("/song-add")
    public String addShip(@Valid @ModelAttribute("addSong") SongAddDTO addSong,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors() || !this.songService.findByPerformer(addSong.getPerformer())) {
            redirectAttributes.addFlashAttribute("addSong", addSong);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSong", bindingResult);
            return "redirect:/song-add";
        }

        this.songService.addSong(addSong);
        return "redirect:/home";
    }
}
