package com.example.bookingtable.reserve;

import static com.example.bookingtable.login.Login.loginId;
import static com.example.bookingtable.login.Login.userId;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.bookingtable.R;
import com.example.bookingtable.db.UserDTO;
import com.example.bookingtable.login.MainActivity;
import com.example.bookingtable.login.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmActivity extends Activity {

    //daytime 에서 가져올 변수
    TextView tvYear3, tvMonth3, tvDay3, tvHour3, tvMinute3;

    TextView tvName2,tvPhone2;
    TextView seat2,zone2;
    Button btnConfirm, btnFinish;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm);
        setTitle("BOOKING CONFIRM");

        //예약 날 정보
        //이전 day time 변수들
        tvYear3 = (TextView) findViewById(R.id.tvYear3);
        tvMonth3 = (TextView) findViewById(R.id.tvMonth3);
        tvDay3 = (TextView) findViewById(R.id.tvDay3);
        tvHour3 = (TextView) findViewById(R.id.tvHour3);
        tvMinute3 = (TextView) findViewById(R.id.tvMinute3);

        tvYear3.setText(((DayTimeActivity)DayTimeActivity.DayContext).tvYear.getText());
        tvMonth3.setText(((DayTimeActivity)DayTimeActivity.DayContext).tvMonth.getText());
        tvDay3.setText(((DayTimeActivity)DayTimeActivity.DayContext).tvDay.getText());
        tvHour3.setText(((DayTimeActivity)DayTimeActivity.DayContext).tvHour.getText());
        tvMinute3.setText(((DayTimeActivity)DayTimeActivity.DayContext).tvMinute.getText());

        //메인 액티비티로 이동해 정보 수정 가능 back기능
        Button btnReturn = (Button)findViewById(R.id.BtnReturnToTable);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //두번째 액티비티의 화면 동작 설계
        //이전 화면 정보 뿌리기
        tvName2 = (TextView)findViewById(R.id.tvName2);
        tvPhone2 = (TextView)findViewById(R.id.tvPhone2);
        seat2 = (TextView)findViewById(R.id.Seat2);
        zone2 = (TextView)findViewById(R.id.seleteZone);

        //앞의 MainActivity 의 정보 받아오기
        //context로 액티비티 전체를 받아와 이전에 저장된 텍스트 변수의 텍스트를 가져옴
        tvName2.setText(((TableActivity)TableActivity.tableContext).tvName.getText());
        tvPhone2.setText(((TableActivity)TableActivity.tableContext).tvPhone.getText());
        seat2.setText(((TableActivity)TableActivity.tableContext).seat.getText());
        zone2.setText(((TableActivity)TableActivity.tableContext).newZone1.getText());

        btnConfirm =(Button)findViewById(R.id.BtnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(ConfirmActivity.this);
                dlg.setTitle("Reservation Confirm");
                dlg.setIcon(R.drawable.btn_star_big_on);
                dlg.setMessage("Do you want to confirm?");
                dlg.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"It's all set!.",
                                Toast.LENGTH_SHORT).show();

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference("users");

                        String year = tvYear3.getText().toString();
                        String month = tvMonth3.getText().toString();
                        String day = tvDay3.getText().toString();
                        String startHour = tvHour3.getText().toString();
                        String startMinute = tvMinute3.getText().toString();
                        String seat = seat2.getText().toString();
                        String zone = zone2.getText().toString();

                        UserDTO s = new UserDTO(year, month, day, startHour, startMinute, seat, zone);
                        databaseReference.child(loginId).child("year").setValue(s.getYear());
                        databaseReference.child(loginId).child("month").setValue(s.getMonth());
                        databaseReference.child(loginId).child("day").setValue(s.getDay());
                        databaseReference.child(loginId).child("startHour").setValue(s.getStartHour());
                        databaseReference.child(loginId).child("startMinute").setValue(s.getStartMinute());
                        databaseReference.child(loginId).child("seat").setValue(s.getSeat());
                        databaseReference.child(loginId).child("zone").setValue(s.getZone());

                        databaseReference = firebaseDatabase.getReference("Users");

                        Users u = new Users(year, month, day, startHour, startMinute, seat, zone);
                        databaseReference.child(userId).child("year").setValue(u.getYear());
                        databaseReference.child(userId).child("month").setValue(u.getMonth());
                        databaseReference.child(userId).child("day").setValue(u.getDay());
                        databaseReference.child(userId).child("startHour").setValue(u.getStartHour());
                        databaseReference.child(userId).child("startMinute").setValue(u.getStartMinute());
                        databaseReference.child(userId).child("seat").setValue(u.getSeat());
                        databaseReference.child(userId).child("zone").setValue(u.getZone());


                    }
                });
                dlg.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancelled.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show(); //***

            }
        });

        btnFinish = (Button) findViewById(R.id.BtnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmActivity.this, MainActivity.class); //다음 Table클래스 정보 입력
                startActivity(intent);//다음 액티비티 화면에 출력
            }
        });
    }
}
