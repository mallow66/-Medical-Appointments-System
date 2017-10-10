package com.mallow.brahim.mydocandroid.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.mallow.brahim.mydocandroid.Model.Patient;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brahim on 8/15/17.
 */

public class MySpringPostRequest extends JsonObjectRequest{



    public MySpringPostRequest(String url, Map<String,Object> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super("http://192.168.1.20:8080"+url, new JSONObject(params), listener, errorListener);
    }








}
