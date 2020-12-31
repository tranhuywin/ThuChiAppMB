package com.example.thuchiapp.data;

import com.example.thuchiapp.DAL.DatabaseHelper;
import com.example.thuchiapp.data.model.LoggedInUser;
import com.example.thuchiapp.ui.login.LoginActivity;
import com.example.thuchiapp.ui.login.LoginViewModel;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            LoggedInUser fakeUser = new LoggedInUser("admin@thuchi.com", "admin",1,"admin");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}