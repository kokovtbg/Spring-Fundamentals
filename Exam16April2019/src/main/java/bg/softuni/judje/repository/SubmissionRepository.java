package bg.softuni.judje.repository;

import bg.softuni.judje.model.Problem;
import bg.softuni.judje.model.Submission;
import bg.softuni.judje.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findAllByUserAndProblem(User user, Problem problem);
}
