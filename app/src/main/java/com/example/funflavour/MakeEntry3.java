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

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeEntry3 extends AppCompatActivity {
private EditText mango;
private Button save;
private FirebaseAuth mAuth;
private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry3);
    mango=findViewById(R.id.mango);
    save=findViewById(R.id.finish);
    mAuth=FirebaseAuth.getInstance();
    firebaseFirestore=FirebaseFirestore.getInstance();
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mangoStr=mango.getText().toString();
            if (!TextUtils.isEmpty(mangoStr)){
                int mangoValue=Integer.parseInt(mangoStr);

            Map<String,Object> data=new HashMap<>();
            data.put("Time", FieldValue.serverTimestamp());
            data.put(Contract.MANGO,mangoValue);
                firebaseFirestore.collection("Mango").document(getDate()).
                        set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MakeEntry3.this,"Successfull ", Toast.LENGTH_SHORT).show();

                            finish();
                        } else {
                            String exception = task.getException().toString();
                            Toast.makeText(MakeEntry3.this, "Error : " + exception, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(MakeEntry3.this,"Enter all the field",Toast.LENGTH_SHORT).show();
            }

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

}
