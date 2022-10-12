package bg.softuni.gira.web;

import bg.softuni.gira.model.dto.TaskAddDTO;
import bg.softuni.gira.service.TaskService;
import bg.softuni.gira.user.CurrentUser;
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
public class AddTaskController {
    private TaskService taskService;
    private CurrentUser currentUser;

    @Autowired
    public AddTaskController(TaskService taskService, CurrentUser currentUser) {
        this.taskService = taskService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add-task")
    public String task(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("addTask")) {
            model.addAttribute("addTask", new TaskAddDTO());
        }
        return "add-task";
    }

    @PostMapping("/add-task")
    public String task(@Valid @ModelAttribute("addTask") TaskAddDTO addTask,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors() || !this.taskService.checkForNameAndProgress(addTask)) {
            redirectAttributes.addFlashAttribute("addTask", addTask);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTask", bindingResult);
            return "redirect:/add-task";
        }

        this.taskService.addTask(addTask);
        return "redirect:/home";

    }
}
