package com.example.kumarGroup.OverTime;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStorage {
    private Context ctx;
    private SharedPreferences pref;
    private SharedPreferences.Editor writer;
    private static final String FILENAME = "easylearn";
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int LONG = 3;
    public static final int STRING = 4;
    public static final int BOOLEAN = 5;
    public DataStorage(Context ctx){
        this.ctx =  ctx;
        pref = this.ctx.getSharedPreferences(FILENAME,Context.MODE_PRIVATE);
        writer = pref.edit();
    }
    public void write(String key,int value)
    {
        writer.putInt(key,value);
        writer.commit();
    }
    public void write(String key,String value){
        writer.putString(key,value);
        writer.commit();
    }
    public void write(String key,boolean value){
        writer.putBoolean(key,value);
        writer.commit();

    }
    public void write(String key,long value){
        writer.putLong(key,value);
    }
    public void write(String key,float value){
        writer.putFloat(key,value);
    }

    public Object read(String key,int DataType)
    {
        Object temp = null;
        if(DataType==INTEGER)
            temp = pref.getInt(key,0);
        else if(DataType==FLOAT)
            temp = pref.getFloat(key,0.0f);
        else if(DataType==LONG)
            temp = pref.getLong(key,0);
        else if(DataType==STRING)
            temp = pref.getString(key,"");
        else if(DataType==BOOLEAN)
            temp = pref.getBoolean(key,false);

        return temp;
    }
    public void delete(String key){
        writer.remove(key); //delete specific key

    }
    public void deleteall(){
        writer.clear(); //delete all keys
    }

}
