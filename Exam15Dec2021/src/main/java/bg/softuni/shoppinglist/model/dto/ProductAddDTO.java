package bg.softuni.shoppinglist.model.dto;

import bg.softuni.shoppinglist.model.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank
    @Size(min = 5)
    private String description;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;
    @Positive
    @NotNull
    private BigDecimal price;
    @NotNull
    private CategoryEnum category;

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

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
