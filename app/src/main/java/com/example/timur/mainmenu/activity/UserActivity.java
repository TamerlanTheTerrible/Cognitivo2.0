package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.model.User;
import com.example.timur.mainmenu.service.DBservice;

/**
 * Created by Timur on 4/5/2016.
 */
public class UserActivity extends BaseActivity {
    User user;
    TextView fname, lname, dob, country, email, username, password, logout;
    DBservice dBservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        dBservice = new DBservice(this);
        dBservice = dBservice.open();
        user = dBservice.getUser(USERNAME);
        fname = (TextView)findViewById(R.id.txtFirstnameInfo);
        lname = (TextView)findViewById(R.id.txtLastnameInfo);
        dob = (TextView)findViewById(R.id.txtDOBInfo);
        country = (TextView)findViewById(R.id.txtCountryInfo);
        email = (TextView)findViewById(R.id.txtEmalinfo);
        logout = (TextView)findViewById(R.id.txtLogout);
        username = (TextView)findViewById(R.id.txtUsernameinfo);

        username.setText(USERNAME);
        fname.setText(user.getFirstname());
        lname.setText(user.getLastname());
        dob.setText(user.getDOB());
        country.setText(user.getCountry());
        email.setText(user.getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERNAME ="user1";
                LOGGED_IN =false;
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dBservice.close();
    }
}
