package com.example.bookingtable.statue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookingtable.R;
import com.example.bookingtable.db.UserDTO;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class SeatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SeatAdapter seatAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        recyclerView = (RecyclerView)findViewById((R.id.rv));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UserDTO> options =
                new FirebaseRecyclerOptions.Builder<UserDTO>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), UserDTO.class)
                        .build();


        seatAdapter = new SeatAdapter(options);
        recyclerView.setAdapter(seatAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        seatAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        seatAdapter.stopListening();
    }

}