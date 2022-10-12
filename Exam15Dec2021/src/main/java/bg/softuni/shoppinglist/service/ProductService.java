package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.model.Category;
import bg.softuni.shoppinglist.model.CategoryEnum;
import bg.softuni.shoppinglist.model.Product;
import bg.softuni.shoppinglist.model.dto.ProductAddDTO;
import bg.softuni.shoppinglist.model.dto.ProductViewDTO;
import bg.softuni.shoppinglist.repository.CategoryRepository;
import bg.softuni.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public void addProduct(ProductAddDTO addProduct) {
        Product product = new Product();
        product.setName(addProduct.getName());
        product.setDescription(addProduct.getDescription());
        product.setNeededBefore(addProduct.getNeededBefore());
        Category category = this.categoryRepository.findByName(addProduct.getCategory());
        product.setCategory(category);
        product.setPrice(addProduct.getPrice());
        this.productRepository.save(product);
    }

    public List<ProductViewDTO> getAllByCategory(CategoryEnum categoryEnum) {
        List<Product> products = this.productRepository.findAllByCategoryName(categoryEnum);
        return products.stream()
                .map(p -> new ProductViewDTO(p.getId(), p.getName(), p.getPrice())).toList();
    }

    @Transactional
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}
