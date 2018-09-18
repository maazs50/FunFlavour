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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.*;
import java.text.*;

public class MakeEntry extends AppCompatActivity {
    private EditText d51;
    private EditText d52;
    private EditText d53;
    private EditText d54;
    private EditText d55;
    private EditText d56;
    private EditText d57;
    private EditText d58;
    private EditText d59;
    private EditText d510;
    private Button save;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFireStore;
    private DocumentSnapshot lastVisible;
    private Map<String,Object> calRecord;
    private ProgressBar makeProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry);
        d51=findViewById(R.id.d51);
        d52=findViewById(R.id.d52);
        d53=findViewById(R.id.d53);
        d54=findViewById(R.id.d54);
        d55=findViewById(R.id.d55);
        d56=findViewById(R.id.d56);
        d57=findViewById(R.id.d57);
        d58=findViewById(R.id.d58);
        d59=findViewById(R.id.d59);
        d510=findViewById(R.id.d510);
        save=findViewById(R.id.save5);
        makeProgress=findViewById(R.id.makeProgress);
        mAuth=FirebaseAuth.getInstance();
        mFireStore=FirebaseFirestore.getInstance();
        calRecord=new HashMap<>();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String d1String=d51.getText().toString();

                String d2String=d52.getText().toString();
                String d3String=d53.getText().toString();
                String d4String=d54.getText().toString();
                String d5String=d55.getText().toString();
                String d6String=d56.getText().toString();
                String d7String=d57.getText().toString();
                String d8String=d58.getText().toString();
                String d9String=d59.getText().toString();
                String d10String=d510.getText().toString();



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

//Store the value in count
    mFireStore.collection("Low Volume").document(getDate()).set(record).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
if (task.isSuccessful()){
    Toast.makeText(MakeEntry.this,"Successfull ", Toast.LENGTH_SHORT).show();

} else {
    String exception = task.getException().toString();
    Toast.makeText(MakeEntry.this, "Error : " +exception, Toast.LENGTH_SHORT).show();
}
        }
    });

//Get the last document records in order to calculate the counts TodaysRecord - lastDOcumentsRecord
    Query lastRecords =  mFireStore.collection("Low Volume").orderBy("Time", Query.Direction.DESCENDING).limit(2);
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
                calRecord.put("Time",FieldValue.serverTimestamp());
                calRecord.put(Contract.LEMON,d1-lemonCount);
                calRecord.put(Contract.FRUIT_BEER, d2-fruitBeerCount);
                calRecord.put(Contract.STRAWBERRY, d3-strawberryCount);
                calRecord.put(Contract.ORANGE,d4-orangeCount);
                calRecord.put(Contract.JEERA, d5-jeeraCount);
                calRecord.put(Contract.COLA, d6-colaCount);
                calRecord.put(Contract.BLUEBERRY, d7-blueberryCount);
                calRecord.put(Contract.GRAPES,d8-grapesCount);
                calRecord.put(Contract.LITCHI, d9-litchiCount);
                calRecord.put(Contract.APPLE,d10-appleCount);




                mFireStore.collection("Records").document("Low Volume").collection(getDate()).document("Calculations").set(calRecord).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent=new Intent(MakeEntry.this,MakeEntry2.class);
                            makeProgress.setVisibility(View.INVISIBLE);

                            startActivity(intent);
                        }
                        else{
                            String exception = task.getException().toString();
                            Toast.makeText(MakeEntry.this, "Error : " + exception, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                } else {
                    Log.d("Data", "No such document");
                }
            makeProgress.setVisibility(View.INVISIBLE);
        }
    });

}
else{
    Toast.makeText(MakeEntry.this,"Enter all the field",Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(MakeEntry.this,SignIn.class));
            finish();
        }

    }
}
