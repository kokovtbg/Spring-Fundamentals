package bg.softuni.gira.model.dto;

import bg.softuni.gira.model.ClassificationName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddDTO {
    @NotBlank
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    private String name;
    @NotBlank
    @Size(min = 5, message = "Description length must be more than 5 characters")
    private String description;
    @FutureOrPresent(message = "The date cannot be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @NotNull(message = "Classification cannot be null")
    private ClassificationName classification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public ClassificationName getClassification() {
        return classification;
    }

    public void setClassification(ClassificationName classification) {
        this.classification = classification;
    }
}
