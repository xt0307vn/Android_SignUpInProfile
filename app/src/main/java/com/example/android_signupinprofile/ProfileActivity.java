package com.example.android_signupinprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    DatabaseUsers databaseUsers;
    ImageView btn_back;
    TextView tview_fullname, tview_username, tview_password, tview_phone, tview_email;
    String username, password, fullname, phone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        connectID();
        databaseUsers = new DatabaseUsers(ProfileActivity.this);
        Cursor cursor = databaseUsers.readAllData();
        getDataUser(cursor, username, password);
        setProfile();


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfileActivity.this, SigninActivity.class);
                startActivity(intent1);
            }
        });


    }


    public void connectID() {
        tview_fullname = findViewById(R.id.fullname);
        tview_username = findViewById(R.id.username);
        tview_password = findViewById(R.id.password);
        tview_phone = findViewById(R.id.phone);
        tview_email = findViewById(R.id.email);
        btn_back = findViewById(R.id.btn_back);
    }

    public void getDataUser(Cursor cursor, String username, String password) {
        while(cursor.moveToNext()) {
            if(username.equalsIgnoreCase(cursor.getString(2)) && password.equalsIgnoreCase(cursor.getString(3))) {
                fullname = cursor.getString(1);
                phone = cursor.getString(4);
                email = cursor.getString(5);
            }
        }
    }

    public void setProfile() {
        tview_fullname.setText(fullname);
        tview_username.setText(username);
        tview_password.setText(password);
        tview_phone.setText(phone);
        tview_email.setText(email);
    }


}