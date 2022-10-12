package bg.softuni.exam17august.repository;

import bg.softuni.exam17august.model.Mood;
import bg.softuni.exam17august.model.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
    Mood findByName(MoodEnum mood);
}
