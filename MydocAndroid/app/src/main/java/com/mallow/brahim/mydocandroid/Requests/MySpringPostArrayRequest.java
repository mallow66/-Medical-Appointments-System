package com.mallow.brahim.mydocandroid.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

/**
 * Created by brahim on 9/26/17.
 */

public class MySpringPostArrayRequest extends JsonRequest<JSONArray> {

    public MySpringPostArrayRequest(String url, String requestBody, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(Method.POST, "http://192.168.1.20:8080"+url, requestBody, listener, errorListener);
    }



    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
}
