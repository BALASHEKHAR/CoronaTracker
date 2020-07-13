package com.example.coronatracker;

class DistrictData {
    String districtname;
    String notes;
    int active;
    int confirmed;
    int recovered;
    int Deceased;

    public DistrictData(String districtname, String notes, int active, int confirmed, int recovered, int deceased) {
        this.districtname = districtname;
        this.notes = notes;
        this.active = active;
        this.confirmed = confirmed;
        this.recovered = recovered;
        Deceased = deceased;
    }

    public String getDistrictname() {
        return districtname;
    }

    public String getNotes() {
        return notes;
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

    @Override
    public String toString() {
        return districtname ;

    }
}
