package bg.softuni.exam25june.service;

import bg.softuni.exam25june.model.Song;
import bg.softuni.exam25june.model.Style;
import bg.softuni.exam25june.model.StyleName;
import bg.softuni.exam25june.model.dto.SongAddDTO;
import bg.softuni.exam25june.model.dto.SongViewDTO;
import bg.softuni.exam25june.repository.SongRepository;
import bg.softuni.exam25june.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    private SongRepository songRepository;
    private StyleRepository styleRepository;

    @Autowired
    public SongService(SongRepository songRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
    }

    public boolean findByPerformer(String performer) {
        Song songByPerformer = this.songRepository.findByPerformer(performer);
        if (songByPerformer != null) {
            return false;
        }
        return true;
    }

    public void addSong(SongAddDTO addSong) {
        Song song = new Song();
        song.setPerformer(addSong.getPerformer());
        song.setDuration(addSong.getDuration());
        song.setTitle(addSong.getTitle());
        song.setReleaseDate(addSong.getReleaseDate());
        Style style = this.styleRepository.findByStyleName(addSong.getStyle());
        song.setStyle(style);
        this.songRepository.save(song);
    }

    public List<SongViewDTO> findAllByStylePop() {
        List<Song> songsPop = this.songRepository.findAllByStyleStyleName(StyleName.POP);
        List<SongViewDTO> songViewDTOs = new ArrayList<>();
        convertSongs(songsPop, songViewDTOs);
        return songViewDTOs;
    }

    public List<SongViewDTO> findAllByStyleRock() {
        List<Song> songsRock = this.songRepository.findAllByStyleStyleName(StyleName.ROCK);
        List<SongViewDTO> songViewDTOs = new ArrayList<>();
        convertSongs(songsRock, songViewDTOs);
        return songViewDTOs;

    }

    public List<SongViewDTO> findAllByStyleJazz() {
        List<Song> songsJazz = this.songRepository.findAllByStyleStyleName(StyleName.JAZZ);
        List<SongViewDTO> songViewDTOs = new ArrayList<>();
        convertSongs(songsJazz, songViewDTOs);
        return songViewDTOs;
    }

    private void convertSongs(List<Song> songs, List<SongViewDTO> songViewDTOs) {
        songs.forEach(s -> {
            SongViewDTO songViewDTO = new SongViewDTO();
            songViewDTO.setPerformer(s.getPerformer());
            songViewDTO.setTitle(s.getTitle());
            songViewDTO.setDuration(s.getDuration());
            songViewDTOs.add(songViewDTO);
        });
    }

}
