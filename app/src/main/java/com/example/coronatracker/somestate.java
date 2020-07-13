package com.example.coronatracker;

import android.app.Application;

import java.util.ArrayList;

class somestate extends Application {
    static ArrayList<StateData> ar;
    public static void setstate(ArrayList<StateData> sd)
    {
        ar=sd;
    }
    public static ArrayList<StateData> getstate()
    {
        return ar;
    }



}
