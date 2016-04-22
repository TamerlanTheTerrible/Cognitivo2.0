package com.example.timur.mainmenu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

import com.example.timur.mainmenu.activity.LoginActivity;
import com.example.timur.mainmenu.model.User;

public class CreateUserActivity extends Activity {

    EditText etfirstName, etLastName, etUsername, etPassword, etDOB, etEmail, etCountry;
    Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        // Show the Up button in the action bar.
        //setupActionBar();

        etfirstName =(EditText) findViewById(R.id.et_fisrtname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etUsername = (EditText) findViewById(R.id.et_cu_username);
        etPassword = (EditText) findViewById(R.id.et_cu_password);
        etDOB = (EditText) findViewById(R.id.et_cu_dob);
        etEmail = (EditText) findViewById(R.id.et_cu_email);
        etCountry = (EditText) findViewById(R.id.et_cu_country);
        btnCreateUser=(Button) findViewById(R.id.btn_createuser);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String firstname, lastname, username, password, dob, email, country;

                firstname = etfirstName.getText().toString();
                lastname = etLastName.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                dob = etDOB.getText().toString();
                email = etEmail.getText().toString();
                country = etCountry.getText().toString();

                User userDetail = new User(firstname,
                        lastname, username, password, dob, email, country);

                new AsyncCreateUser().execute(userDetail);

            }
        });

    }

    protected class AsyncCreateUser extends
            AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... params) {

            RestAPI api = new RestAPI();
            try {

                api.CreateNewAccount(params[0].getFirstname(),params[0].getLastname(),
                        params[0].getDOB(), params[0].getEmail(), params[0].getCountry(),
                        params[0].getUsername(),params[0].getPassword());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateUser", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Intent i = new Intent(CreateUserActivity.this, LoginActivityTest.class);
            startActivity(i);
        }

    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
/*    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

}
