package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.model.CategoryEnum;
import bg.softuni.shoppinglist.model.dto.ProductViewDTO;
import bg.softuni.shoppinglist.service.ProductService;
import bg.softuni.shoppinglist.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
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
    public String getHome(Model model) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        List<ProductViewDTO> foodProducts = this.productService.getAllByCategory(CategoryEnum.FOOD);
        model.addAttribute("food", foodProducts);
        List<ProductViewDTO> drinkProducts = this.productService.getAllByCategory(CategoryEnum.DRINK);
        model.addAttribute("drink", drinkProducts);
        List<ProductViewDTO> householdProducts = this.productService.getAllByCategory(CategoryEnum.HOUSEHOLD);
        model.addAttribute("household", householdProducts);
        List<ProductViewDTO> otherProducts = this.productService.getAllByCategory(CategoryEnum.OTHER);
        model.addAttribute("other", otherProducts);
        BigDecimal sum = new BigDecimal(String.valueOf(BigDecimal.ZERO));
        List<BigDecimal> bigDecimals = foodProducts.stream().map(ProductViewDTO::getPrice).toList();
        for (BigDecimal p : bigDecimals) {
            sum = sum.add(p);
        }
        bigDecimals = drinkProducts.stream().map(ProductViewDTO::getPrice).toList();
        for (BigDecimal p : bigDecimals) {
            sum = sum.add(p);
        }
        bigDecimals = householdProducts.stream().map(ProductViewDTO::getPrice).toList();
        for (BigDecimal p : bigDecimals) {
            sum = sum.add(p);
        }
        bigDecimals = otherProducts.stream().map(ProductViewDTO::getPrice).toList();
        for (BigDecimal p : bigDecimals) {
            sum = sum.add(p);
        }
        model.addAttribute("sum", sum);
        return "home";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteProduct(@PathVariable("id") long id) {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.deleteProduct(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/deleteAll", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteAll() {
        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.deleteAll();
        return "redirect:/home";
    }
}
