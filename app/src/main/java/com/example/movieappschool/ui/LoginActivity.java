package com.example.movieappschool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieappschool.MainActivity;
import com.example.movieappschool.R;
import com.example.movieappschool.data.LocalAppStorage;
import com.example.movieappschool.data.LoginService;
import com.example.movieappschool.domain.User;

public class LoginActivity extends AppCompatActivity{
    LoginService login = new LoginService();
    private LocalAppStorage localAppStorage;
    private Button mLoginButton;
    private EditText mUsernameInput, mPasswordInput;
    private User mUser;

    public LoginActivity() {
        localAppStorage = (LocalAppStorage) this.getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mLoginButton = findViewById(R.id.loginButton);
        mUsernameInput = findViewById(R.id.editUsername);
        mPasswordInput = findViewById(R.id.editPassword);

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Username = mUsernameInput.getText().toString();
                String Password = mPasswordInput.getText().toString();

                mUser = login.executeLogin(Username, Password);
                if (mUser != null) {
                    localAppStorage.setUser(mUser);
                    localAppStorage.setLoggedIn();
                    Intent i = new Intent(LoginActivity.this, AccountActivity.class);
                    startActivity(i);
                }
            }
        });
    }

}
