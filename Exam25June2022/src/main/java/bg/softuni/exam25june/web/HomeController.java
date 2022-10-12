package bg.softuni.exam25june.web;

import bg.softuni.exam25june.model.dto.SongViewDTO;
import bg.softuni.exam25june.model.dto.UserAlbumDTO;
import bg.softuni.exam25june.service.SongService;
import bg.softuni.exam25june.service.UserService;
import bg.softuni.exam25june.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private SongService songService;
    private UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(SongService songService, UserService userService, CurrentUser currentUser) {
        this.songService = songService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        List<SongViewDTO> songListPop = this.songService.findAllByStylePop();
        model.addAttribute("songListPop", songListPop);
        List<SongViewDTO> songListRock = this.songService.findAllByStyleRock();
        model.addAttribute("songListRock", songListRock);
        List<SongViewDTO> songListJazz = this.songService.findAllByStyleJazz();
        model.addAttribute("songListJazz", songListJazz);


        UserAlbumDTO userAlbum = this.userService.findByUsernameAndAddSong(this.currentUser.getUsername(), null);
        model.addAttribute("userAlbum", userAlbum);
        int totalTime = userAlbum.getSongs().stream().mapToInt(SongViewDTO::getDuration).sum();
        model.addAttribute("totalTime", totalTime);
        return "home";
    }

    @GetMapping("/add")
    public String getAddSong(@RequestParam(name = "title") String title,
                             Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        UserAlbumDTO userAlbum = this.userService.findByUsernameAndAddSong(this.currentUser.getUsername(), title);
        model.addAttribute("userAlbum", userAlbum);
        int totalTime = userAlbum.getSongs().stream().mapToInt(SongViewDTO::getDuration).sum();
        model.addAttribute("totalTime", totalTime);

        return "redirect:/home";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.userService.deletePlaylist(this.currentUser.getUsername());
        return "redirect:/home";
    }
}
