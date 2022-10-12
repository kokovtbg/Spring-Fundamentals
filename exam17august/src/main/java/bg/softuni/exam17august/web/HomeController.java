package bg.softuni.exam17august.web;

import bg.softuni.exam17august.model.dto.PostViewDTO;
import bg.softuni.exam17august.service.PostService;
import bg.softuni.exam17august.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private PostService postService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(PostService postService, CurrentUser currentUser) {
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        List<PostViewDTO> postViewDTOs = this.postService.getAllOwnedPosts();
        model.addAttribute("ownPosts", postViewDTOs);
        List<PostViewDTO> postViewDTOsTheir = this.postService.getAllTheirPosts();
        model.addAttribute("theirPosts", postViewDTOsTheir);
        return "home";
    }

    @RequestMapping(value = "/like/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String likePost(@PathVariable("id") long id) {
        this.postService.likePost(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deletePost(@PathVariable("id") long id) {
        this.postService.deletePost(id);
        return "redirect:/home";
    }
}
