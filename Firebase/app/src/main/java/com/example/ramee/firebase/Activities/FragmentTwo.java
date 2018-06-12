package com.example.ramee.firebase.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ramee.firebase.Models.Properties;
import com.example.ramee.firebase.Models.PropertyList;
import com.example.ramee.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramee on 19/04/2018.
 */

public class FragmentTwo extends Fragment {

    private String userID;

    ListView listViewProperties;

    List<Properties> propertiesList;

    private FirebaseAuth mAuth;

    public FragmentTwo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        listViewProperties = (ListView) view.findViewById(R.id.listViewProperties3);

        propertiesList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        userID = user.getUid();

        listViewProperties.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Properties properties = propertiesList.get(i);

                showUpdateDialog(properties.getPropertyID(), properties.getPropertyLocation());
            }
        });

        return view;
    }

    private void showUpdateDialog(final String propertyID, final String propertyLocation){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.delete_option, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextLocation = (EditText) dialogView.findViewById(R.id.editTextPropertyLocation3Update);
        final Spinner spinnerType = (Spinner) dialogView.findViewById(R.id.spinnerProperty3Update);

        final EditText editTextUpdateRental = (EditText) dialogView.findViewById(R.id.editTextPropertyRental3Update);
        final Spinner spinnerLength = (Spinner) dialogView.findViewById(R.id.spinnerLength3Update);

        final EditText editTextUpdateAddress = (EditText) dialogView.findViewById(R.id.editTextPropertyAddressUpdate);
        final EditText editTextUpdatePostcode = (EditText) dialogView.findViewById(R.id.editTextPostCodeUpdate);

        final Spinner spinnerMType = (Spinner) dialogView.findViewById(R.id.spinnerManagementUpdate);
        final EditText editTextMName = (EditText) dialogView.findViewById(R.id.editTextManagementNameUpdate);


        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.btnDelete);
        final Button buttonCancel = (Button) dialogView.findViewById(R.id.btnClose);

        dialogBuilder.setTitle("Updating Property " + propertyLocation);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        DatabaseReference mFirebaseRef = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextLocation.setText(dataSnapshot.child("propertyLocation").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query2 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextUpdateRental.setText(dataSnapshot.child("propertyRental").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query3 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextUpdatePostcode.setText(dataSnapshot.child("propertyPostcode").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query4 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextUpdateAddress.setText(dataSnapshot.child("propertyAddress").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query5 = mFirebaseRef.child("properties").child(userID).child(propertyID);

        query5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextMName.setText(dataSnapshot.child("managementName").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = editTextLocation.getText().toString().trim();
                String type = spinnerType.getSelectedItem().toString();

                String rental = editTextUpdateRental.getText().toString().trim();
                String length = spinnerLength.getSelectedItem().toString();

                String address = editTextUpdateAddress.getText().toString().trim();
                String postcode = editTextUpdatePostcode.getText().toString().trim();

                String mName = spinnerMType.getSelectedItem().toString();
                String mType = editTextMName.getText().toString().trim();

                if (TextUtils.isEmpty(location)){
                    editTextLocation.setError("Field Requried");
                    return;
                }
                updateProperties(propertyID, location, type, rental, length, address, postcode, mName, mType);

                alertDialog.dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProperties(propertyID);
                alertDialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });



    }

    private boolean updateProperties (String id, String location, String type, String rental, String length, String address, String postcode, String mtype, String mname){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("properties").child(userID).child(id);

        Properties properties = new Properties(id, location, type, rental, length, address, postcode, mtype, mname);

        databaseReference.setValue(properties);

        Toast.makeText(getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

        return true;
    }

    private void deleteProperties(String propertyID){
        DatabaseReference drProperties = FirebaseDatabase.getInstance().getReference("properties").child(userID).child(propertyID);
        drProperties.removeValue();
        Toast.makeText(getContext(), "Deleted Property", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseDatabase.getInstance().getReference("properties").getRef().child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                propertiesList.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Properties property = data.getValue(Properties.class);

                    propertiesList.add(property);
                }

                PropertyList adapter = new PropertyList(getActivity(), propertiesList);
                listViewProperties.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
