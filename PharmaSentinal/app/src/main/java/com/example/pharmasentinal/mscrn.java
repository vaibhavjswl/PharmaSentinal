package com.example.pharmasentinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class mscrn extends AppCompatActivity {

    Button addmed , seeallmed  , deletemed  , dispexpmed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mscreen);

        addmed = (Button) findViewById(R.id.mscrnbtn1) ;
        seeallmed = (Button) findViewById(R.id.mscrnbtn2) ;
        deletemed = (Button) findViewById(R.id.mscrnbtn3) ;
        dispexpmed = (Button) findViewById(R.id.mscrnbtn4) ;


        addmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mscrn.this, addMedicine.class));
            }
        });

        seeallmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mscrn.this, DisplayMed.class));
            }
        });

        deletemed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mscrn.this, deletemed.class));
            }
        });

        dispexpmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mscrn.this, dispexpmed.class));
            }
        });
    }
}
