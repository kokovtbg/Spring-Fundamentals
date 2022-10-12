package bg.softuni.exam17august.service;

import bg.softuni.exam17august.model.Mood;
import bg.softuni.exam17august.model.Post;
import bg.softuni.exam17august.model.User;
import bg.softuni.exam17august.model.dto.PostAddDTO;
import bg.softuni.exam17august.model.dto.PostViewDTO;
import bg.softuni.exam17august.repository.MoodRepository;
import bg.softuni.exam17august.repository.PostRepository;
import bg.softuni.exam17august.repository.UserRepository;
import bg.softuni.exam17august.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private MoodRepository moodRepository;
    private CurrentUser currentUser;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, MoodRepository moodRepository, CurrentUser currentUser) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
        this.currentUser = currentUser;
    }

    public List<PostViewDTO> getAllOwnedPosts() {
        List<Post> posts = this.postRepository.findAllByUserUsername(this.currentUser.getUsername());
        return posts.stream()
                .map(p -> new PostViewDTO(p.getId(), p.getContent(), p.getUserLikes().size(), p.getMood().getName()))
                .collect(Collectors.toList());
    }

    public List<PostViewDTO> getAllTheirPosts() {
        List<Post> posts = this.postRepository.findAllByUserUsernameNot(this.currentUser.getUsername());
        return posts.stream()
                .map(p -> new PostViewDTO(p.getId(), p.getContent(), p.getUserLikes().size(), p.getMood().getName(), p.getUser().getUsername()))
                .collect(Collectors.toList());
    }

    public void addPost(PostAddDTO postAdd) {
        Post post = new Post();
        post.setContent(postAdd.getContent());
        Optional<User> optByUsername = this.userRepository.findByUsername(this.currentUser.getUsername());
        User user = optByUsername.get();
        post.setUser(user);
        Mood mood = this.moodRepository.findByName(postAdd.getMood());
        post.setMood(mood);
        this.postRepository.save(post);
    }

    public void likePost(long id) {
        Optional<Post> optPostById = this.postRepository.findById(id);
        Post post = optPostById.get();
        Optional<User> optUserByUsername = this.userRepository.findByUsername(this.currentUser.getUsername());
        User user = optUserByUsername.get();
        if (post.getUserLikes().contains(user)) {
            return;
        }
        post.addLike(user);
        this.postRepository.save(post);
    }

    @Transactional
    public void deletePost(long id) {
        this.postRepository.deleteById(id);
    }
}
