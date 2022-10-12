package bg.softuni.judje.web;

import bg.softuni.judje.model.Problem;
import bg.softuni.judje.model.Submission;
import bg.softuni.judje.service.ProblemService;
import bg.softuni.judje.service.SubmissionService;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private ProblemService problemService;
    private SubmissionService submissionService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(ProblemService problemService,
                          SubmissionService submissionService,
                          CurrentUser currentUser) {
        this.problemService = problemService;
        this.submissionService = submissionService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("problemSubmission")) {
            List<Problem> problemList = this.problemService.findAll();

            List<Submission> submissionList;
            Map<String, Integer> problemSubmission = new LinkedHashMap<>();
            for (Problem problem : problemList) {
                submissionList = this.submissionService.findAllByUserAndProblem(problem);
                int submissionMax = submissionList.stream().mapToInt(Submission::getAchievedResult).max().orElse(0);
                problemSubmission.put(problem.getName(), submissionMax);
            }
            model.addAttribute("problemSubmission", problemSubmission);
        }

        return "home";
    }
}
