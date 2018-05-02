package com.example.allvan_pc.futsal_match;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Base_Activity implements View.OnClickListener{

    private static final String TAG = "SignInActivity";
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

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mUsername.getText().toString())) {
            mUsername.setError("Required");
            result = false;
        } else {
            mUsername.setError(null);
        }

        if (TextUtils.isEmpty(mPassword.getText().toString())) {
            mPassword.setError("Required");
            result = false;
        } else {
            mPassword.setError(null);
        }

        return result;
    }

    private void signUp(){
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(MainActivity.this, "Sign Up Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.login) {
            signUp();
        }
    }
}
