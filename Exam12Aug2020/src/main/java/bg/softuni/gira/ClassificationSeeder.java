package bg.softuni.gira;

import bg.softuni.gira.model.Classification;
import bg.softuni.gira.model.ClassificationName;
import bg.softuni.gira.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ClassificationSeeder implements CommandLineRunner {
    private ClassificationRepository classificationRepository;

    @Autowired
    public ClassificationSeeder(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.classificationRepository.count() == 0) {
            Arrays.stream(ClassificationName.values())
                    .map(Classification::new)
                    .forEach(c -> this.classificationRepository.save(c));
        }
    }
}
