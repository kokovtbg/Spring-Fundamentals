package bg.softuni.gira.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = "Email cant be empty")
    @Size(min = 3, max = 20, message = "Email must be between 3 and 20 characters")
    private String email;
    @NotBlank(message = "Password cant be empty")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
