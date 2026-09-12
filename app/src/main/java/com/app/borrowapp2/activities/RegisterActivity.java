package com.app.borrowapp2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.borrowapp2.R;
import com.app.borrowapp2.database.DatabaseRepository;
import com.app.borrowapp2.models.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerTv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpNavigation();
    }
    private void setUpNavigation(){
        LinearLayout registerButton = findViewById(R.id.registerBtn);
        TextView loginTv = findViewById(R.id.loginTv);
        registerButton.setOnClickListener(v->toRegister());
        loginTv.setOnClickListener(v->toLogin());
    }
    private void toRegister(){
        EditText usernameEt = findViewById(R.id.usernameEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        String username = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Input credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.length()<8 || password.length()>15){
            Toast.makeText(this, "Password length must be between 8 - 15 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(0,username, password);
        try(DatabaseRepository db = new DatabaseRepository(this))
        {
            if(db.register(user)){
                toLogin();
            }
        }
    }
    private void toLogin(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}