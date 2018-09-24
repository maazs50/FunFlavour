package com.example.funflavour;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.text.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;
import java.util.Map;

public class RecordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    TextView recordTextView;
    Button selectData;
    DatePickerDialog.OnDateSetListener mDateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        mAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        recordTextView=findViewById(R.id.recordText);
        selectData=findViewById(R.id.selectDataBtn);
        selectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               datePicker();
            }
        });
        mDateListner =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    recordTextView.setText(dayOfMonth+"-"+month+"-"+year);
            }
        };



    }


    public void test(){
        DocumentReference documentReference= firebaseFirestore.collection("Records").document("High Volume").
                collection("18-09-2018 12:00:19").document("Calculations");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String,Object> record=document.getData();
                        for (Map.Entry<String, Object> entry : record.entrySet()) {
                            if (entry.getKey() != "Time") {
                                recordTextView.append(entry.getKey() + " = " + entry.getValue() + "\n");
                            }
                        }


                    } else {
                        Toast.makeText(RecordActivity.this, "Records not retreived", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RecordActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   public void datePicker(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog=new DatePickerDialog(RecordActivity.this,
                R.style.Theme_AppCompat_DayNight,mDateListner,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
