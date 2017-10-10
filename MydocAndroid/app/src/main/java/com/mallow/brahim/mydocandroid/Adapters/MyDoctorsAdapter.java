package com.mallow.brahim.mydocandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mallow.brahim.mydocandroid.Model.Doctor;
import com.mallow.brahim.mydocandroid.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by brahim on 9/15/17.
 */

public class MyDoctorsAdapter extends ArrayAdapter<Doctor> {

    private Activity activity;

    public MyDoctorsAdapter(Activity activity, Context context, List<Doctor> doctors){
        super(context, 0, doctors);
        this.activity = activity;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.item_my_doctor, null);
            TextView firstName = (TextView)convertView.findViewById(R.id.item_firstName);
            TextView lastName = (TextView)convertView.findViewById(R.id.item_lastName);

            Doctor doctor = getItem(position);

            firstName.setText(doctor.getFirstName());
            lastName.setText(doctor.getLastName());

        }



        return convertView;
    }
}
