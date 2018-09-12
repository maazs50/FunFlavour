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
        mFireStore=FirebaseFirestore.getInstance();

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


                    mFireStore.collection("High Volume").document(getDate()).set(record).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MakeEntry2.this,"Successfull ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MakeEntry2.this,MakeEntry3.class));
                                finish();
                            } else {
                                String exception = task.getException().toString();
                                Toast.makeText(MakeEntry2.this, "Error : " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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

}
