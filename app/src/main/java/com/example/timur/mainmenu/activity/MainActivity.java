package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.ActivityMainTest;
import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.service.DBservice;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button trainingBeginButton;
    TextView txtMotivation;
    DBservice dbService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtMotivation = (TextView)findViewById(R.id.motivationText);
        txtMotivation.setText("hello, "+ USERNAME);
        dbService = new DBservice(this);
        createDB();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        trainingBeginButton = (Button)findViewById(R.id.trainingBeginButton);
        trainingBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GamesScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.nav_scores) {
            intent = new Intent(MainActivity.this, LeaderboardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_account) {
            if(LOGGED_IN ==false){
               intent = new Intent(MainActivity.this, LoginActivity.class);
            }else{
                intent = new Intent(MainActivity.this, UserActivity.class);
            }
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            intent = new Intent(MainActivity.this, ActivityMainTest.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            intent = new Intent(MainActivity.this, TopScoresActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_about) {
            intent = new Intent(MainActivity.this, LeaderboardActivity2.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createDB(){
        dbService = dbService.open();
        if(dbService.isTableEmpty()){
            dbService.insertUser("test", "test", "01/01/2000", "Uzbekistan", "test@mail.com", "test", "test");
            Toast.makeText(getApplicationContext(), "Table added", Toast.LENGTH_SHORT).show();
        }
        dbService.close();
    }
}
