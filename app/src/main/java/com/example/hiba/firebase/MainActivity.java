package com.example.hiba.firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private ProgressDialog progressD;
    EditText e1,e2;
   TextView sign;
    Button b;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        progressD=new ProgressDialog(this);
       e1=(EditText)findViewById(R.id.TextEmail);
        e2=(EditText)findViewById(R.id.Textpass);
        sign=(TextView)findViewById(R.id.TextView);
        b=(Button)findViewById(R.id.buttonLogin);

        b.setOnClickListener(this);
        sign.setOnClickListener(this);
    }
public void registerUser() {
    String email=e1.getText().toString().trim();
    String pass=e2.getText().toString().trim();

    progressD.setMessage("registering user ..");
    progressD.show();

    mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (task.isSuccessful()) {
                        progressD.hide();
                        Toast.makeText(MainActivity.this, "Authentication succed.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    // ...
                }
            });
}

    @Override
    public void onClick(View v) {
        if(v == b)
        {
            registerUser();
        }

    }
}
