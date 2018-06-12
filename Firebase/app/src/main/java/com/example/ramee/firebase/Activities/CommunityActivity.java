package com.example.ramee.firebase.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ramee.firebase.Models.Message;
import com.example.ramee.firebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommunityActivity extends AppCompatActivity {

    private EditText editMessage, editName;
    private DatabaseReference mDatabase;
    private RecyclerView eMessageList;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editMessage = (EditText) findViewById(R.id.editMessageE);
        editName = (EditText) findViewById(R.id.editNameE);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");

        eMessageList = (RecyclerView) findViewById(R.id.messageRec);

        eMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        eMessageList.setLayoutManager(linearLayoutManager);

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(CommunityActivity.this,LoginActivity.class));
                }
            }
        };

    }

    public void sendButtonClicked(View view){
        final String messageValue = editMessage.getText().toString().trim();
        final String nameValue = editName.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue) && !TextUtils.isEmpty(nameValue)){
            final DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(messageValue);
            newPost.child("name").setValue(nameValue);
        }
        eMessageList.scrollToPosition(eMessageList.getAdapter().getItemCount());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseRecyclerAdapter <Message,MessageViewHolder> FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.singlemessagelayout,
                MessageViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                      viewHolder.setContent(model.getContent());
                      viewHolder.setName(model.getName());
            }
        };
        eMessageList.setAdapter(FBRA);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setContent(String content){
            TextView message_content = (TextView) mView.findViewById(R.id.messageText);
            message_content.setText(content);
        }

        public void setName(String name){
            TextView username_content = (TextView) mView.findViewById(R.id.usernameText);
            username_content.setText(name);
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
