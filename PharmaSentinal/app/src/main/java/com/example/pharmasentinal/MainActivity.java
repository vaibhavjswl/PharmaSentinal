package com.example.pharmasentinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button ActBtnLogin, ActBtnRegister;
    EditText EmailLogin, PasswordLogin;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActBtnLogin = findViewById(R.id.btnLogin);
        ActBtnRegister = findViewById(R.id.btnRegisterHere);
        EmailLogin = findViewById(R.id.txtEmail);
        PasswordLogin = findViewById(R.id.txtPassword);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        ActBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunLoginUser();
            }
        });

        ActBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }

    private void FunLoginUser() {
        String email = EmailLogin.getText().toString().trim().toLowerCase();
        String password = PasswordLogin.getText().toString().trim().toLowerCase();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailLogin.setError("Enter a valid Email Address");
            return;
        }

        if (TextUtils.isEmpty(password) || password.length()<4 || password.length() >10) {
            PasswordLogin.setError("Enter a valid Password");
            return;
        }

        progressDialog.setMessage("Please Wait ....");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Successfully" , Toast.LENGTH_SHORT).show();

                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid() ;

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users") ;



                          /*      //Test Statements
                            ref.child(id).push().setValue(new Medicine( new Date(2018,5,10) ,"Cetrizine"  ,12));
                            ref.child(id).push().setValue(new Medicine( new Date(2019,2,2) ,"Sumo" , 1));
                            ref.child(id).push().setValue(new Medicine( new Date(2015,6,16) ,"Allopurinol" , 13));
                            ref.child(id).push().setValue(new Medicine( new Date(2019,4,27) ,"Acyclovir" , 15));



                            //ref.child(id).removeValue() ;*/
                            startActivity(new Intent(MainActivity.this, mscrn.class));
                        }

                        else{
                            Toast.makeText(MainActivity.this,"Login Unsuccessfull" , Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
