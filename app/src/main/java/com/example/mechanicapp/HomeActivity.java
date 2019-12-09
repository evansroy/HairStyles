package com.example.mechanicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textViewUserEmail;
    private TextView textViewUsername;

    private FirebaseAuth firebaseAuth;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //if (savedInstanceState == null){
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
        //       new ProfileFragment()).commit();
        // navigationView.setCheckedItem(R.id.nav_profile);
// }

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = findViewById(R.id.tvUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());

        textViewUsername = findViewById(R.id.userName);
        //textViewUsername.setText(user.getDisplayName());

        //btnLogOut.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //  public void onClick(View view) {

        //   if (view == btnLogOut) {
        //     firebaseAuth.signOut();
        //     finish();
        //        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        // }

        // }
        // });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_contacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Contacts_Fragment()).commit();
                break;
            case R.id.nav_invite_friends:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Fragment_InviteFriends()).commit();
                break;
            case R.id.nav_live_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Fragment_LiveChat()).commit();
                break;
            case R.id.nav_hire_tools:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Fragment_HireTools()).commit();
                break;
            case R.id.nav_send_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frament_SendFeedback()).commit();
                break;
            case R.id.nav_ask_for_Quote:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Frament_AskForQuotes()).commit();
                break;
            case R.id.nav_log_out:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mechanicWeb) {

            Toast.makeText(this, "You have clicked on Mechanic Web", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MechanicWeb_Activity.class));
        }
        else  if (id == R.id.settings){

                Toast.makeText(this, "You have Click on Setting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Settings_Activity.class));
            }
        else if (id == R.id.help){
            Toast.makeText(this, "You Click on Help", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Help_Activity.class));
        }




                return super.onOptionsItemSelected(item);
            }
        }


