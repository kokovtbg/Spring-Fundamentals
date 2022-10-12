package bg.softuni.battleships.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank
    @Size(min = 3, max = 10)
    private String username;
    @NotBlank
    @Size(min = 3)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
