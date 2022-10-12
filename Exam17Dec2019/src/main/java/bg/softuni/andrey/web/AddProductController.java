package bg.softuni.andrey.web;

import bg.softuni.andrey.model.dto.ProductAddDTO;
import bg.softuni.andrey.service.ProductService;
import bg.softuni.andrey.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {
    private ProductService productService;
    private CurrentUser currentUser;

    @Autowired
    public AddProductController(ProductService productService, CurrentUser currentUser) {
        this.productService = productService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add-product")
    public String product(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("addProduct")) {
            model.addAttribute("addProduct", new ProductAddDTO());
        }
        return "add-product";
    }

    @PostMapping("/add-product")
    public String product(@ModelAttribute("addProduct") ProductAddDTO addProduct) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        this.productService.addProduct(addProduct);
        return "redirect:/home";

    }
}
