package com.epikent.crud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            // This is now a dummy function, doesn't actually register anything
            Toast.makeText(this, "Akun berhasil di register.", Toast.LENGTH_SHORT).show();
            finish(); // Go back to LoginActivity
        });
    }
}

