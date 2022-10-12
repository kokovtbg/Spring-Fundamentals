package bg.softuni.battleships.model.dto;

public class ShipViewDTO {
    private long id;
    private String name;
    private long health;
    private long power;

    public ShipViewDTO() {
    }

    public ShipViewDTO(long id, String name, long health, long power) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.power = power;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }
}
