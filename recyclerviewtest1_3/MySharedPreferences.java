package com.example.recyclerviewtest1_3;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySharedPreferences {

    private SharedPreferences prefs;
    private String key;

    public MySharedPreferences(SharedPreferences prefs, String key) {
        this.prefs = prefs;
        this.key = key;
    }

    public void saveBreakdownData(ArrayList<Person> breakdownDataArrayList) {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(breakdownDataArrayList);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<Person> readBreakdownData() {
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<ArrayList<Person>>() {
        }.getType();
        if("".equals(json)){
            return null;
        }
        return gson.fromJson(json, type);
    }

    public void clearBreakdownData(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }


}
