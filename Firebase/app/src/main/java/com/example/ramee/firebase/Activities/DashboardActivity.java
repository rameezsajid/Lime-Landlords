package com.example.ramee.firebase.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ramee.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    RelativeLayout addPropertiesLayout, searchPropertiesLayout, addYeldLayout, sendMailLayout, logoutLayout, chatLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("Dashboard");

        addPropertiesLayout = findViewById(R.id.addProperty);
        searchPropertiesLayout = findViewById(R.id.searchProperty);
        addYeldLayout = findViewById(R.id.checkYield);
        sendMailLayout = findViewById(R.id.sendMail);
        logoutLayout = findViewById(R.id.logoutLayout);
        chatLayout = findViewById(R.id.chatLayout);

        addPropertiesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashboardActivity.this, PropertiesActivity.class);
                startActivity(intent1);
            }
        });

        searchPropertiesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent (DashboardActivity.this, SearchBarActivity.class);
                startActivity(intent6);
            }
        });

        addYeldLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DashboardActivity.this, YieldActivity.class);
                startActivity(intent2);
            }
        });

        sendMailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(DashboardActivity.this, MailActivity.class);
                startActivity(intent3);
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(DashboardActivity.this, LoginActivity.class);
                mAuth.signOut();
                startActivity(intent4);
            }
        });

        chatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent (DashboardActivity.this, CommunityActivity.class);
                startActivity(intent5);
            }
        });

        setupFirebaseAuth();

    }

    private void checkCurrentUser(FirebaseUser user){
        if(user == null){
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void setupFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                checkCurrentUser(user);
                if (user != null) {

                } else {

                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
