package com.app.borrowapp2.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.borrowapp2.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Fixed the ID from RegisterIconTv to mainLyt to correctly apply window insets to the root layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLyt), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up click listener for the Login text to return to the login screen
        if (findViewById(R.id.loginTv) != null) {
            findViewById(R.id.loginTv).setOnClickListener(v -> finish());
        }
    }
}