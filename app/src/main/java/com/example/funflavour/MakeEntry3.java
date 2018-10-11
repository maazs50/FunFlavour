package com.example.funflavour;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeEntry3 extends AppCompatActivity {
private EditText mango;
private Button save,finish;
private TextView totalMangoTextView,totalLowVolTextView,totalHighVolTextView,totalSum,
        lowCount, highCount,mangoCountTextView;
private FirebaseAuth mAuth;
private FirebaseFirestore firebaseFirestore;
public int totalLowVolume,totalHighVolume, totalMango=0, sum=0, lowVolCount,highVolCount;
private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry3);
        initialize();
        linearLayout.setVisibility(View.INVISIBLE);
        finish.setVisibility(View.INVISIBLE);
        retreiveLowHighRecords();
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearLayout.setVisibility(View.VISIBLE);
            finish.setVisibility(View.VISIBLE);

            String mangoStr=mango.getText().toString();
            if (!TextUtils.isEmpty(mangoStr)){
                int mangoCount=Integer.parseInt(mangoStr);
//Save the data in firebase
            Map<String,Object> data=new HashMap<>();
            data.put("Time", FieldValue.serverTimestamp());
            data.put(Contract.MANGO,mangoCount);
                firebaseFirestore.collection("Mango").document(getDate()).
                        set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MakeEntry3.this,"Successfull ", Toast.LENGTH_SHORT).show();

                        } else {
                            String exception = task.getException().toString();
                            Toast.makeText(MakeEntry3.this, "Error : " + exception, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //Save in records
                totalMango=mangoCount*7;
                sum=sum+totalMango;
                data.put("Total",totalMango);
                    totalMangoTextView.setText(String.valueOf(totalMango));

                firebaseFirestore.collection("Records").document("Mango").collection(getDate()).document("Calculations").set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                        }else{
                            String exception = task.getException().toString();
                            Toast.makeText(MakeEntry3.this, "Error : " + exception, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mangoCountTextView.setText(String.valueOf(mangoCount));
                totalHighVolTextView.setText(String.valueOf(totalHighVolume));

                totalLowVolTextView.setText(String.valueOf(totalLowVolume));
                totalSum.setText(String.valueOf(sum));

                Log.v("Values",mangoCount+":"+totalLowVolume+":"+totalHighVolume);
                disableButtons();
                Map<String,Object> finalRecord=new HashMap<>();
                finalRecord.put("Time",FieldValue.serverTimestamp());
                finalRecord.put("High Volume",totalHighVolume);
                finalRecord.put("Low Volume",totalLowVolume);
                finalRecord.put("Mango",totalMango);
                finalRecord.put("High Count",highVolCount);
                finalRecord.put("Low Count",lowVolCount);
                finalRecord.put("Mango Count",mangoCount);
                finalRecord.put("Total Amount",sum);
                firebaseFirestore.collection("Records").document(getDate()).set(finalRecord).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                            Toast.makeText(getApplicationContext(),"Records Saved!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                Toast.makeText(MakeEntry3.this,"Field cannot be empty",Toast.LENGTH_SHORT).show();
            }

        }
    });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MakeEntry3.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //This will be used to name the document
    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String dateStr=formatter.format(date).toString();
        return dateStr;
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser==null){
            startActivity(new Intent(MakeEntry3.this,SignIn.class));
            finish();
        }

    }
    private void retreiveLowHighRecords(){
        //Gets the total amount and count for the textView
        Query lastRecord1= firebaseFirestore.collection("Records").document("High Volume").collection("Calculations")
                .orderBy("Time", Query.Direction.DESCENDING).limit(1);
        lastRecord1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                DocumentSnapshot ds=documentSnapshots.getDocuments().get(0);
                if (ds.exists()){
                    totalHighVolume=Integer.parseInt(ds.getData().get("TotalAmount").toString());
                    highVolCount=Integer.parseInt(ds.getData().get("TotalCount").toString());
                    highCount.setText(String.valueOf(highVolCount));
                    sum=sum+totalHighVolume;
                            
                }
            }
        });
        //Gets the total amount and count for the textView

        Query lastRecord= firebaseFirestore.collection("Records").document("Low Volume").collection("Calculations")
                .orderBy("Time", Query.Direction.DESCENDING).limit(1);
        lastRecord.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                DocumentSnapshot ds=documentSnapshots.getDocuments().get(0);
                if (ds.exists()){
                    totalLowVolume=totalLowVolume+Integer.parseInt(ds.getData().get("TotalAmount").toString());
                    lowVolCount=Integer.parseInt(ds.getData().get("TotalCount").toString());
                    lowCount.setText(String.valueOf(lowVolCount));
                    sum=sum+totalLowVolume;
                }
            }
        });
    }
    private void disableButtons(){

        mango.setEnabled(false);
        save.setEnabled(false);
    }
    private void initialize(){
        mango=findViewById(R.id.mango);
        save=findViewById(R.id.save);
        linearLayout=findViewById(R.id.linearLayout);
        totalLowVolTextView=findViewById(R.id.lowVolTextView);
        totalHighVolTextView=findViewById(R.id.highVolTextView);
        totalSum=findViewById(R.id.total);
        finish=findViewById(R.id.finish);
        totalMangoTextView=findViewById(R.id.mangoTextView);
        lowCount=findViewById(R.id.lowVolCount);
        highCount=findViewById(R.id.highVolCount);
        mangoCountTextView=findViewById(R.id.mangoCount);
        mAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
    }
}
