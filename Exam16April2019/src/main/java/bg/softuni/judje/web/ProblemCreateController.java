package bg.softuni.judje.web;

import bg.softuni.judje.model.dto.ProblemCreateDTO;
import bg.softuni.judje.service.ProblemService;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProblemCreateController {
    private ProblemService problemService;
    private CurrentUser currentUser;

    @Autowired
    public ProblemCreateController(ProblemService problemService, CurrentUser currentUser) {
        this.problemService = problemService;
        this.currentUser = currentUser;
    }

    @GetMapping("/problem-create")
    public String problemCreate(Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("problemCreate")) {
            model.addAttribute("problemCreate", new ProblemCreateDTO());
        }
        return "create-problem";
    }

    @PostMapping("/problem-create")
    public String problemCreate(@ModelAttribute ProblemCreateDTO problemCreate) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.problemService.createProblem(problemCreate);
        return "redirect:/home";
    }
}
