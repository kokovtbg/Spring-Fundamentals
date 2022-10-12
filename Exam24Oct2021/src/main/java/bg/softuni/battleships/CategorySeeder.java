package bg.softuni.battleships;

import bg.softuni.battleships.model.Category;
import bg.softuni.battleships.model.CategoryEnum;
import bg.softuni.battleships.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .map(Category::new)
                    .forEach(c -> this.categoryRepository.save(c));
        }
    }
}
