package bg.softuni.exam25june.model.dto;

import bg.softuni.exam25june.model.Song;

import java.util.ArrayList;
import java.util.List;

public class UserAlbumDTO {
    private String username;
    private List<SongViewDTO> songs;

    public UserAlbumDTO() {
        this.songs = new ArrayList<>();
    }

    public void addSong(SongViewDTO songViewDTO) {
        this.songs.add(songViewDTO);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SongViewDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongViewDTO> songs) {
        this.songs = songs;
    }
}
