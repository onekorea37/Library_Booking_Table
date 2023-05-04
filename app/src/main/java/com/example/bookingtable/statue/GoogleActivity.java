package com.example.bookingtable.statue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookingtable.R;
import com.example.bookingtable.db.UserDTO;
import com.example.bookingtable.login.Users;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class GoogleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GoogleAdapter googleAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        recyclerView = (RecyclerView)findViewById((R.id.rv));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), Users.class)
                        .build();


        googleAdapter = new GoogleAdapter(options);
        recyclerView.setAdapter(googleAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleAdapter.stopListening();
    }

}