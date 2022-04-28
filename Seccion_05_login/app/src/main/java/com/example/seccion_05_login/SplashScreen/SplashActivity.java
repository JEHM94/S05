package com.example.seccion_05_login.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.seccion_05_login.Activities.LoginActivity;
import com.example.seccion_05_login.Activities.MainActivity;
import com.example.seccion_05_login.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if (!Util.getUserMailPreferences(preferences).isEmpty() &&
                !Util.getUserPasswordPreferences(preferences).isEmpty())
            startActivity(intentMain);
        else
            startActivity(intentLogin);
        //Destruye la instancia del activity para no volver
        finish();
    }

//    private String getUserMailPreferences() {
//        return preferences.getString("email", "");
//    }
//
//    private String getUserPasswordPreferences() {
//        return preferences.getString("password", "");
//    }
}