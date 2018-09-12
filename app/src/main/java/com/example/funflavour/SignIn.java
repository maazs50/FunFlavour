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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private ProgressBar loginProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        loginEmailText = findViewById(R.id.reg_email);
        loginPassText = findViewById(R.id.reg_confirm_pass);
        loginBtn = findViewById(R.id.login_btn);
        loginProgress=findViewById(R.id.login_progress);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail=loginEmailText.getText().toString();
                String loginPass =loginPassText.getText().toString();
                if (!TextUtils.isEmpty(loginEmail)&&!TextUtils.isEmpty(loginPass)){
                    loginProgress.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("first", "Alan");
                                        user.put("middle", "Mathison");
                                        user.put("last", "Turing");
                                        user.put("born", 1912);


                                        sendToMain();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        String errorMessage=task.getException().toString();
                                        Toast.makeText(SignIn.this, errorMessage,
                                                Toast.LENGTH_SHORT).show();

                                    }
                                    loginProgress.setVisibility(View.INVISIBLE);
                                    // ...
                                }

                            });
                }
            }
        });
    }
    private void sendToMain(){
        startActivity(new Intent(SignIn.this,MainActivity.class));
        finish();
    }
}
