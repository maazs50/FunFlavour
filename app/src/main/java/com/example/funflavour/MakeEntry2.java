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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeEntry2 extends AppCompatActivity {
    private EditText d101;
    private EditText d102;
    private EditText d103;
    private EditText d104;
    private EditText d105;
    private EditText d106;
    private EditText d107;
    private EditText d108;
    private EditText d109;
    private EditText d110;
    private Button save;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private Map<String,Object> calRecord2;
    private ProgressBar makeProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry2);
        d101=findViewById(R.id.d101);
        d102=findViewById(R.id.d102);
        d103=findViewById(R.id.d103);
        d104=findViewById(R.id.d104);
        d105=findViewById(R.id.d105);
        d106=findViewById(R.id.d106);
        d107=findViewById(R.id.d107);
        d108=findViewById(R.id.d108);
        d109=findViewById(R.id.d109);
        d110=findViewById(R.id.d110);
        save=findViewById(R.id.save10);
        mAuth=FirebaseAuth.getInstance();
        makeProgress=findViewById(R.id.makeProgress);
        mFireStore=FirebaseFirestore.getInstance();
        calRecord2=new HashMap<>();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String d1String=d101.getText().toString();

                String d2String=d102.getText().toString();
                String d3String=d103.getText().toString();
                String d4String=d104.getText().toString();
                String d5String=d105.getText().toString();
                String d6String=d106.getText().toString();
                String d7String=d107.getText().toString();
                String d8String=d108.getText().toString();
                String d9String=d109.getText().toString();
                String d10String=d110.getText().toString();



                if (!TextUtils.isEmpty(d1String)&&!TextUtils.isEmpty(d2String)&&
                        !TextUtils.isEmpty(d3String)&&!TextUtils.isEmpty(d4String)&&
                        !TextUtils.isEmpty(d5String)&&!TextUtils.isEmpty(d6String)&&
                        !TextUtils.isEmpty(d7String)&&!TextUtils.isEmpty(d8String)&&
                        !TextUtils.isEmpty(d9String)&&!TextUtils.isEmpty(d10String)) {
                    makeProgress.setVisibility(View.VISIBLE);
                    final int d1=Integer.parseInt(d1String);
                    final int d2=Integer.parseInt(d2String);
                    final int d3=Integer.parseInt(d3String);
                    final int d4=Integer.parseInt(d4String);
                    final int d5=Integer.parseInt(d5String);
                    final int d6=Integer.parseInt(d6String);
                    final int d7=Integer.parseInt(d7String);
                    final int d8=Integer.parseInt(d8String);
                    final int d9=Integer.parseInt(d9String);
                    final int d10=Integer.parseInt(d10String);

                    Map<String, Object> record = new HashMap<>();
                    record.put("Time", FieldValue.serverTimestamp());
                    record.put(Contract.LEMON, d1);
                    record.put(Contract.FRUIT_BEER, d2);
                    record.put(Contract.STRAWBERRY, d3);
                    record.put(Contract.ORANGE, d4);
                    record.put(Contract.JEERA, d5);
                    record.put(Contract.COLA, d6);
                    record.put(Contract.BLUEBERRY, d7);
                    record.put(Contract.GRAPES, d8);
                    record.put(Contract.LITCHI, d9);
                    record.put(Contract.APPLE, d10);


                    mFireStore.collection("High Volume").document(getDate()).set(record).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MakeEntry2.this,"Successfull ", Toast.LENGTH_SHORT).show();

                            } else {
                                String exception = task.getException().toString();
                                Toast.makeText(MakeEntry2.this, "Error : " + exception, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//Get the last document records in order to calculate the counts TodaysRecord - lastDOcumentsRecord

                    Query lastRecords =  mFireStore.collection("High Volume").orderBy("Time", Query.Direction.DESCENDING).limit(2);
                    lastRecords.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            DocumentSnapshot document=documentSnapshots.getDocuments().get(1);
                            if (document.exists()) {
                                int lemonCount= Integer.parseInt(document.getData().get(Contract.LEMON).toString());
                                int fruitBeerCount= Integer.parseInt(document.getData().get(Contract.FRUIT_BEER).toString());
                                int strawberryCount= Integer.parseInt(document.getData().get(Contract.STRAWBERRY).toString());
                                int orangeCount= Integer.parseInt(document.getData().get(Contract.ORANGE).toString());
                                int jeeraCount= Integer.parseInt(document.getData().get(Contract.JEERA).toString());
                                int colaCount= Integer.parseInt(document.getData().get(Contract.COLA).toString());
                                int blueberryCount= Integer.parseInt(document.getData().get(Contract.BLUEBERRY).toString());
                                int grapesCount= Integer.parseInt(document.getData().get(Contract.GRAPES).toString());
                                int litchiCount= Integer.parseInt(document.getData().get(Contract.LITCHI).toString());
                                int appleCount= Integer.parseInt(document.getData().get(Contract.APPLE).toString());
                                calRecord2.put("Time",FieldValue.serverTimestamp());
                                calRecord2.put(Contract.LEMON,d1-lemonCount);
                                calRecord2.put(Contract.FRUIT_BEER, d2-fruitBeerCount);
                                calRecord2.put(Contract.STRAWBERRY, d3-strawberryCount);
                                calRecord2.put(Contract.ORANGE,d4-orangeCount);
                                calRecord2.put(Contract.JEERA, d5-jeeraCount);
                                calRecord2.put(Contract.COLA, d6-colaCount);
                                calRecord2.put(Contract.BLUEBERRY, d7-blueberryCount);
                                calRecord2.put(Contract.GRAPES,d8-grapesCount);
                                calRecord2.put(Contract.LITCHI, d9-litchiCount);
                                calRecord2.put(Contract.APPLE,d10-appleCount);
                                mFireStore.collection("Records").document("High Volume").collection(getDate()).document("Calculations").set(calRecord2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            makeProgress.setVisibility(View.INVISIBLE);
                                            startActivity(new Intent(MakeEntry2.this,MakeEntry3.class));
                                            finish();
                                        }
                                        else{
                                            String exception = task.getException().toString();
                                            Toast.makeText(MakeEntry2.this, "Error : " + exception, Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                            } else {
                                Log.d("Data", "No such document");
                            }

                        }
                    });
                     makeProgress.setVisibility(View.INVISIBLE);
                    
                    
                }
                else{
                    Toast.makeText(MakeEntry2.this,"Enter all the field",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
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
            startActivity(new Intent(MakeEntry2.this,SignIn.class));
            finish();
        }

    }
}
