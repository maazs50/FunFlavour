package com.example.funflavour;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

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
        mAuth=FirebaseAuth.getInstance();
        mFireStore=FirebaseFirestore.getInstance();

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
    int d1=Integer.parseInt(d1String);
    int d2=Integer.parseInt(d2String);
    int d3=Integer.parseInt(d3String);
    int d4=Integer.parseInt(d4String);
    int d5=Integer.parseInt(d5String);
    int d6=Integer.parseInt(d6String);
    int d7=Integer.parseInt(d7String);
    int d8=Integer.parseInt(d8String);
    int d9=Integer.parseInt(d9String);
    int d10=Integer.parseInt(d10String);

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


    mFireStore.collection("Low Volume").document(getDate()).set(record).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
if (task.isSuccessful()){
    Toast.makeText(MakeEntry.this,"Successfull ", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(MakeEntry.this,MakeEntry2.class));
    finish();
} else {
    String exception = task.getException().toString();
    Toast.makeText(MakeEntry.this, "Error : " + task.getException().toString(), Toast.LENGTH_SHORT).show();
}
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

}
