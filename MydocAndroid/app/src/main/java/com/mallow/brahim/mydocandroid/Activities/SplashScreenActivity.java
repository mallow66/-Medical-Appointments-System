package com.mallow.brahim.mydocandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mallow.brahim.mydocandroid.Model.Person;
import com.mallow.brahim.mydocandroid.R;
import com.mallow.brahim.mydocandroid.SessionManager.MySessionManager;

/**
 * Created by brahim on 8/15/17.
 */

public class SplashScreenActivity extends AppCompatActivity {


    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                MySessionManager sessionManager = new MySessionManager(getApplicationContext());
                if(/*sessionManager.isLoggedIn()*/ 1 == 0){
                    Bundle b = new Bundle();
                    Person pe = sessionManager.getPersonSession();
                    b.putSerializable("person", pe);

                    Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class );
                    i.putExtras(b);
                    startActivity(i);

                }
                else{
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                }


                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
