package com.mallow.brahim.mydocandroid.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.mallow.brahim.mydocandroid.Model.Person;

/**
 * Created by brahim on 9/14/17.
 */

public class MySessionManager {

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;

    private static final String PREF_NAME = "myDoctor";
    private static  final int PRIVATE_MODE = 0;
    private static final String USERNAME = "shared_username";
    private static final String FIRST_NAME = "shared_first_name";
    private static final String LAST_NAME = "shared_last_name" ;
    private static final String IS_LOGIN = "is_login";

    public MySessionManager(Context context){
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(Person person){
        editor.putString(USERNAME, person.getUsername());
        editor.putString(FIRST_NAME, person.getFirstName());
        editor.putString(LAST_NAME, person.getLastName());
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }


    public Person getPersonSession(){
        Person person = new Person();
        person.setUsername(sharedPreferences.getString(USERNAME, null));
        person.setFirstName(sharedPreferences.getString(FIRST_NAME, null));
        person.setLastName(sharedPreferences.getString(LAST_NAME, null));


        return person;
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


}
