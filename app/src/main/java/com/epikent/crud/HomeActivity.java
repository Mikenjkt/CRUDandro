package com.epikent.crud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private TextView tvWelcome;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        // Optional: Display the token or other info
        String token = sessionManager.getAuthToken();
        tvWelcome.setText("Selamat datang! gak banyak apa apa disini, silahkan klik logout untuk keluar");

        btnLogout.setOnClickListener(v -> logout());
    }

    private void logout() {
        sessionManager.logoutUser();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Close HomeActivity
    }
}
