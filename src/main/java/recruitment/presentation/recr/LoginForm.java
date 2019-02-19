package recruitment.presentation.recr;

import recruitment.util.Util;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

class LoginForm {

    @NotEmpty
    @NotBlank(message = "Please submit your username.")
    private String username;

    @NotEmpty
    @NotBlank(message = "Please submit your password.")
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

    @Override
    public String toString() {
        return Util.toString(this);
    }
}
