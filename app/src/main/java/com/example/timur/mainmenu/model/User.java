package com.example.timur.mainmenu.model;

import android.view.ViewGroup;

/**
 * Created by Timur on 4/5/2016.
 */
public class User {
    private int Id;
    private String Firstname;
    private String Lastname;
    private String DOB;
    private String Country;
    private String Email;
    private String Username;
    private String Password;

    public User(){};
    public User(String fname, String lname, String dob, String country,
                String email, String username, String pswrd){
        Firstname=fname;
        Lastname=lname;
        DOB = dob;
        Country = country;
        Email = email;
        Username = username;
        Password = pswrd;
    }

    public void setFirstname(String _Firstname){
        Firstname = _Firstname;
    }

    public String getFirstname(){
        return Firstname;
    }

    public void setLastname(String _Lastname){
        Lastname = _Lastname;
    }

    public String getLastname(){
        return Lastname;
    }

    public void setDOB(String _DOB){
        DOB = _DOB;
    }

    public String getDOB(){
        return DOB;
    }

    public void setCountry(String _Country){
        Country = _Country;
    }

    public String getCountry(){
        return Country;
    }

    public void setEmail(String _Email){
        Email = _Email;
    }

    public String getEmail(){
        return Email;
    }

    public void setUsername(String _Username){
        Username = _Username;
    }

    public String getUsername(){
        return Username;
    }

    public void setPassword(String _Password){
        Password = _Password;
    }

    public String getPassword(){
        return Password;
    }
}
