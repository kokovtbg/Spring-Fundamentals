package bg.softuni.exam25june;

import bg.softuni.exam25june.model.Style;
import bg.softuni.exam25june.model.StyleName;
import bg.softuni.exam25june.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StyleSeeder implements CommandLineRunner {
    private StyleRepository styleRepository;

    @Autowired
    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() == 0) {
            Arrays.stream(StyleName.values())
                    .map(Style::new)
                    .forEach(s -> styleRepository.save(s));
        }
    }
}
