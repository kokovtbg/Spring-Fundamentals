package bg.softuni.exam17august.service;

import bg.softuni.exam17august.model.User;
import bg.softuni.exam17august.model.dto.UserLoginDTO;
import bg.softuni.exam17august.model.dto.UserRegisterDTO;
import bg.softuni.exam17august.repository.UserRepository;
import bg.softuni.exam17august.user.CurrentUser;
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
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        this.userRepository.save(user);
    }
}
