package bg.softuni.gira.repository;

import bg.softuni.gira.model.Classification;
import bg.softuni.gira.model.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findByClassificationName(ClassificationName classification);
}
