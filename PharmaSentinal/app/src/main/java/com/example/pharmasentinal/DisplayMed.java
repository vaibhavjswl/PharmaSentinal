package com.example.pharmasentinal;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DisplayMed extends AppCompatActivity {

    ListView listView ;
    FirebaseDatabase database ;
    DatabaseReference ref ;
    ArrayList<Medicine> Medicines ;
    ArrayAdapter<Medicine> adapter ;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_displaymed);

        listView = (ListView) findViewById(R.id.MedListView) ;

        database = FirebaseDatabase.getInstance() ;
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid() ;
        ref = database.getReference();


        Medicines = new ArrayList<Medicine>();

        adapter = new ArrayAdapter<Medicine>(this ,R.layout.medinfo,R.id.medinfo , Medicines) ;


        ref.child("Users").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    Medicine med = ds.getValue(Medicine.class);

                    Medicines.add(med);
                }
                Collections.sort(Medicines , new Medicine());
                listView.setAdapter(adapter) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
