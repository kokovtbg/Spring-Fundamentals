package bg.softuni.coffeshop.web;

import bg.softuni.coffeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeshop.service.OrderService;
import bg.softuni.coffeshop.user.CurrentUser;
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
public class OrderAddController {
    private OrderService orderService;
    private CurrentUser currentUser;

    @Autowired
    public OrderAddController(OrderService orderService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.currentUser = currentUser;
    }

    @GetMapping("/order-add")
    public String getOrderAdd(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("orderAdd")) {
            model.addAttribute("orderAdd", new OrderAddDTO());
        }
        return "order-add";
    }

    @PostMapping("/order-add")
    public String postOrderAdd(@Valid @ModelAttribute("orderAdd") OrderAddDTO orderAdd,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAdd", orderAdd);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAdd", bindingResult);
            return "redirect:/order-add";
        }

        this.orderService.addOrder(orderAdd);
        return "redirect:/home";
    }
}
