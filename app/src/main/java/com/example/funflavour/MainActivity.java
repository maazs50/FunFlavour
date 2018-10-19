package com.example.funflavour;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button makeEntry,records,signOut, exit;
        private FirebaseAuth mAuth;
        private FirebaseFirestore firebaseFirestore;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initialize();
            setTitle("FUN FLAVORS");
            makeEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,MakeEntry.class));
                }
            });

            records.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,RecordList.class));
                }
            });
//Map for entry
signOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mAuth.signOut();
        sendToLogin();
    }
});

exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
});

        }

    private void initialize(){
    records=findViewById(R.id.records);
    makeEntry=findViewById(R.id.entry);
    signOut=findViewById(R.id.signout);
    exit=findViewById(R.id.exit);
    mAuth=FirebaseAuth.getInstance();
    firebaseFirestore=FirebaseFirestore.getInstance();
        }

    @Override
        protected void onStart() {
            super.onStart();
            FirebaseUser currentUser=mAuth.getCurrentUser();
            if (currentUser==null){
                sendToLogin();
            }


        }

        private void sendToLogin() {
            Intent intent=new Intent(MainActivity.this, SignIn.class);
            startActivity(intent);
            finish();
        }


    }