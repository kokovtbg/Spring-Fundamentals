package bg.softuni.andrey.service;

import bg.softuni.andrey.model.Product;
import bg.softuni.andrey.model.User;
import bg.softuni.andrey.model.dto.ProductAddDTO;
import bg.softuni.andrey.repository.ProductRepository;
import bg.softuni.andrey.repository.UserRepository;
import bg.softuni.andrey.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          UserRepository userRepository,
                          CurrentUser currentUser) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void addProduct(ProductAddDTO addProduct) {
        Product product = new Product();
        product.setName(addProduct.getName());
        product.setDescription(addProduct.getDescription());
        product.setPrice(addProduct.getPrice());
        product.setCategory(addProduct.getCategory());
        product.setSex(addProduct.getSex());
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());
        product.setUser(user);
        this.productRepository.save(product);
    }

    public List<Product> findAllByCurrentUser() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());

        return this.productRepository.findAllByUser(user);
    }

    public void deleteById(long id) {
        this.productRepository.deleteById(id);
    }

    public Product findById(long id) {
        return this.productRepository.findById(id).get();
    }
}
