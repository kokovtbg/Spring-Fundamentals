package bg.softuni.musicdb.web;

import bg.softuni.musicdb.model.Album;
import bg.softuni.musicdb.service.AlbumService;
import bg.softuni.musicdb.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private AlbumService albumService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(AlbumService albumService, CurrentUser currentUser) {
        this.albumService = albumService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("albumList")) {
            List<Album> albumList = this.albumService.findAllByUserAndSort(this.currentUser.getUsername());
            model.addAttribute("albumList", albumList);
            int totalCopies = 0;
            for (Album album : albumList) {
                totalCopies += album.getCopies();
            }
            model.addAttribute("totalCopies", totalCopies);
        }
        return "home";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.albumService.delete(id);
        return "redirect:/home";
    }
}
