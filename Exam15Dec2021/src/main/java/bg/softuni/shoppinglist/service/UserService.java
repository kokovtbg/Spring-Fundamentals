package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.model.User;
import bg.softuni.shoppinglist.model.dto.UserLoginDTO;
import bg.softuni.shoppinglist.model.dto.UserRegisterDTO;
import bg.softuni.shoppinglist.repository.UserRepository;
import bg.softuni.shoppinglist.user.CurrentUser;
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
        Optional<User> optByUsernameAndPassword = this.userRepository.findByUsernameAndPassword(username, password);
        return optByUsernameAndPassword.isPresent();
    }

    public void login(UserLoginDTO userLogin) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setUsername(userLogin.getUsername());
    }

    public void logout() {
        this.currentUser.clear();
    }

    public boolean findUserByUsername(String username) {
        Optional<User> optByUsername = this.userRepository.findByUsername(username);
        return optByUsername.isPresent();
    }

    public void register(UserRegisterDTO userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        this.userRepository.save(user);
    }
}
