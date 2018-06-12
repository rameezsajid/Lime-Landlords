package com.example.ramee.firebase.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ramee.firebase.Models.Properties;
import com.example.ramee.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ramee on 19/04/2018.
 */

public class FragmentOne extends Fragment {

    private EditText editText, editText2, editText3, editText4, editText5;
    private Spinner spinner, spinner2, spinner3;

    private String userID;
    private Button addButton;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    public FragmentOne() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        editText = (EditText) view.findViewById(R.id.editTextPropertyLocation3);
        spinner = (Spinner) view.findViewById(R.id.spinnerProperty3);

        editText2 = (EditText) view.findViewById(R.id.editTextPropertyRental3);
        spinner2 = (Spinner) view.findViewById(R.id.spinnerLength3);

        editText3 = (EditText) view.findViewById(R.id.editTextPropertyAddress);
        editText4 = (EditText) view.findViewById(R.id.editTextPostCode);

        spinner3 = (Spinner) view.findViewById(R.id.spinnerManagement);
        editText5 = (EditText) view.findViewById(R.id.editTextManagementName);

        addButton = (Button) view.findViewById(R.id.addButton);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference("properties");

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProperties();
            }
        });

        return view;
    }


    private void addProperties(){
        String location = editText.getText().toString().trim();
        String propertyType = spinner.getSelectedItem().toString();
        String rental = editText2.getText().toString().trim();
        String length = spinner2.getSelectedItem().toString();
        String address = editText3.getText().toString().trim();
        String postcode = editText4.getText().toString().trim();
        String managementType = spinner3.getSelectedItem().toString();
        String managementName = editText5.getText().toString().trim();

        if (!TextUtils.isEmpty(location) && !TextUtils.isEmpty(rental) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(postcode) && !TextUtils.isEmpty(managementName)){

            String id = myRef.push().getKey();

            Properties properties = new Properties(id, location, propertyType, rental, length, address, postcode, managementType, managementName);

            myRef.child(userID).child(id).setValue(properties);

            Toast.makeText(getActivity(), "Property Added", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
