package bg.softuni.musicdb.repository;

import bg.softuni.musicdb.model.Artist;
import bg.softuni.musicdb.model.ArtistEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(ArtistEnum artist);
}
