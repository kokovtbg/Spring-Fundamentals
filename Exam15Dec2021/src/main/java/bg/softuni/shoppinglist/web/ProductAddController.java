package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.model.dto.ProductAddDTO;
import bg.softuni.shoppinglist.service.ProductService;
import bg.softuni.shoppinglist.user.CurrentUser;
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
public class ProductAddController {
    private ProductService productService;
    private CurrentUser currentUser;

    @Autowired
    public ProductAddController(ProductService productService, CurrentUser currentUser) {
        this.productService = productService;
        this.currentUser = currentUser;
    }

    @GetMapping("/product-add")
    public String getProductAdd(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (!model.containsAttribute("addProduct")) {
            model.addAttribute("addProduct", new ProductAddDTO());
        }
        return "product-add";
    }

    @PostMapping("/product-add")
    public String postProductAdd(@Valid @ModelAttribute("addProduct") ProductAddDTO addProduct,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProduct", addProduct);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProduct", bindingResult);
            return "redirect:/product-add";
        }
        this.productService.addProduct(addProduct);
        return "redirect:/home";
    }
}
