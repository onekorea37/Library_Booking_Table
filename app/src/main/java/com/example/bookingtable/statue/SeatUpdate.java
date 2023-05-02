package com.example.bookingtable.statue;

import static com.example.bookingtable.login.Login.loginId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingtable.R;

import com.example.bookingtable.reserve.ConfirmActivity;
import com.example.bookingtable.reserve.TableActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class SeatUpdate extends AppCompatActivity {

    TextView name2, id2;
    TextView statueYear, statueMonth, statueDay;
    TextView statueHour, statueMin;
    TextView statueZone, statueSeat;
    Button btnEdit, btnInfo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_update);

        name2 = (TextView) findViewById(R.id.status_title);
        id2 = (TextView) findViewById(R.id.status_user_id);
        statueZone = (TextView) findViewById(R.id.status_zone_number);
        statueSeat = (TextView) findViewById(R.id.status_seat_number);
        statueYear = (TextView) findViewById(R.id.sYear);
        statueMonth = (TextView) findViewById(R.id.sMonth);
        statueDay = (TextView) findViewById(R.id.sDay);
        statueHour = (TextView) findViewById(R.id.sHour);
        statueMin = (TextView) findViewById(R.id.sMin);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        Query query = databaseReference.child(loginId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get user data
                String name = dataSnapshot.child("name").getValue(String.class);
                String id = dataSnapshot.child("id").getValue(String.class);
                String zone = dataSnapshot.child("zone").getValue(String.class);
                String seat = dataSnapshot.child("seat").getValue(String.class);
                String year = dataSnapshot.child("year").getValue(String.class);
                String month = dataSnapshot.child("month").getValue(String.class);
                String day = dataSnapshot.child("day").getValue(String.class);
                String startHour = dataSnapshot.child("startHour").getValue(String.class);
                String startMinute = dataSnapshot.child("startMinute").getValue(String.class);


                // Update UI with user data
                name2.setText(name);
                id2.setText(id);
                statueZone.setText(zone);
                statueSeat.setText(seat);
                statueYear.setText(year);
                statueMonth.setText(month);
                statueDay.setText(day);
                statueHour.setText(startHour);
                statueMin.setText(startMinute);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnEdit = (Button) findViewById(R.id.status_cancel_button);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatUpdate.this, SeatActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });

        btnInfo = (Button) findViewById(R.id.status_extend_button);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeatUpdate.this, StatueActivity.class);
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });
    }
}
