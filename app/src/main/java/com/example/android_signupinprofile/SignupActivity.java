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

public class SignupActivity extends AppCompatActivity {
    EditText edt_username, edt_password, edt_email, edt_phone, edt_fullname;
    Button btn_signup;
    TextView signup_click;
    ArrayList<Users> usersArrayList;
    String str_username, str_password, str_email, str_phone, str_fullname;

    DatabaseUsers databaseUsers;
    ArrayList<String> user_id, user_fullname, user_username, user_password, user_phone, user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        connectID();
        databaseUsers = new DatabaseUsers(SignupActivity.this);
        user_id = new ArrayList<>();
        user_fullname = new ArrayList<>();
        user_username = new ArrayList<>();
        user_password = new ArrayList<>();
        user_phone = new ArrayList<>();
        user_email = new ArrayList<>();
        Cursor cursor = databaseUsers.readAllData();
        usersArrayList = new ArrayList<>();


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
                if(str_fullname.isEmpty() || str_username.isEmpty() ||str_password.isEmpty() ||str_phone.isEmpty() ||str_email.isEmpty()) {
//                    if(str_fullname.isEmpty()) {
//                        Toast.makeText(SignupActivity.this, "Bạn chưa nhập họ tên", Toast.LENGTH_SHORT).show();
//                    }
//                    if(str_username.isEmpty()) {
//                        Toast.makeText(SignupActivity.this, "Bạn chưa nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
//                    }
//                    if(str_password.isEmpty()) {
//                        Toast.makeText(SignupActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
//                    }
//                    if(str_phone.isEmpty()) {
//                        Toast.makeText(SignupActivity.this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
//                    }
//                    if(str_email.isEmpty()) {
//                        Toast.makeText(SignupActivity.this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
//                    }
                    Toast.makeText(SignupActivity.this, "Bạn chưa nhập đầy đru thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if(cursor.getCount() == 0) {
                        databaseUsers.addUsers(str_fullname, str_username,str_password,str_phone,str_email);
                        Toast.makeText(SignupActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        resetInput();
                        gotoSignin();
                    } else {
                        if(checkSignup(cursor, str_username)) {
                            Toast.makeText(SignupActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            databaseUsers.addUsers(str_fullname, str_username,str_password,str_phone,str_email);
                            resetInput();
                            gotoSignin();
                        }
                    }
                }

            }
        });


        signup_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoSignin();
            }
        });
    }


    public void connectID() {
        edt_username = findViewById(R.id.input_username);
        edt_password = findViewById(R.id.input_password);
        edt_email = findViewById(R.id.input_email);
        edt_phone = findViewById(R.id.input_phone);
        edt_fullname = findViewById(R.id.input_fullname);
        btn_signup = findViewById(R.id.btn_signup);
        signup_click = findViewById(R.id.signup_clickhere);
    }

    public void getInput() {
        str_username = edt_username.getText().toString().trim();
        str_password = edt_password.getText().toString().trim();
        str_email = edt_email.getText().toString().trim();
        str_phone = edt_phone.getText().toString().trim();
        str_fullname = edt_fullname.getText().toString().trim();
    }

    public void gotoSignin() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }

    public boolean checkSignup(Cursor cursor, String username) {
        while(cursor.moveToNext()) {
            if(username.equalsIgnoreCase(cursor.getString(2).toString().trim())) {
                return true;
            }
        }
        return false;
    }

    public void resetInput() {
        edt_username.setText("");
        edt_password.setText("");
        edt_email.setText("");
        edt_phone.setText("");
        edt_fullname.setText("");
    }


}