package com.example.pharmasentinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    Button BTNREGHere , BTNbackReg ;
    TextView NameReg , EmailReg , PasswordReg ;
    FirebaseAuth mAuth;
    DatabaseReference databaseNameRegistration ;
    ProgressDialog progressDialog ;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BTNREGHere = findViewById(R.id.btnRegisterHere) ;
        BTNbackReg = findViewById(R.id.btnbackreg);
        NameReg = findViewById(R.id.txtEnterName) ;
        EmailReg = findViewById(R.id.txtEmailReg) ;
        PasswordReg = findViewById(R.id.txtPasswordReg) ;
        databaseNameRegistration = FirebaseDatabase.getInstance().getReference("Users");
        progressDialog = new ProgressDialog( this) ;
        mAuth = FirebaseAuth.getInstance() ;

        BTNREGHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunRegisterHere();
            }
        });

        BTNbackReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

    }

    private void FunRegisterHere() {

        final String email;
        email = EmailReg.getText().toString().trim().toLowerCase();
        String password = PasswordReg.getText().toString().trim();

        final String name = NameReg.getText().toString().trim();

        if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailReg.setError("Please Enter a valid Email");
            return ;
        }

        if(TextUtils.isEmpty(password) || password.length() <4 || password.length() > 10){
            PasswordReg.setError("Enter the Password");
        }

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please Enter Name" , Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User ...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email ,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
/*
                            String id= databaseNameRegistration.push().getKey();
                            NameRegistration nameRegistration = new NameRegistration(id,name,email);
                            databaseNameRegistration.child(id).setValue(nameRegistration);
                            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");*/
                            Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Not Registered",Toast.LENGTH_LONG).show();
                        }

                        progressDialog.dismiss();
                    }
                });

    }
}
