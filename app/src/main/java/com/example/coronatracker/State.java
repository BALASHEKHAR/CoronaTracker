package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class State extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter madapter;
   RecyclerView.LayoutManager layoutManager;
    ArrayList<StateData> stateDataList=new ArrayList<>() ;
    ArrayList<DistrictData> districtDataList=new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        recyclerView=findViewById(R.id.recyler);
        parse();


    }
  public void parse()
  {
      stateDataList=somestate.getstate();
      layoutManager =new LinearLayoutManager(State.this);
      madapter=new StateAdapter(State.this,stateDataList);
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setAdapter(madapter);
      recyclerView.scrollToPosition(0);
  }
}