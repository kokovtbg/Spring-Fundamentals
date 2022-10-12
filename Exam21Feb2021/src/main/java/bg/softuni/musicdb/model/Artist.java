package bg.softuni.musicdb.model;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArtistEnum name;
    @Column(columnDefinition = "text")
    private String careerInformation;

    public Artist() {
    }

    public Artist(ArtistEnum name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArtistEnum getName() {
        return name;
    }

    public void setName(ArtistEnum name) {
        this.name = name;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
