package bg.softuni.exam25june.model.dto;

import bg.softuni.exam25june.model.StyleName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongAddDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String performer;
    @NotBlank
    @Size(min = 2, max = 20)
    private String title;
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Positive
    private int duration;
    @NotNull
    private StyleName style;

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public StyleName getStyle() {
        return style;
    }

    public void setStyle(StyleName style) {
        this.style = style;
    }
}
