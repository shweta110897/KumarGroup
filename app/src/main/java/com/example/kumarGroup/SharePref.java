package com.example.kumarGroup;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharePref {

    private static final String TAG = SharePref.class.getSimpleName();

    public final String FO_ID = "FO_ID";
    public final String DO_ID = "DO_ID";
    public final String STAFF_ID = "STAFF_ID";
    public final String LOGIN_USER = "LOGIN_USER";
    public final String PHONE_NUMBER = "PHONE_NUMBER";
    public final String CALL_ID = "CALL_ID";
    public final String task_type = "task_type";


    public final String NOTI_TYPE = "NOTI_TYPE";



    public final String FOMEDIA_MODEL = "FOMEDIA_MODEL";
    public final String DELIVERY_TYPE = "DELIVERY_TYPE";
    public final String TODAY_DATE = "TODAY_DATE";

    public final String SALES_MANAGER = "Sales Manager";
    public final String WORKSHOP_MANAER = "workshop manager";
    public final String SALES_PROMOTER = "sales promoter";

    private SharedPreferences sharedpreference;

    public SharePref(Context context) {
        Log.d("TAG", "SharePref: context123"+context);
        sharedpreference = context.getSharedPreferences("sonalika", Context.MODE_PRIVATE);
        //Log.e(TAG,"SharePref Constructor");
    }

    public String getSharedPref(String key) {
        Log.e("getSharedPref","key:"+key+" - "+"value:"+sharedpreference.getString(key, "NULL"));
        return sharedpreference.getString(key, "NULL");
    }

    public void putSharedPref(String key, String value) {
        Log.e("putSharedPref","key:"+key+" - "+"value:"+value);
        SharedPreferences.Editor edit = sharedpreference.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public void ClearPref() {
        SharedPreferences.Editor edit = sharedpreference.edit();
        edit.clear();
        edit.apply();
    }
}
