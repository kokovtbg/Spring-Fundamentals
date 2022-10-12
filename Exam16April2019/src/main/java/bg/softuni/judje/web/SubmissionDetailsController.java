package bg.softuni.judje.web;

import bg.softuni.judje.model.Submission;
import bg.softuni.judje.model.dto.SubmissionCreateDTO;
import bg.softuni.judje.service.SubmissionService;
import bg.softuni.judje.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmissionDetailsController {
    private SubmissionService submissionService;
    private CurrentUser currentUser;

    @Autowired
    public SubmissionDetailsController(SubmissionService submissionService, CurrentUser currentUser) {
        this.submissionService = submissionService;
        this.currentUser = currentUser;
    }

    @GetMapping("/submission-details")
    public String submissionDetails(@RequestParam(name = "id") long id, Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("submissionDetails")) {
            Submission submissionDetails = this.submissionService.findById(id);
            model.addAttribute("submissionDetails", submissionDetails);
        }
        return "details-submission";
    }
}
