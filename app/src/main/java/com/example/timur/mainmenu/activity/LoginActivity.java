package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.service.DBservice;

/**
 * Created by Timur on 4/5/2016.
 */
public class LoginActivity extends BaseActivity {
    private EditText edtUsername;
    private EditText edtPassword;
    private TextView txtToRegister;
    Button btnLogin;
    DBservice dBservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        dBservice = new DBservice(this);
        dBservice = dBservice.open();

        edtPassword = (EditText)findViewById(R.id.edtPasswordLog);
        edtUsername = (EditText)findViewById(R.id.edtUsernameLog);
        txtToRegister = (TextView)findViewById(R.id.txtToResgiter);
        txtToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(dBservice.login(username, password)){
                    USERNAME = username;
                    LOGGED_IN =true;
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dBservice.close();
    }
}
