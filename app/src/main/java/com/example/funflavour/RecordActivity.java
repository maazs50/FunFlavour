package com.example.funflavour;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.text.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;
import java.util.Map;

public class RecordActivity extends AppCompatActivity {
    public TextView dateTextView,totalMangoTextView,totalLowVolTextView,totalHighVolTextView,totalSum,
            lowCount, highCount,mangoCountTextView;
    Button exit2;
    public FirebaseAuth mAuth;
    public FirebaseFirestore firebaseFirestore;
    //public int totalLowVolume,totalHighVolume, totalMango=0, sum=0, lowVolCount,highVolCount;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        mAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        initialize();
        firebaseFirestore.collection("Records").orderBy("time",Query.Direction.DESCENDING).
                get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                DocumentSnapshot ds=documentSnapshots.getDocuments().get(0);
                long lowVol= (long) ds.get(Contract.LOW_VOLUME);
                long highVol= (long) ds.get(Contract.HIGH_VOLUME);
                long highCount1= (long) ds.get(Contract.HIGH_VOLUME_COUNT);
                long lowCount1= (long) ds.get(Contract.LOW_VOLUME_COUNT);
                long mango= (long) ds.get(Contract.MANGO);
                long mangoCount= (long) ds.get(Contract.MANGO_COUNT);
                long total=(long)ds.get(Contract.TOTAL_AMOUNT);
                Date date=(Date)ds.get(Contract.TIME);
                totalLowVolTextView.setText(String.valueOf(lowVol));
                totalHighVolTextView.setText(String.valueOf(highVol));
                dateTextView.setText(String.valueOf(date));
                lowCount.setText(String.valueOf(lowCount1));
                highCount.setText(String.valueOf(highCount1));
                mangoCountTextView.setText(String.valueOf(mangoCount));
                totalMangoTextView.setText(String.valueOf(mango));
                totalSum.setText(String.valueOf(total));
            }
        });
  exit2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          android.os.Process.killProcess(android.os.Process.myPid());
          System.exit(1);
      }
  });
    }

    private void initialize(){
        totalLowVolTextView=findViewById(R.id.recordlowVolTextView);
        totalHighVolTextView=findViewById(R.id.recordhighVolTextView);
        totalSum=findViewById(R.id.recordtotal);
        totalMangoTextView=findViewById(R.id.recordmangoTextView);
        lowCount=findViewById(R.id.recordlowVolCount);
        highCount=findViewById(R.id.recordhighVolCount);
        mangoCountTextView=findViewById(R.id.recordmangoCount);
        dateTextView=findViewById(R.id.record_date);
        exit2=findViewById(R.id.exit2);
        mAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
    }

}
