package com.mallow.brahim.mydocandroid.Activities;

import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallow.brahim.mydocandroid.Model.Appointment;
import com.mallow.brahim.mydocandroid.Model.Doctor;
import com.mallow.brahim.mydocandroid.Model.Patient;
import com.mallow.brahim.mydocandroid.Model.Person;
import com.mallow.brahim.mydocandroid.R;
import com.mallow.brahim.mydocandroid.Requests.MySpringPostArrayRequest;
import com.mallow.brahim.mydocandroid.SessionManager.MySessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by brahim on 9/26/17.
 */

public class MyDoctorDetailsActivity extends AppCompatActivity {

    private Set<Appointment> appointments;
    private ObjectMapper objectMapper;
    private Doctor doctor;
    private WeekView weekView;

    private Person patient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor_details);
        weekView = (WeekView) findViewById(R.id.my_weekView);
        MySessionManager sessionManager = new MySessionManager(this);
        patient = sessionManager.getPersonSession();
        doctor = (Doctor) getIntent().getExtras().getSerializable("doctor");

        appointments = new HashSet<>();
        objectMapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("doctor", "doctor1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MySpringPostArrayRequest appointmentRequest = new MySpringPostArrayRequest("/mydoctordetails", jsonObject.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_LONG).show();
                Appointment appointment = null;
                    for (int i = 0; i<response.length(); i++){
                        try {
                            appointment =  objectMapper.readValue(response.getJSONObject(i).toString(), Appointment.class);
                            appointments.add(appointment);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                Log.d("APPOINTEMENTS :::::::", appointments.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Error
                        Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });








        weekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {

            }
        });



        weekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

                /*Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 3);
                startTime.set(Calendar.MINUTE, 12);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.add(Calendar.HOUR, 2);
                endTime.set(Calendar.MONTH, newMonth-1);*/


                //MyCalendar myCalendar = new MyCalendar(d, newYear, newMonth);
                //Calendar startTime = new GregorianCalendar(2017, 8, 29, 9, 0);
                //Calendar endTime = new GregorianCalendar(2017, 8, 29, 10, 15);




                //Toast.makeText(getBaseContext(), calendar.get(Calendar.YEAR) + " - "+ calendar.get(Calendar.MONTH) + "-"+calendar.get(Calendar.DAY_OF_MONTH) + "  "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE), Toast.LENGTH_LONG).show();
                //WeekViewEvent event = new WeekViewEvent(1, "Hello", startTime, endTime);

                List<WeekViewEvent> events = new ArrayList<>();
                //events.add(event);

                return events;


            }
        });

        weekView.setEventLongPressListener(new WeekView.EventLongPressListener() {
            @Override
            public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
                Toast.makeText(getBaseContext(), event.getName(), Toast.LENGTH_LONG).show();

            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(appointmentRequest);
    }
}
