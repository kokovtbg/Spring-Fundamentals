package bg.softuni.shoppinglist.repository;

import bg.softuni.shoppinglist.model.CategoryEnum;
import bg.softuni.shoppinglist.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryName(CategoryEnum categoryEnum);
}
