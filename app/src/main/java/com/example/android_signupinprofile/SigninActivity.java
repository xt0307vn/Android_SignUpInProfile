package com.example.android_signupinprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {
    EditText edt_username, edt_password;
    TextView tview_signinClick;
    Button btn_signin;
    String str_username, str_password;
    DatabaseUsers databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        connectID();
        databaseUsers = new DatabaseUsers(SigninActivity.this);
        Cursor cursor = databaseUsers.readAllData();

        tview_signinClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignup();
            }
        });


        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();

                if(checkLogin(cursor, str_username, str_password)) {
                    Toast.makeText(SigninActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    gotoProfile();
                } else {
                    Toast.makeText(SigninActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void connectID() {
        edt_username = findViewById(R.id.input_username);
        edt_password = findViewById(R.id.input_password);
        tview_signinClick = findViewById(R.id.tview_signinClick);
        btn_signin = findViewById(R.id.btn_signin);
    }

    public void gotoSignup() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void getInput() {
        str_password = edt_password.getText().toString().trim();
        str_username = edt_username.getText().toString().trim();
    }
    public boolean checkLogin(Cursor cursor, String username, String password) {
        while(cursor.moveToNext()) {
            if(username.equalsIgnoreCase(cursor.getString(2).toString().trim()) && password.equalsIgnoreCase(cursor.getString(2).toString().trim())) {
                return true;
            }
        }
        return false;
    }


    public void gotoProfile() {
        Intent intent = new Intent(SigninActivity.this, ProfileActivity.class);
        intent.putExtra("username", str_username);
        intent.putExtra("password", str_password);
        startActivity(intent);
    }
}