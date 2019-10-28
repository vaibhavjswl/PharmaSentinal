package com.example.pharmasentinal;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class deletemed extends AppCompatActivity {

    Button btnDelMed ;
    TextView delmedname ;
    String id;


    FirebaseDatabase database ;
    DatabaseReference ref ;
    ArrayList<Medicine> arr ;
    boolean found ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deletemed);

        delmedname = findViewById(R.id.delete_med_en) ;
        btnDelMed = findViewById(R.id.btnDelMed) ;
        database = FirebaseDatabase.getInstance() ;
        id = FirebaseAuth.getInstance().getCurrentUser().getUid() ;
        ref = database.getReference();


        arr = new ArrayList<Medicine>();

        btnDelMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMed();
            }
        });

    }


    void DeleteMed(){

        database = FirebaseDatabase.getInstance() ;
        final String id = FirebaseAuth.getInstance().getCurrentUser().getUid() ;
        ref = database.getReference();
        final String medname = delmedname.getText().toString().trim().toLowerCase() ;
         found = false;


        ref.child("Users").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    Medicine med = (Medicine) ds.getValue(Medicine.class);

                    if(med.medName.toLowerCase().equals(medname)){
                        ref.child("Users").child(id).child(ds.getKey()).removeValue() ;
                        Toast.makeText(deletemed.this,"Medicine Successfully Deleted" , Toast.LENGTH_SHORT).show();
                        found = true ;
                        break ;
                    }

                }
                Collections.sort(arr , new Medicine());
                if(!found)
                    Toast.makeText(deletemed.this,"Medicine Deletion Unsuccessfull \nMedicine Not Found" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       /* if(found==true){

        }

        else{
            Toast.makeText(deletemed.this,"Medicine Deletion Unsuccessfully" , Toast.LENGTH_SHORT).show();
        }*/
    }
}
