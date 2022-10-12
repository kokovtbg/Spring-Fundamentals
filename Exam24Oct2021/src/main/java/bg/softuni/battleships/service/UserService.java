package bg.softuni.battleships.service;

import bg.softuni.battleships.model.User;
import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegisterDTO;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public boolean findByUsernameAndPassword(String username, String password) {
        Optional<User> optionalUser = this.userRepository.findByUsernameAndPassword(username, password);
        return optionalUser.isPresent();
    }

    public void login(UserLoginDTO userLogin) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setUsername(userLogin.getUsername());
    }

    public void logout() {
        this.currentUser.clear();
    }

    public boolean findByUsernameAndEmail(String username, String email) {
        Optional<User> optByUsername = this.userRepository.findByUsername(username);
        if (optByUsername.isPresent()) {
            return true;
        }
        Optional<User> optByEmail = this.userRepository.findByEmail(email);
        if (optByEmail.isPresent()) {
            return true;
        }
        return false;
    }

    public void register(UserRegisterDTO userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setFullName(userRegister.getFullName());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        this.userRepository.save(user);
    }
}
