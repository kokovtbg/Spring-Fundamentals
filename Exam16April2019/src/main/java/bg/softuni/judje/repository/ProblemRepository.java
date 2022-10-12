package bg.softuni.judje.repository;

import bg.softuni.judje.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Problem findByName(String name);
}
