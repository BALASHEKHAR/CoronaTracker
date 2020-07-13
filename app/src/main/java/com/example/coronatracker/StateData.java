package com.example.coronatracker;

import java.io.Serializable;
import java.util.ArrayList;

class StateData {
    String state;
    int active;
    int confirmed;
    int recovered;
    int Deceased;
    ArrayList<DistrictData>  listdistrictData;

    public StateData(String state, int active, int confirmed, int recovered, int deceased,ArrayList<DistrictData> listdistrictData) {
        this.state = state;
        this.active = active;
        this.confirmed = confirmed;
        this.recovered = recovered;
        Deceased = deceased;
        this.listdistrictData=listdistrictData;
    }

    public String getState() {
        return state;
    }

    public int getActive() {
        return active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeceased() {
        return Deceased;
    }

    public ArrayList<DistrictData> getListdistrictData() {
        return listdistrictData;
    }


    @Override
    public String toString() {
        return state;
    }
}
