package bg.softuni.gira.web;

import bg.softuni.gira.model.Task;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private TaskService taskService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(TaskService taskService, CurrentUser currentUser) {
        this.taskService = taskService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("taskList")) {
            List<Task> taskList = this.taskService.findAll();
            model.addAttribute("taskList", taskList);
        }
        return "home";
    }

    @GetMapping("/progress")
    public String progress(@RequestParam(name = "id") long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.taskService.progressTask(id);
        return "redirect:/home";
    }
}
