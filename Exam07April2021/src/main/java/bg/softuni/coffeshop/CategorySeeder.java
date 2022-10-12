package bg.softuni.coffeshop;

import bg.softuni.coffeshop.model.Category;
import bg.softuni.coffeshop.model.CategoryEnum;
import bg.softuni.coffeshop.repository.CategoryRepository;
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
                    .forEach(c -> {
                        if (c.getName().toString().equals("DRINK")) {
                            c.setNeededTime(1);
                        }
                        if (c.getName().toString().equals("COFFEE")) {
                            c.setNeededTime(2);
                        }
                        if (c.getName().toString().equals("OTHER")) {
                            c.setNeededTime(5);
                        }
                        if (c.getName().toString().equals("CAKE")) {
                            c.setNeededTime(10);
                        }
                        this.categoryRepository.save(c);
                    });
        }
    }
}
