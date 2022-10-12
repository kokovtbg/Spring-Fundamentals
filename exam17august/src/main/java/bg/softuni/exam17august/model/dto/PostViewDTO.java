package bg.softuni.exam17august.model.dto;

import bg.softuni.exam17august.model.MoodEnum;

public class PostViewDTO {
    private long id;
    private String content;
    private int likes;
    private MoodEnum mood;
    private String username;

    public PostViewDTO() {
    }

    public PostViewDTO(long id, String content, int likes, MoodEnum mood) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.mood = mood;
    }

    public PostViewDTO(long id, String content, int likes, MoodEnum mood, String username) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.mood = mood;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public void setMood(MoodEnum mood) {
        this.mood = mood;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
