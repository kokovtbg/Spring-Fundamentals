package bg.softuni.exam17august.model.dto;

import bg.softuni.exam17august.model.MoodEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostAddDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String content;
    @NotNull
    private MoodEnum mood;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public void setMood(MoodEnum mood) {
        this.mood = mood;
    }
}
