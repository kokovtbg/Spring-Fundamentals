package bg.softuni.exam25june.service;

import bg.softuni.exam25june.model.Song;
import bg.softuni.exam25june.model.User;
import bg.softuni.exam25june.model.dto.SongViewDTO;
import bg.softuni.exam25june.model.dto.UserAlbumDTO;
import bg.softuni.exam25june.model.dto.UserLoginDTO;
import bg.softuni.exam25june.model.dto.UserRegisterDTO;
import bg.softuni.exam25june.repository.SongRepository;
import bg.softuni.exam25june.repository.UserRepository;
import bg.softuni.exam25june.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private SongRepository songRepository;
    private CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository, SongRepository songRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.currentUser = currentUser;
    }

    public boolean findUserByUsernameAndPassword(String username, String password) {
        User userByUsernameAndPassword = this.userRepository.findByUsernameAndPassword(username, password);
        if (userByUsernameAndPassword == null) {
            return false;
        }
        return true;
    }

    public boolean findByUsernameAndEmailAndConfirmPassword(UserRegisterDTO userRegister) {
        User byUsername = this.userRepository.findByUsername(userRegister.getUsername());
        if (byUsername != null) {
            return false;
        }
        User userByPassword = this.userRepository.findByEmail(userRegister.getEmail());
        if (userByPassword != null) {
            return false;
        }
        if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    public void login(UserLoginDTO userLogin) {
        this.currentUser.setLoggedIn(true);
        this.currentUser.setUsername(userLogin.getUsername());
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

    public UserAlbumDTO findByUsernameAndAddSong(String username, String title) {
        User userByUsername = this.userRepository.findByUsername(username);
        if (title == null) {
            UserAlbumDTO userAlbumDTO = new UserAlbumDTO();
            userAlbumDTO.setUsername(username);
            List<SongViewDTO> songViewDTOList = new ArrayList<>();
            List<Song> songs = userByUsername.getSongs();
            songs.forEach(s -> {
                SongViewDTO songViewDTO = new SongViewDTO();
                songViewDTO.setPerformer(s.getPerformer());
                songViewDTO.setTitle(s.getTitle());
                songViewDTO.setDuration(s.getDuration());
                songViewDTOList.add(songViewDTO);
            });
            userAlbumDTO.setSongs(songViewDTOList);
            return userAlbumDTO;
        }
        Song song = this.songRepository.findByTitle(title);
        userByUsername.addSong(song);
        userRepository.save(userByUsername);
        UserAlbumDTO userAlbumDTO = new UserAlbumDTO();
        userAlbumDTO.setUsername(userByUsername.getUsername());
        List<SongViewDTO> songViewDTOList = new ArrayList<>();
        userByUsername = this.userRepository.findByUsername(username);
        List<Song> songs = userByUsername.getSongs();
        songs.forEach(s -> {
            SongViewDTO songViewDTO = new SongViewDTO();
            songViewDTO.setPerformer(s.getPerformer());
            songViewDTO.setTitle(s.getTitle());
            songViewDTO.setDuration(s.getDuration());
            songViewDTOList.add(songViewDTO);
        });
        userAlbumDTO.setSongs(songViewDTOList);
        return userAlbumDTO;
    }

    public void deletePlaylist(String username) {
        User byUsername = this.userRepository.findByUsername(username);
        byUsername.clearSongs();
        this.userRepository.save(byUsername);
    }
}
