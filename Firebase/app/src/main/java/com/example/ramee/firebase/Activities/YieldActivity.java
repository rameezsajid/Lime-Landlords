package com.example.ramee.firebase.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramee.firebase.R;
import com.google.firebase.auth.FirebaseAuth;

public class YieldActivity extends AppCompatActivity {

    private Button addSum;
    EditText editTextPropertyPrice, editTextRental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yield);

        getSupportActionBar().setTitle("Calculate Your Yield");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addSum = (Button) findViewById(R.id.buttonAddSum);

        editTextPropertyPrice = (EditText)findViewById(R.id.editTextPropertyPrice);
        editTextRental = (EditText)findViewById(R.id.editTextRental);

        addSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yieldSum();
            }
        });

    }

    // this method calculates yield
    private void yieldSum(){
        EditText sum3 = (EditText)findViewById(R.id.editTextPropertyPrice);
        EditText sum4 = (EditText)findViewById(R.id.editTextRental);
        TextView num = (TextView)findViewById(R.id.tvYieldSum);

        String string1 = editTextPropertyPrice.getText().toString().trim();
        String string2 = editTextRental.getText().toString().trim();

        if (string1.isEmpty() || string2.isEmpty()){
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
            return;
        } else {
            int input3 = Integer.parseInt(sum3.getText().toString());
            int input4 = Integer.parseInt(sum4.getText().toString());

            int calculateYield = (input4 * 100) / input3;
            num.setText(Integer.toString(calculateYield));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }

        switch (item.getItemId()){
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.menu_dashboard:
                startActivity(new Intent(this, DashboardActivity.class));
                break;
        }

        return true;
    }
}
