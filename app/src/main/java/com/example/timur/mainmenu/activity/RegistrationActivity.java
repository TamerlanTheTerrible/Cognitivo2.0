package com.example.timur.mainmenu.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.service.DBservice;

/**
 * Created by Timur on 4/4/2016.
 */
public class RegistrationActivity extends BaseActivity{
    EditText edtFirstname, edtLastname, edtCountry, edtUsername, edtPassword, edtEmail;
    TextView txtDOB;
    Button btnReg;
    String date;
    int DIALOG_DATE;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    DBservice dBservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registraion_layout);

        dBservice = new DBservice(this);
        dBservice = dBservice.open();

        edtFirstname = (EditText)findViewById(R.id.edtFirstname);
        edtLastname = (EditText)findViewById(R.id.edtLastname);
        edtCountry = (EditText)findViewById(R.id.edtCountry);
        edtUsername = (EditText)findViewById(R.id.edtUsernameReg);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText)findViewById(R.id.edtPasswordReg);
        txtDOB = (TextView)findViewById(R.id.txtDOB);
        btnReg = (Button)findViewById(R.id.btnReg);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.txtDOB:
                        showDialog(DIALOG_DATE);
                        break;
                    case R.id.btnReg:
                        registration();
                        break;
                }
            }
        };
        txtDOB.setOnClickListener(onClickListener);
        btnReg.setOnClickListener(onClickListener);
    }

    public void registration(){
        String fname = edtFirstname.getText().toString();
        String lname = edtLastname.getText().toString();
        String country = edtCountry.getText().toString();
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String dob = txtDOB.getText().toString();

        if(fname.equals("")||lname.equals("")||dob.equals("")||country.equals("")||username.equals("")||email.equals("")||password.equals("")){
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
        }if(dBservice.userExists(username)){
            Toast.makeText(getApplicationContext(), "User with this username already registered", Toast.LENGTH_LONG).show();
        }
        else {
            dBservice.insertUser(fname, lname, dob, country, email, username, password);
            Toast.makeText(getApplicationContext(), "Account Successfully Registered ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);finish();
            dBservice.close();
        }
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            txtDOB.setText(myDay + "/" + myMonth + "/" + myYear);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dBservice.close();
    }
}
