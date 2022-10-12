package bg.softuni.exam25june.repository;

import bg.softuni.exam25june.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByEmail(String email);
}
