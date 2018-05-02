package com.example.allvan_pc.futsal_match;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mSendData;
    private EditText mUsername;
    private EditText mPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mSendData = (Button) findViewById(R.id.login);
    }

    private void onAuthSuccess(FirebaseUser currentUser) {
    }

    @Override
    public void onStart(){
        super.onStart();

        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }

    private void signUp(){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
    }

    @Override
    public void onClick(View view) {

    }
}
