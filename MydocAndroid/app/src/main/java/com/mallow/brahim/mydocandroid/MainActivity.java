package com.mallow.brahim.mydocandroid;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mallow.brahim.mydocandroid.Model.Patient;
import com.mallow.brahim.mydocandroid.Model.Person;
import com.mallow.brahim.mydocandroid.Model.Role;
import com.mallow.brahim.mydocandroid.Requests.MySpringPostRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private EditText username, firstName, lastName, email, phone, address, password, date;
    private Button registerButton;
    private Gson gson = new Gson();

    private static ObjectMapper objectMapper = new ObjectMapper();

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmaz");
       // objectMapper.setDateFormat(df);

        username = (EditText) findViewById(R.id.username_editText);
        firstName = (EditText) findViewById(R.id.first_name_editText);
        lastName = (EditText) findViewById(R.id.last_name_editText);
        email= (EditText) findViewById(R.id.email_editText);
        phone= (EditText) findViewById(R.id.phone_editText);
        address = (EditText) findViewById(R.id.address_editText);
        date = (EditText) findViewById(R.id.address_editText);
        password = (EditText) findViewById(R.id.password_editText);

        registerButton = (Button) findViewById(R.id.register_button);






        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String URL = "http://192.168.1.24:8080/save";
// Post params to be sent to the server


               Patient patient = new Patient(username.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), new Date(1419038000), email.getText().toString(), phone.getText().toString(), address.getText().toString(), password.getText().toString());
                Set<Role> roles = new HashSet<Role>();
                roles.add(new Role("PATIENT"));
                patient.setRoles(roles);



              Map<String, Object> params = objectMapper.convertValue(patient, Map.class);




                MySpringPostRequest request_json = new MySpringPostRequest(URL, params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                              Toast.makeText(MainActivity.this, response.toString() , Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Failed" , Toast.LENGTH_LONG).show();
                        VolleyLog.e("Error: ", error.getMessage());

                    }
                });

// add the request object to the queue to be executed
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(request_json);
            }
        });








    }
}
