package com.example.seccion_05_login.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seccion_05_login.R;
import com.example.seccion_05_login.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private SwitchCompat switchRemember;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BindUI();

        // solo para leer shared preferences
        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        //
        setCredentialsIfExist();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                if (login(email, password)) {
                    saveOnPreferences(email, password);
                    goToMain();
                }
            }
        });
    }

    // Metodos ///
    private void BindUI() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (SwitchCompat) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }

    private boolean login(String email, String password) {
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email is not Valid, please try again.", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "Password is not Valid, 4 characters or more. Please try again.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        // FLAGS PARA EVITAR QUE EL USUARIO REGRESE CON EL BOTÓN ATRÁS
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void saveOnPreferences(String email, String password) {
        if (switchRemember.isChecked()) {
            //Instancia editor para poder escribir shared preferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            //Commit detiene el codigo hasta que se guardan todos los datos
            //editor.commit();
            //Apply deja que el codigo continue mientras se van guardando los datos
            editor.apply();
        } else {
            Util.removeSharedPreferences(preferences);
        }
    }

    private void setCredentialsIfExist() {
        String email = Util.getUserMailPreferences(preferences);
        String password = Util.getUserPasswordPreferences(preferences);
        if(!email.isEmpty() && !password.isEmpty()){
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            switchRemember.setChecked(true);
        }
    }

//    private String getUserMailPreferences() {
//        return preferences.getString("email", "");
//    }
//
//    private String getUserPasswordPreferences() {
//        return preferences.getString("password", "");
//    }
    // Metodos ///
}