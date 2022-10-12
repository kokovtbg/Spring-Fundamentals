package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.User;
import bg.softuni.musicdb.model.dto.UserLoginDTO;
import bg.softuni.musicdb.model.dto.UserRegisterDTO;
import bg.softuni.musicdb.repository.UserRepository;
import bg.softuni.musicdb.user.CurrentUser;
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

    public boolean findUserByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        }
        return true;
    }

    public void login(UserLoginDTO userLoginDTO) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setUsername(userLoginDTO.getUsername());
    }

    public boolean findByUsernameAndEmailAndConfirmPassword(UserRegisterDTO userRegisterDTO) {
        User byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        if (byUsername != null) {
            return false;
        }
        User userByEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());
        if (userByEmail != null) {
            return false;
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    public void register(UserRegisterDTO userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setFullName(userRegister.getFullName());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        this.userRepository.save(user);
    }

    public void logout() {
        this.currentUser.clear();
    }
}
