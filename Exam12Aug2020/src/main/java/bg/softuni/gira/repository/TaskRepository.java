package bg.softuni.gira.repository;

import bg.softuni.gira.model.Progress;
import bg.softuni.gira.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByName(String name);

    Task findByProgress(Progress open);
}
