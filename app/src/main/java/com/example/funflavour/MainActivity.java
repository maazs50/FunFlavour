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

    Button makeEntry;

        private FirebaseAuth mAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            makeEntry=findViewById(R.id.entry);
            makeEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,MakeEntry.class));
                }
            });
            mAuth=FirebaseAuth.getInstance();

//Map for entry

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