package com.example.bookingtable.statue;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingtable.R;
import com.example.bookingtable.db.UserDTO;
import com.example.bookingtable.reserve.ConfirmActivity;
import com.example.bookingtable.reserve.DayTimeActivity;
import com.example.bookingtable.reserve.TableActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SeatAdapter extends FirebaseRecyclerAdapter<UserDTO, SeatAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SeatAdapter(@NonNull FirebaseRecyclerOptions<UserDTO> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull UserDTO model) {
        holder.zone.setText(model.getZone());
        holder.seat.setText(model.getSeat());

        Glide.with(holder.img.getContext())
                .load(model.getUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup2))
                        .setExpanded(true, 1600).create();

                View v = dialogPlus.getHolderView();


                EditText zone = v.findViewById(R.id.txtZone);
                EditText seat = v.findViewById(R.id.txtSeat);
                EditText url = v.findViewById(R.id.txtImageUrl);

                Button btnUpdate = v.findViewById(R.id.btnUpdate);

                zone.setText(model.getZone());
                seat.setText(model.getSeat());
                url.setText(model.getUrl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("zone", zone.getText().toString());
                        map.put("seat", seat.getText().toString());
                        map.put("url", url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.zone.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.zone.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });

            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView zone, seat;

        Button btnEdit, btnTable;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            zone = (TextView)itemView.findViewById(R.id.zonetext);
            seat = (TextView)itemView.findViewById(R.id.seattext);
            img = (CircleImageView)itemView.findViewById(R.id.imgl);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnTable = (Button)itemView.findViewById(R.id.btnTable);

            btnTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SeatUpdate.class);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}