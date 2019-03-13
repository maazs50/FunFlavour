package com.example.funflavour;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.*;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecordList extends AppCompatActivity {
    private RecyclerView mRecordList;
    private FirebaseFirestore mFirestore;
    private String Tag="Error";
    private List<Record> mList;
    private RecordListAdapter recordListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list);
        mList=new ArrayList<>();
        recordListAdapter= new RecordListAdapter(mList, RecordList.this);
        mRecordList=findViewById(R.id.recordList);
        mRecordList.setHasFixedSize(true);
        mRecordList.setLayoutManager(new LinearLayoutManager(this));
        mRecordList.setAdapter(recordListAdapter);
        mFirestore=FirebaseFirestore.getInstance();
        mFirestore.collection("Records").orderBy("time",Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
           if (e!=null){
               Log.d(Tag,e.getMessage());
           }
           for (DocumentChange doc:documentSnapshots.getDocumentChanges()){
               if (doc.getType()==DocumentChange.Type.ADDED){
                    Record record=doc.getDocument().toObject(Record.class);
                    mList.add(record);
                    recordListAdapter.notifyDataSetChanged();
               }
           }
            }
        });


      //To check monthly
//         mFirestore.collection("Records").orderBy("time",Query.Direction.DESCENDING).limit(500).addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//                long sum=0;
//                for (DocumentSnapshot doc:documentSnapshots){
//                    String value=doc.get("total_amount").toString();
//                    String time=doc.get("time").toString();
//                    sum=sum+Long.parseLong(value);
//                    Log.i(time +" : ",value);
//
//                }
//                Log.i("Sum : ",String.valueOf(sum));
//            }
//        });


    }


}
