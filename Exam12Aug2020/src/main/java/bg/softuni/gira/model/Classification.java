package bg.softuni.gira.model;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private ClassificationName classificationName;
    private String description;

    public Classification() {
    }

    public Classification(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
