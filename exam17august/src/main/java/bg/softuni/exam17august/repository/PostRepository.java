package bg.softuni.exam17august.repository;

import bg.softuni.exam17august.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserUsername(String username);

    List<Post> findAllByUserUsernameNot(String username);
}
