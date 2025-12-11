package com.epikent.crud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword; // Password field is now dummy
    private Button btnLogin, btnGoToRegister;
    private SessionManager sessionManager;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        if (sessionManager.getAuthToken() != null) {
            goToHomePage();
            return;
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);

        btnLogin.setOnClickListener(v -> attemptLoginValidation());
        btnGoToRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void attemptLoginValidation() {
        final String inputEmail = etEmail.getText().toString().trim();
        // Password input is ignored (dummy)

        if (inputEmail.isEmpty()) {
            Toast.makeText(this, "Enter an email to check availability", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. Fetch the list of users from Reqres
        apiInterface.getUsers().enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> userList = response.body().getData();
                    boolean emailFound = false;

                    // 2. Iterate the list and check for email match
                    for (User user : userList) {
                        if (user.getEmail().equalsIgnoreCase(inputEmail)) {
                            emailFound = true;
                            // In this dummy scenario, finding the email means "successful login"
                            // We save a dummy token to manage the session
                            sessionManager.saveAuthToken("DUMMY_TOKEN_FOR_" + user.getFirstName());
                            goToHomePage();
                            break;
                        }
                    }

                    if (!emailFound) {
                        Toast.makeText(LoginActivity.this, "Input Login Salah.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Failed to retrieve user list.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToHomePage() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}