package bg.softuni.heroes.model.dto;

import bg.softuni.heroes.model.HeroClass;

public class HeroAddDTO {
    private String name;
    private HeroClass heroClass;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
