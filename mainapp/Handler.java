package com.rana.adarsh.imagetotext.mainapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Handler {

    public static Handler mInstancr;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Handler(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized Handler getInstance(Context context)
    {
        if(mInstancr == null)
        {
            mInstancr = new Handler(context);
        }
        return mInstancr;
    }

    public  <T>void addToRequestQue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
