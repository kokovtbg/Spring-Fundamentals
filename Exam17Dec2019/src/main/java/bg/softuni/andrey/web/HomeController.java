package bg.softuni.andrey.web;

import bg.softuni.andrey.model.Product;
import bg.softuni.andrey.service.ProductService;
import bg.softuni.andrey.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private ProductService productService;
    private CurrentUser currentUser;

    @Autowired
    public HomeController(ProductService productService, CurrentUser currentUser) {
        this.productService = productService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("productList")) {
            List<Product> productList = this.productService.findAllByCurrentUser();
            model.addAttribute("productList", productList);
        }
        return "home";
    }

    @GetMapping("/details")
    public String details(@RequestParam(name = "id") long id, Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("detailProduct")) {
            Product detailProduct = this.productService.findById(id);
            model.addAttribute("detailProduct", detailProduct);
        }

        return "details-product";
    }

    @PostMapping("/details")
    public String delete(@RequestParam(name = "id") long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.deleteById(id);
        return "redirect:/home";
    }
}
