package com.example.bookingtable.login;

import static com.example.bookingtable.login.Login.loginStatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookingtable.reserve.DayTimeActivity;
import com.example.bookingtable.R;
import com.example.bookingtable.reserve.ZoneActivity;
import com.example.bookingtable.statue.GoogleActivity;
import com.example.bookingtable.statue.StatueActivity;

public class MainActivity extends AppCompatActivity {

   //  @SuppressLint("StaticFieldLeak")
    static Button main_login_button;
    static Button main_reserve_button;
    static Button main_statue_button;
    static Button main_statue_button2;
    Intent intent;

    //  @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBackPressed();

        main_login_button = findViewById(R.id.mainLoginButton);
        main_statue_button = findViewById(R.id.mainStatueButton);
        main_reserve_button = findViewById(R.id.mainReserveButton);
        main_statue_button2 = findViewById(R.id.mainStatueButton2);

        if(loginStatus){
            main_login_button.setText("LOGOUT");
            main_login_button.setBackgroundColor(Color.LTGRAY);
        }

        main_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginStatus) { // 로그인이 되어있으면
                    loginStatus = false;
                    main_login_button.setBackgroundColor(Color.TRANSPARENT);
                    main_login_button.setText("Click to Logout");
                    Toast.makeText(getApplicationContext(), "You are logged out.", Toast.LENGTH_SHORT).show();

                } else {
                    intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
            }
            });

        main_statue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StatueActivity.class);
                startActivity(intent);
            }
        });

        main_statue_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GoogleActivity.class);
                startActivity(intent);
            }
        });

        main_reserve_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ZoneActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    // 뒤로가기 방지
    public void onBackPressed() {  }

}