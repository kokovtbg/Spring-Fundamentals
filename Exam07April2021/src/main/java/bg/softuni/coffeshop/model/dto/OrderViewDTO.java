package bg.softuni.coffeshop.model.dto;

import bg.softuni.coffeshop.model.CategoryEnum;

import java.math.BigDecimal;

public class OrderViewDTO {
    private long id;
    private String name;
    private BigDecimal price;
    private CategoryEnum category;

    public OrderViewDTO() {
    }

    public OrderViewDTO(long id, String name, BigDecimal price, CategoryEnum category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
