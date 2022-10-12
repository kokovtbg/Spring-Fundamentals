package bg.softuni.coffeshop.repository;

import bg.softuni.coffeshop.model.Category;
import bg.softuni.coffeshop.model.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryEnum category);
}
