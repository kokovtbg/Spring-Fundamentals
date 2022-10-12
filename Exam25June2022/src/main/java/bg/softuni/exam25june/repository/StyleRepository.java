package bg.softuni.exam25june.repository;

import bg.softuni.exam25june.model.Style;
import bg.softuni.exam25june.model.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    Style findByStyleName(StyleName style);
}
