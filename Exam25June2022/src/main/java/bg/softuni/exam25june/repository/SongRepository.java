package bg.softuni.exam25june.repository;

import bg.softuni.exam25june.model.Song;
import bg.softuni.exam25june.model.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByPerformer(String performer);

    List<Song> findAllByStyleStyleName(StyleName pop);

    Song findByTitle(String title);
}
