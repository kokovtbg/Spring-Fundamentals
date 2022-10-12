package bg.softuni.exam17august;

import bg.softuni.exam17august.model.Mood;
import bg.softuni.exam17august.model.MoodEnum;
import bg.softuni.exam17august.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MoodSeeder implements CommandLineRunner {
    private MoodRepository moodRepository;

    @Autowired
    public MoodSeeder(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.moodRepository.count() == 0) {
            Arrays.stream(MoodEnum.values())
                    .map(Mood::new)
                    .forEach(m -> this.moodRepository.save(m));
        }
    }
}
