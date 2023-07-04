package com.example.kumarGroup;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FCMMessages {

    public void sendMessageSingle(final String recipient, final String title, final String body, final Map<String, String> dataMap)
    {
        Map<String, Object> notificationMap = new HashMap<>();
        notificationMap.put("body", body);
        notificationMap.put("title", title);

        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("notification", notificationMap);
        rootMap.put("to", recipient);
        if (dataMap != null)
            rootMap.put("data", dataMap);


        Log.d("rootMap",rootMap.toString());
        new SendFCM().setFcm(rootMap).execute();
    }


    public void sendMessageMulti( final JSONArray recipients, final String title, final String body, final Map<String, String> dataMap) {

        Map<String, Object> notificationMap = new HashMap<>();
        notificationMap.put("body", body);
        notificationMap.put("title", title);
        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("notification", notificationMap);
        rootMap.put("registration_ids", recipients);
        if (dataMap != null)
            rootMap.put("data", dataMap);

        new SendFCM().setFcm(rootMap).execute();
    }

    @SuppressLint("StaticFieldLeak")
    class SendFCM extends AsyncTask<String, String, String> {

        private String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
        private Map<String, Object> fcm;

        SendFCM setFcm(Map<String, Object> fcm) {
            this.fcm = fcm;
            return this;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, new JSONObject(fcm).toString());
                Request request = new Request.Builder()
                        .url(FCM_MESSAGE_URL)
                        .post(body)
                        .addHeader("Authorization","key=" + Utils.SERVER_KEY)
                        .build();
                Log.d("request",request.toString());
                Response response = new OkHttpClient().newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject resultJson = new JSONObject(result);
                int success, failure;
                success = resultJson.getInt("success");
                failure = resultJson.getInt("failure");
                Log.d("success",""+success);
                Log.d("failure",""+failure);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
