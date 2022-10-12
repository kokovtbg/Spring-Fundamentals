package bg.softuni.exam17august.web;

import bg.softuni.exam17august.model.dto.PostAddDTO;
import bg.softuni.exam17august.service.PostService;
import bg.softuni.exam17august.user.CurrentUser;
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
public class PostAddController {
    private PostService postService;
    private CurrentUser currentUser;

    @Autowired
    public PostAddController(PostService postService, CurrentUser currentUser) {
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @GetMapping("/post-add")
    public String getPostAd(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("postAdd")) {
            model.addAttribute("postAdd", new PostAddDTO());
        }
        return "post-add";
    }

    @PostMapping("/post-add")
    public String postAdd(@Valid @ModelAttribute("postAdd") PostAddDTO postAdd,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAdd", postAdd);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postAdd", bindingResult);
            return "redirect:/post-add";
        }
        this.postService.addPost(postAdd);
        return "redirect:/home";
    }
}
