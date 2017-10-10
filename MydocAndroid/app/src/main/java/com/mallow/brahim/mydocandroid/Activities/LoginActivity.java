package com.mallow.brahim.mydocandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallow.brahim.mydocandroid.MainActivity;
import com.mallow.brahim.mydocandroid.Model.Patient;
import com.mallow.brahim.mydocandroid.R;
import com.mallow.brahim.mydocandroid.Requests.MySpringPostRequest;
import com.mallow.brahim.mydocandroid.SessionManager.MySessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by brahim on 9/14/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(), "Please fill all the inputs !", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String URL =  "/loginclient";
                final Map<String, Object> params = new HashMap<>();
                params.put("username", username.getText().toString().trim());
                params.put("password", password.getText().toString().trim());

                MySpringPostRequest loginRequest = new MySpringPostRequest(URL, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean success = response.getBoolean("success");
                            if(success){
                                Toast.makeText(getBaseContext(), "Success Login", Toast.LENGTH_LONG).show();

                                Map<String, Object> params = new HashMap<>();
                                params.put("patient", username.getText().toString().trim());
                                MySpringPostRequest infoPatientRequest = new MySpringPostRequest("/infoPatient", params, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        ObjectMapper mapper = new ObjectMapper();
                                        Log.d("HHHHHHHHHH", response.toString());
                                        try {
                                            Patient patient = mapper.readValue(response.toString(), Patient.class);



                                            MySessionManager sessionManager = new MySessionManager(getApplicationContext());
                                            sessionManager.createSession(patient);



                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getBaseContext(), " Session Problem", Toast.LENGTH_LONG).show();
                                            }
                                        });


                                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                                queue.add(infoPatientRequest);
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(getBaseContext(), "Success Failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getBaseContext(), "Error Request", Toast.LENGTH_LONG).show();

                            }
                        });

                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });
    }


}
