package com.example.pharmasentinal;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

public class addMedicine extends AppCompatActivity {

    Button  addmed  ;
    TextView Enmedname , Enmedqty , Enmeddat , Enmedmnth , Enmedyr ;

    FirebaseDatabase database;
    DatabaseReference ref ;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addmedicine);

        Enmedname = findViewById(R.id.entrmedname) ;
        Enmedqty = findViewById(R.id.entermedqty) ;
        Enmeddat = findViewById(R.id.enterexpdat) ;
        Enmedmnth = findViewById(R.id.enterexpmnth)  ;
        Enmedyr = findViewById(R.id.enterexpyr) ;

        addmed = findViewById(R.id.btnAddmed) ;

        addmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            addMedicine();
            }
        });


    }

    void addMedicine(){
        String name = Enmedname.getText().toString().trim().toLowerCase() ;

        Integer qty = Integer.valueOf(Enmedqty.getText().toString().trim());
        Integer date = Integer.valueOf(Enmeddat.getText().toString().trim());
        Integer month = Integer.valueOf(Enmedmnth.getText().toString().trim());
        Integer year = Integer.valueOf(Enmedyr.getText().toString().trim());

        if((month >= 4 && year >= 2019 && qty !=0)) {

            if (month == 4 && date < 30) {
                Toast.makeText(addMedicine.this, "Cannot Add Medicine\nMedicine Already Expired\n", Toast.LENGTH_SHORT).show();
            } else {
                Date expdate = new Date(year, month, date);
                Medicine medicine = new Medicine(expdate, name, qty);

                String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                ref = FirebaseDatabase.getInstance().getReference("Users");

                ref.child(id).push().setValue(medicine);

                Toast.makeText(addMedicine.this, "Medicine Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        else if (qty == 0 ){
            Toast.makeText(addMedicine.this,"Quantity is Zero or Negative\n Enter Correct Quantity" , Toast.LENGTH_SHORT).show();
    }
        else{
            Toast.makeText(addMedicine.this,"Medicine Already Expired" , Toast.LENGTH_SHORT).show();
        }
    }


}
