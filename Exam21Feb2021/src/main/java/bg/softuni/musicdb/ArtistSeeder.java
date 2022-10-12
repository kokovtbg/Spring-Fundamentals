package bg.softuni.musicdb;

import bg.softuni.musicdb.model.Artist;
import bg.softuni.musicdb.model.ArtistEnum;
import bg.softuni.musicdb.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ArtistSeeder implements CommandLineRunner {
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistSeeder(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.artistRepository.count() == 0) {
            Arrays.stream(ArtistEnum.values())
                    .map(Artist::new)
                    .forEach(a -> {
                        if (a.getName().toString().equals("Queen")) {
                            a.setCareerInformation("Queen are a British rock band formed in London in 1970. Their classic line-up was Freddie Mercury (lead vocals, piano), Brian May (guitar, vocals), Roger Taylor (drums, vocals) and John Deacon (bass). Their earliest works were influenced by progressive rock, hard rock and heavy metal, but the band gradually ventured into more conventional and radio-friendly works by incorporating further styles, such as arena rock and pop rock.");
                        }
                        if (a.getName().toString().equals("Metallica")) {
                            a.setCareerInformation("Metallica is an American heavy metal band. The band was formed in 1981 in Los Angeles by vocalist/guitarist James Hetfield and drummer Lars Ulrich, and has been based in San Francisco for most of its career. The band's fast tempos, instrumentals and aggressive musicianship made them one of the founding \"big four\" bands of thrash metal, alongside Megadeth, Anthrax and Slayer. Metallica's current lineup comprises founding members and primary songwriters Hetfield and Ulrich, longtime lead guitarist Kirk Hammett, and bassist Robert Trujillo. Guitarist Dave Mustaine and bassists Ron McGovney, Cliff Burton and Jason Newsted are former members of the band.");
                        }
                        if (a.getName().toString().equals("Madonna")) {
                            a.setCareerInformation("Madonna Louise Ciccone - born and raised in Michigan, Madonna moved to New York City in 1978 to pursue a career in modern dance. After performing as a drummer, guitarist, and vocalist in the rock bands Breakfast Club and Emmy, she rose to solo stardom with her debut studio album, Madonna (1983). She followed it with a series of successful albums, including all-time bestsellers Like a Virgin (1984) and True Blue (1986) as well as Grammy Award winners Ray of Light (1998) and Confessions on a Dance Floor (2005).");
                        }
                        this.artistRepository.save(a);
                    });
        }
    }
}
