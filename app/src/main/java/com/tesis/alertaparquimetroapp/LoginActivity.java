package com.tesis.alertaparquimetroapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEntrar;
    EditText editTextEmail,editTextContraseña;
    TextView textViewRegistro;

    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextEmail=(EditText) findViewById(R.id.idEmail);
        editTextContraseña=(EditText) findViewById(R.id.idContraseña);

        textViewRegistro=(TextView) findViewById(R.id.idRegistroLogin);
        textViewRegistro.setOnClickListener(this);

        btnEntrar=(Button) findViewById(R.id.idEntrar);
        btnEntrar.setOnClickListener(this);

        mAuthListener=new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user=firebaseAuth.getCurrentUser(); //FirebaseUser.firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.i("SESION","Sesion iniciada con email: "+user.getEmail());
                    Intent intentContainer = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intentContainer);
                }
                else
                    {
                        Log.i("SESION","Secion cerrada");
                    }

            }
        };


    }

    private void iniciaSesion(String email,String contraseña)
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.i("Sesion","sesion iniciada correctamente");
                }
                else
                {
                    Log.e("Sesion",task.getException().getMessage()+"");
                }
            }
        });


    }

    @Override
    public void onClick(View v)
    {

        if(v.getId()==R.id.idEntrar)
        {
            String email=editTextEmail.getText().toString();
            String contraseña=editTextContraseña.getText().toString();

            iniciaSesion(email,contraseña);

        }
        else if(v.getId()==R.id.idRegistroLogin)
        {
            Intent intentContainer = new Intent(this,RegistroUsuario.class);
            startActivity(intentContainer);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

       FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);

    }


    @Override
    protected void onStop() {
        super.onStop();

        if(mAuthListener!=null)
        {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}

