package com.example.kumarGroup;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class UserPermission {
    private static final int READ_CALL_LOG = 103;
    private static final int CALL_PHONE = 102;
    private static final int READ_PHONE_STATE = 101;
    private static final int READ_PHONE_NUMBERS = 109;
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CONSTANT = 102;
    private Activity activity;

    public UserPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean checkReadPhoneStatePermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestReadPhoneStatePermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE);
        }
    }

    //////////////////

    public boolean checkWriteExternalPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestWriteExternalPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_CONSTANT);
        }
    }

    ////////////////

    public boolean checkReadExternalPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestReadExternalPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }

    //////////////////

    public boolean checkCallPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestCallPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE);
        }
    }

    //////////////////

    public boolean checkCallLogPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestCallLogPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG);
        }
    }

    public boolean checkCallLogWritePermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    public void requestCallLogWritePermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.WRITE_CALL_LOG}, READ_CALL_LOG);
        }
    }
}
