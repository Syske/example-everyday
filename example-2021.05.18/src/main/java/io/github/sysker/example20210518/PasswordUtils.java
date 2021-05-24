package io.github.sysker.example20210518;

import io.github.sysker.example20210518.annotation.UseCase;

import java.util.List;

/**
 * passwordUtils
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-19 7:49
 */
public class PasswordUtils {
    @UseCase(id = 47, description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }
    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
    @UseCase(id = 49, description = "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }
}
