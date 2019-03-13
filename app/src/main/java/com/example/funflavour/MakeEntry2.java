package com.example.funflavour;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private TextView p101;
    private TextView p102;
    private TextView p103;
    private TextView p104;
    private TextView p105;
    private TextView p106;
    private TextView p107;
    private TextView p108;
    private TextView p109;
    private TextView p1010;
    private Button save;
    private Button check;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private Map<String,Object> calRecord2;
    private ProgressBar makeProgress;
    Vibrator vibe ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry2);
        getSupportActionBar().setTitle("High Volume");
        initialize();
        populate();
        vibe=(Vibrator)MakeEntry2.this.getSystemService(Context.VIBRATOR_SERVICE);
        save.setEnabled(false);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculations();
                            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationsCheck();
            }
        });
    }

    private int sum(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        return r1 +r2 + r3 +r4 +r5 +r6 +r7 +r8 +r9 +r10;
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
    private void initialize(){
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
        p101=findViewById(R.id.p101);
        p102=findViewById(R.id.p102);
        p103=findViewById(R.id.p103);
        p104=findViewById(R.id.p104);
        p105=findViewById(R.id.p105);
        p106=findViewById(R.id.p106);
        p107=findViewById(R.id.p107);
        p108=findViewById(R.id.p108);
        p109=findViewById(R.id.p109);
        p1010=findViewById(R.id.p1010);
        save=findViewById(R.id.save10);
        check=findViewById(R.id.check2);
        mAuth=FirebaseAuth.getInstance();
        makeProgress=findViewById(R.id.makeProgress2);
        mFireStore=FirebaseFirestore.getInstance();
        calRecord2=new HashMap<>();
    }
    private void populate() {

        Query lastRecord =  mFireStore.collection("High Volume").orderBy("Time", Query.Direction.DESCENDING).limit(1);
        lastRecord.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                DocumentSnapshot document=documentSnapshots.getDocuments().get(0);
                String lemon=document.getData().get(Contract.LEMON).toString();
                String fruitBeer=document.getData().get(Contract.FRUIT_BEER).toString();
                String strawberry=document.getData().get(Contract.STRAWBERRY).toString();
                String orange=document.getData().get(Contract.ORANGE).toString();
                String jeera=document.getData().get(Contract.JEERA).toString();
                String cola=document.getData().get(Contract.COLA).toString();
                String blueberry=document.getData().get(Contract.BLUEBERRY).toString();
                String grapes=document.getData().get(Contract.GRAPES).toString();
                String litchi=document.getData().get(Contract.LITCHI).toString();
                String apple=document.getData().get(Contract.APPLE).toString();

                p101.setText(lemon);
                p102.setText(fruitBeer);
                p103.setText(strawberry);
                p104.setText(orange);
                p105.setText(jeera);
                p106.setText(cola);
                p107.setText(blueberry);
                p108.setText(grapes);
                p109.setText(litchi);
                p1010.setText(apple);
            }
        });


    }
private void calculations(){
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
        vibe.vibrate(100);
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
                    int r1 = d1 - lemonCount;
                    int r2 = d2 - fruitBeerCount;
                    int r3 = d3 - strawberryCount;
                    int r4 = d4 - orangeCount;
                    int r5 = d5 - jeeraCount;
                    int r6 = d6 - colaCount;
                    int r7 = d7 - blueberryCount;
                    int r8 = d8 - grapesCount;
                    int r9 = d9 - litchiCount;
                    int r10 = d10 - appleCount;
                    int sum=sum(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10) ;
                    calRecord2.put("Time",FieldValue.serverTimestamp());
                    calRecord2.put(Contract.LEMON,r1);
                    calRecord2.put(Contract.FRUIT_BEER, r2);
                    calRecord2.put(Contract.STRAWBERRY, r3);
                    calRecord2.put(Contract.ORANGE,r4);
                    calRecord2.put(Contract.JEERA, r5);
                    calRecord2.put(Contract.COLA, r6);
                    calRecord2.put(Contract.BLUEBERRY, r7);
                    calRecord2.put(Contract.GRAPES,r8);
                    calRecord2.put(Contract.LITCHI, r9);
                    calRecord2.put(Contract.APPLE,r10);
                    calRecord2.put(Contract.TOTAL_COUNT,sum);
                    calRecord2.put(Contract.TOTAL_AMOUNT,sum*10);
                    mFireStore.collection("Records").document("High Volume").collection("Calculations").document(getDate()).set(calRecord2).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    protected void calculationsCheck(){
        //getting the edittext values
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
        //If  all the editTexts are filled
        if (!TextUtils.isEmpty(d1String)&&!TextUtils.isEmpty(d2String)&&
                !TextUtils.isEmpty(d3String)&&!TextUtils.isEmpty(d4String)&&
                !TextUtils.isEmpty(d5String)&&!TextUtils.isEmpty(d6String)&&
                !TextUtils.isEmpty(d7String)&&!TextUtils.isEmpty(d8String)&&
                !TextUtils.isEmpty(d9String)&&!TextUtils.isEmpty(d10String)) {
            makeProgress.setVisibility(View.VISIBLE);
            //edit text value into integer
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



//Get the last document records in order to calculate the counts TodaysRecord - lastDOcumentsRecord
            Query lastRecords =  mFireStore.collection("High Volume").orderBy("Time", Query.Direction.DESCENDING).limit(1);
            lastRecords.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot documentSnapshots) {
                    DocumentSnapshot document=documentSnapshots.getDocuments().get(0);
                    if (document.exists()) {
                        //10130
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
                        //Todays entry - last documents entry(yesterday)
                        //10140-10130
                        int r1 = d1 - lemonCount;
                        int r2 = d2 - fruitBeerCount;
                        int r3 = d3 - strawberryCount;
                        int r4 = d4 - orangeCount;
                        int r5 = d5 - jeeraCount;
                        int r6 = d6 - colaCount;
                        int r7 = d7 - blueberryCount;
                        int r8 = d8 - grapesCount;
                        int r9 = d9 - litchiCount;
                        int r10 = d10 - appleCount;
                        //returns the no of glases sold
                        int sum=sum(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10) ;
                        int total_amt= sum*10;
                        final AlertDialog.Builder alertBuilder=new AlertDialog.Builder(MakeEntry2.this);

                        alertBuilder.setTitle("Check for count ");
                        alertBuilder.setMessage("Glass Count  : "+sum+"\n"+"Total Amount : "+total_amt);

                        alertBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertBuilder.create().show();
                        save.setEnabled(true);
                    } else
                    {
                        Toast.makeText(MakeEntry2.this,"No such document", Toast.LENGTH_SHORT).show();
                    }
                    makeProgress.setVisibility(View.INVISIBLE);
                }
            });

        }
        else{
            Toast.makeText(MakeEntry2.this,"Enter all the field",Toast.LENGTH_SHORT).show();
        }


    }

}
