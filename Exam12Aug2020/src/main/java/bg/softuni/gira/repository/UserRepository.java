package bg.softuni.gira.repository;

import bg.softuni.gira.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByUsername(String username);

    User findByEmail(String email);
}
