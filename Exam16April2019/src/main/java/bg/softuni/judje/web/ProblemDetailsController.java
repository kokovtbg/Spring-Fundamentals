package bg.softuni.judje.web;

import bg.softuni.judje.model.Problem;
import bg.softuni.judje.model.Submission;
import bg.softuni.judje.service.ProblemService;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProblemDetailsController {
    private ProblemService problemService;
    private CurrentUser currentUser;

    @Autowired
    public ProblemDetailsController(ProblemService problemService, CurrentUser currentUser) {
        this.problemService = problemService;
        this.currentUser = currentUser;
    }

    @GetMapping("/problem-details")
    public String problemDetails(@RequestParam(name = "name") String name, Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("problemDetails")) {
            Problem problemDetails = this.problemService.findByName(name);
            List<Submission> submissions = problemDetails.getSubmissions();
            int count = 0;
            for (Submission submission : submissions) {
                if (submission.getAchievedResult() == problemDetails.getPoints()) {
                    count++;
                }
            }
            double successRate = 0;
            if (submissions.size() != 0) {
                successRate = count * 100.0 / submissions.size();
            }
            model.addAttribute("problemDetails", problemDetails);
            model.addAttribute("successRate", successRate);
        }
        return "details-problem";
    }
}
