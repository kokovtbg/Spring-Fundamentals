package bg.softuni.musicdb.service;

import bg.softuni.musicdb.model.Album;
import bg.softuni.musicdb.model.Artist;
import bg.softuni.musicdb.model.User;
import bg.softuni.musicdb.model.dto.AlbumAddDTO;
import bg.softuni.musicdb.repository.AlbumRepository;
import bg.softuni.musicdb.repository.ArtistRepository;
import bg.softuni.musicdb.repository.UserRepository;
import bg.softuni.musicdb.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;
    private CurrentUser currentUser;
    private UserRepository userRepository;
    private ArtistRepository artistRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository,
                        CurrentUser currentUser,
                        UserRepository userRepository,
                        ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
    }

    public void addAlbum(AlbumAddDTO addAlbum) {
        Album album = new Album();
        album.setName(addAlbum.getName());
        album.setImageUrl(addAlbum.getImageUrl());
        album.setDescription(addAlbum.getDescription());
        album.setCopies(addAlbum.getCopies());
        album.setPrice(addAlbum.getPrice());
        album.setReleaseDate(addAlbum.getReleaseDate());
        album.setProducer(addAlbum.getProducer());
        album.setGenre(addAlbum.getGenre());
        User user = this.userRepository.findByUsername(this.currentUser.getUsername());
        album.setAddedFrom(user);
        Artist artist = this.artistRepository.findByName(addAlbum.getArtist());
        album.setArtist(artist);
        this.albumRepository.save(album);
    }

    public List<Album> findAllByUserAndSort(String username) {
        return this.albumRepository.findAllByAddedFromUsernameOrderByCopiesDesc(username);
    }

    public void delete(long id) {
        Album album = this.albumRepository.findById(id).get();
        this.albumRepository.delete(album);
    }
}
