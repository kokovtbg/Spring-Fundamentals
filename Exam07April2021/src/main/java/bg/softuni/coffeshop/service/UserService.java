package bg.softuni.coffeshop.service;

import bg.softuni.coffeshop.model.User;
import bg.softuni.coffeshop.model.dto.EmployeeOrderDTO;
import bg.softuni.coffeshop.model.dto.UserLoginDTO;
import bg.softuni.coffeshop.model.dto.UserRegisterDTO;
import bg.softuni.coffeshop.repository.UserRepository;
import bg.softuni.coffeshop.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public boolean findUserByUsernameAndPassword(String username, String password) {
        Optional<User> optUser = this.userRepository.findByUsernameAndPassword(username, password);
        return optUser.isPresent();
    }

    public void login(UserLoginDTO userLogin) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setUsername(userLogin.getUsername());
    }

    public void logout() {
        this.currentUser.clear();
    }

    public boolean findByUsernameAndEmail(UserRegisterDTO userRegister) {
        Optional<User> optByUsername = this.userRepository.findByUsername(userRegister.getUsername());
        if (optByUsername.isPresent()) {
            return true;
        }
        Optional<User> optByEmail = this.userRepository.findByEmail(userRegister.getEmail());
        if (optByEmail.isPresent()) {
            return true;
        }
        return false;
    }

    public void register(UserRegisterDTO userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setFirstName(userRegister.getFirstName());
        user.setLastName(userRegister.getLastName());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        this.userRepository.save(user);
    }

    public List<EmployeeOrderDTO> getAllSorted() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(u -> new EmployeeOrderDTO(u.getUsername(), u.getOrders().size()))
                .sorted((u1, u2) -> Integer.compare(u2.getOrdersCount(), u1.getOrdersCount()))
                .collect(Collectors.toList());
    }
}
