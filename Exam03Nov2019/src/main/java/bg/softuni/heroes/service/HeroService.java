package bg.softuni.heroes.service;

import bg.softuni.heroes.model.Hero;
import bg.softuni.heroes.model.User;
import bg.softuni.heroes.model.dto.HeroAddDTO;
import bg.softuni.heroes.repository.HeroRepository;
import bg.softuni.heroes.repository.UserRepository;
import bg.softuni.heroes.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    private HeroRepository heroRepository;
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public HeroService(HeroRepository heroRepository,
                       UserRepository userRepository,
                       CurrentUser currentUser) {
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }


    public void addHero(HeroAddDTO addHero) {
        Hero hero = new Hero();
        hero.setName(addHero.getName());
        hero.setHeroClass(addHero.getHeroClass());
        hero.setLevel(addHero.getLevel());
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());
        hero.setUser(user);
        this.heroRepository.save(hero);
    }

    public List<Hero> findAllByCurrentUser() {
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());
        return user.getHeroes();
    }

    public Hero findById(long id) {
        return this.heroRepository.findById(id).get();
    }

    public void deleteById(long id) {
        this.heroRepository.deleteById(id);
    }
}
