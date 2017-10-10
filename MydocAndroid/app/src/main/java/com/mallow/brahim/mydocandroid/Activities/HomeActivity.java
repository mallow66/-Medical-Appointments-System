package com.mallow.brahim.mydocandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallow.brahim.mydocandroid.Adapters.MyDoctorsAdapter;
import com.mallow.brahim.mydocandroid.Model.Doctor;
import com.mallow.brahim.mydocandroid.Model.Person;
import com.mallow.brahim.mydocandroid.R;
import com.mallow.brahim.mydocandroid.Requests.MySpringPostArrayRequest;
import com.mallow.brahim.mydocandroid.SessionManager.MySessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by brahim on 9/14/17.
 */

public class HomeActivity extends AppCompatActivity {

    private ListView myDoctorsListView;
    private MySessionManager sessionManager;
    private ObjectMapper myObjectMapper;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDoctorsListView = (ListView) findViewById(R.id.my_doctors_listView);
        Log.d("HELO", myDoctorsListView.getId()+"");
        myDoctorsListView.setAdapter(new MyDoctorsAdapter(this, this, new LinkedList<Doctor>()));

        myObjectMapper = new ObjectMapper();

        sessionManager = new MySessionManager(getApplicationContext());
        Person patient = sessionManager.getPersonSession();
       // Toast.makeText(getBaseContext(), patient.toString(), Toast.LENGTH_LONG).show();

        Map<String, String> params = new HashMap<>();
        params.put("patient", "patient1" );



        JSONObject j = new JSONObject();
        try {
            j.put("patient", "patient1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MySpringPostArrayRequest myDoctorsRequest = new MySpringPostArrayRequest("/mydoctors", j.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               // Toast.makeText(HomeActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                    Log.d("HELOOOOOO", response.toString());
                for(int  i = 0; i<response.length(); i++){

                    Doctor d = null;
                    try {
                        d = myObjectMapper.readValue(response.getJSONObject(i).toString(), Doctor.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("DOCTOR ::::::::::::::", d.toString());
                    ((MyDoctorsAdapter)myDoctorsListView.getAdapter()).add(d);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(HomeActivity.this, "ERRROR", Toast.LENGTH_LONG).show();

                    }
                });


        RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);

        queue.add(myDoctorsRequest);



    myDoctorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Doctor doctor = (Doctor)adapterView.getAdapter().getItem(i);
            Bundle b = new Bundle();
            b.putSerializable("doctor", doctor);
            Intent intent = new Intent(HomeActivity.this, MyDoctorDetailsActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
    });


    }



}
