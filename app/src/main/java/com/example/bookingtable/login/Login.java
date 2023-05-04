package com.example.bookingtable.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingtable.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText id, password;
    ProgressBar progressBar;
    Button btnLogin;
    TextView signupText;

    public static boolean loginStatus = false;
    public static String loginId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        id = findViewById(R.id.loginId);
        password = findViewById(R.id.loginPassword);

        signupText = findViewById(R.id.signupText);
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin = findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(v -> {
            if (!validateId() | !validatePassword()){

            }else {
                checkUser();
            }
        });
    }

    public Boolean validateId(){
        String val = id.getText().toString();
        if (val.isEmpty()){
            id.setError("ID cannot be empty");
            return false;
        } else {
            id.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = password.getText().toString();
        if (val.isEmpty()){
            password.setError("Password cannot be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUserId = id.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = databaseReference.orderByChild("id").equalTo(userUserId);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    id.setError(null);
                    String passwordFromFB = snapshot.child(userUserId).child("pw").getValue(String.class);

                    if(Objects.requireNonNull(passwordFromFB).equals(userPassword)){
                        id.setError(null);
                        loginStatus = true;
                        loginId = userUserId;

                        Toast.makeText(getApplicationContext(), "Login Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        password.setError("Invalid Credentials");
                        password.requestFocus();
                    }
                } else{
                    id.setError("User Does Not Exist");
                    id.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadUser:onCancelled", error.toException());
            }
        });
    }


}
