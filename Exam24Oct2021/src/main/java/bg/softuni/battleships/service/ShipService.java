package bg.softuni.battleships.service;

import bg.softuni.battleships.model.Category;
import bg.softuni.battleships.model.Ship;
import bg.softuni.battleships.model.User;
import bg.softuni.battleships.model.dto.ShipAddDTO;
import bg.softuni.battleships.model.dto.ShipViewDTO;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {
    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       UserRepository userRepository,
                       CurrentUser currentUser) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void addShip(ShipAddDTO shipAdd) {
        Ship ship = new Ship();
        ship.setName(shipAdd.getName());
        ship.setHealth(shipAdd.getHealth());
        ship.setPower(shipAdd.getPower());
        ship.setCreated(shipAdd.getCreated());
        Category category = this.categoryRepository.findByName(shipAdd.getCategory());
        ship.setCategory(category);
        Optional<User> optByUsername = this.userRepository.findByUsername(currentUser.getUsername());
        User user = null;
        if (optByUsername.isPresent()) {
            user = optByUsername.get();
        }
        ship.setUser(user);
        this.shipRepository.save(ship);
    }

    public boolean findByName(String name) {
        Optional<Ship> shipOptional = this.shipRepository.findByName(name);
        return shipOptional.isPresent();
    }

    public List<ShipViewDTO> getAllOwned(String username) {
        List<Ship> ships = this.shipRepository.findAllByUserUsername(username);
        return ships.stream()
                .map(s -> new ShipViewDTO(s.getId(), s.getName(), s.getHealth(), s.getPower()))
                .collect(Collectors.toList());
    }

    public List<ShipViewDTO> getAllTheir(String username) {
        List<Ship> ships = this.shipRepository.findAllByUserUsernameNot(username);
        return ships.stream()
                .map(s -> new ShipViewDTO(s.getId(), s.getName(), s.getHealth(), s.getPower()))
                .collect(Collectors.toList());
    }

    public boolean battle(long attackerId, long defenderId) {
        Optional<Ship> optAttacker = this.shipRepository.findById(attackerId);
        Ship attacker = null;
        if (optAttacker.isPresent()) {
            attacker = optAttacker.get();
        }
        Optional<Ship> optDefender = this.shipRepository.findById(defenderId);
        Ship defender = null;
        if (optDefender.isPresent()) {
            defender = optDefender.get();
        }
        if (attacker == null || defender == null) {
            return false;
        }
        defender.setHealth(defender.getHealth() - attacker.getPower());
        if (defender.getHealth() <= 0) {
            this.shipRepository.delete(defender);
        } else {
            this.shipRepository.save(defender);
        }
        return true;
    }
}
