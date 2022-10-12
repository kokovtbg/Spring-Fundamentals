package bg.softuni.coffeshop.web;

import bg.softuni.coffeshop.model.dto.EmployeeOrderDTO;
import bg.softuni.coffeshop.model.dto.OrderViewDTO;
import bg.softuni.coffeshop.service.OrderService;
import bg.softuni.coffeshop.service.UserService;
import bg.softuni.coffeshop.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    private OrderService orderService;
    private UserService userService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(OrderService orderService,
                          UserService userService,
                          CurrentUser currentUser) {
        this.orderService = orderService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        List<OrderViewDTO> orderViewDTOs = this.orderService.getAllSorted();
        model.addAttribute("orders", orderViewDTOs);
        int timeNeeded = this.orderService.getAllTimeNeeded();
        model.addAttribute("time", timeNeeded);
        List<EmployeeOrderDTO> employeeOrderDTOs = this.userService.getAllSorted();
        model.addAttribute("employees", employeeOrderDTOs);
        return "home";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteProduct(@PathVariable("id") long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        this.orderService.delete(id);
        return "redirect:/home";
    }
}
