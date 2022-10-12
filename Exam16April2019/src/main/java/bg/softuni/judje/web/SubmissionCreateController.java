package bg.softuni.judje.web;

import bg.softuni.judje.model.Problem;
import bg.softuni.judje.model.dto.SubmissionCreateDTO;
import bg.softuni.judje.service.ProblemService;
import bg.softuni.judje.service.SubmissionService;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmissionCreateController {
    private SubmissionService submissionService;
    private ProblemService problemService;
    public Problem problem;
    private CurrentUser currentUser;

    @Autowired
    public SubmissionCreateController(SubmissionService submissionService,
                                      ProblemService problemService,
                                      CurrentUser currentUser) {
        this.submissionService = submissionService;
        this.problemService = problemService;
        this.currentUser = currentUser;
    }

    @GetMapping("/submission-create")
    public String submitCreate(@RequestParam(name = "name") String name, Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("submissionCreate")) {
            this.problem = this.problemService.findByName(name);
            model.addAttribute("submissionCreate", new SubmissionCreateDTO());
            model.addAttribute("problemName", this.problem.getName());
        }
        return "create-submission";
    }

    @PostMapping("/submission-create")
    public String submitCreate(@ModelAttribute("submissionCreate") SubmissionCreateDTO submissionCreate) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.submissionService.createSubmission(submissionCreate, this.problem);
        return "redirect:/home";
    }
}
