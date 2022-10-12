package bg.softuni.musicdb.repository;

import bg.softuni.musicdb.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByAddedFromUsernameOrderByCopiesDesc(String username);

}
