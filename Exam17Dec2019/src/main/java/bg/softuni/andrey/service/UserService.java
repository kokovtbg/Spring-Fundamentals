package bg.softuni.andrey.service;

import bg.softuni.andrey.model.User;
import bg.softuni.andrey.model.dto.UserRegisterDTO;
import bg.softuni.andrey.user.CurrentUser;
import bg.softuni.andrey.model.dto.UserLoginDTO;
import bg.softuni.andrey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void login(UserLoginDTO userLoginDTO) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setUsername(userLoginDTO.getUsername());
    }

    public void register(UserRegisterDTO userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        this.userRepository.save(user);
    }

    public void logout() {
        this.currentUser.clear();
    }
}
