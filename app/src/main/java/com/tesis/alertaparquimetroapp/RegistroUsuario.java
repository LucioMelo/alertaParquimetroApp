package com.tesis.alertaparquimetroapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroUsuario extends AppCompatActivity implements View.OnClickListener {

    Button btnRegistrarUsuario;
    EditText editTextEmail,editTextContraseña,editTextNombre;
    FirebaseAuth.AuthStateListener mAuthListenerReg;
    FirebaseAuth mAuthReg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);


        editTextEmail=(EditText) findViewById(R.id.idCorreo);
        editTextContraseña=(EditText) findViewById(R.id.idContraseñaRegistro);


        btnRegistrarUsuario=(Button)  findViewById(R.id.idRegistrarUsuario);
        btnRegistrarUsuario.setOnClickListener(this);

        mAuthReg=FirebaseAuth.getInstance();

        mAuthListenerReg = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.i("REGISTRO", "onAuthStateChanged:signed_in:" + user.getUid()+" Sesion iniciada con email: "+user.getEmail());
                    Intent intentContainer = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intentContainer);
                } else {
                    // User is signed out
                    Log.i("REGISTRO", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }


    private void registrar(String email,String contraseña)
    {

        Log.i("Email","email: "+email+"contraseña: "+contraseña);
        mAuthReg.createUserWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.i("Sesion","usuario creado correctamente");
                    //Intent menuActivity = new Intent(getApplicationContext(),MenuActivity.class);
                    //startActivity(menuActivity);
                }
                else
                    {
                        Log.e("Sesion",task.getException().getMessage()+"Usuario no creado");
                        Toast.makeText(getApplicationContext(),"No se registro",Toast.LENGTH_LONG).show();

                    }
            }
        });

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.idRegistrarUsuario)
        {
            String emailRegistro=editTextEmail.getText().toString();
            String contraseñaRegistro=editTextContraseña.getText().toString();
            registrar(emailRegistro,contraseñaRegistro);

        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        mAuthReg.addAuthStateListener(mAuthListenerReg);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mAuthListenerReg!=null)
        {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListenerReg);
        }
    }


}
