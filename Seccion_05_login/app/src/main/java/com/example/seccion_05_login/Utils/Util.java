package com.example.seccion_05_login.Utils;

import android.content.SharedPreferences;

public class Util {

    //Static para poder hacer uso del metodo sin instanciar el objeto
    public static String getUserMailPreferences(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static String getUserPasswordPreferences(SharedPreferences preferences) {
        return preferences.getString("password", "");
    }

    public static void removeSharedPreferences (SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }
}
