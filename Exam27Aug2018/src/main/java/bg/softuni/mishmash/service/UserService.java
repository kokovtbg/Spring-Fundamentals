package bg.softuni.mishmash.service;

import bg.softuni.mishmash.model.Role;
import bg.softuni.mishmash.model.User;
import bg.softuni.mishmash.model.dto.UserLoginDTO;
import bg.softuni.mishmash.model.dto.UserRegisterDTO;
import bg.softuni.mishmash.repository.UserRepository;
import bg.softuni.mishmash.user.CurrentUser;
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
        if (userRegister.getUsername().equals("koko_sgi")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        this.userRepository.save(user);
    }

    public void logout() {
        this.currentUser.clear();
    }
}
