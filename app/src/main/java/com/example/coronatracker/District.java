package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class District extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DistrictData> districtDataList=new ArrayList<>();
    TextView state,sc,sa,sr,sd;
    int stat=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        recyclerView=findViewById(R.id.distrecyler);
        state=findViewById(R.id.dstatename);
        sc=findViewById(R.id.dsc);
        sa=findViewById(R.id.dsa);
        sr=findViewById(R.id.dsr);
        sd=findViewById(R.id.dsd);
        init();
        state.setText(somestate.getstate().get(stat).getState());
        sc.setText(String.valueOf(somestate.getstate().get(stat).getConfirmed()));
        sa.setText(String.valueOf(somestate.getstate().get(stat).getActive()));
        sr.setText(String.valueOf(somestate.getstate().get(stat).getRecovered()));
        sd.setText(String.valueOf(somestate.getstate().get(stat).getDeceased()));

    }
    public  void init()
    {
        Intent i=getIntent();
        stat=i.getIntExtra("pos",0);
        pasre(stat);



    }
    public void pasre(final int stat)
    {

            RequestQueue queue = Volley.newRequestQueue(this);
            String url="https://api.covid19india.org/v2/state_district_wise.json";
            JsonArrayRequest jsonObjectRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

          /*      try {
                    JSONObject jjj= (JSONObject) response.get(0);
                    JSONArray jaaa=jjj.getJSONArray("districtData");
                    Log.d("ggg",""+jaaa.length());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                    try {
                        JSONObject jsss= (JSONObject) response.get(stat);
                        JSONArray jaaa=jsss.getJSONArray("districtData");
                        for(int i=0;i<jaaa.length();i++)
                        {
                            JSONObject ggga= (JSONObject) jaaa.get(i);
                            DistrictData ds=new DistrictData(ggga.getString("district"),
                                    ggga.getString("notes"),
                                    ggga.getInt("active"),
                                    ggga.getInt("confirmed"),
                                    ggga.getInt("recovered"),
                                    ggga.getInt("deceased"));
                            districtDataList.add(ds);
                         //   Log.d("ggg",""+ggga);

                        }
                        layoutManager=new LinearLayoutManager(District.this);
                        adapter=new DistrictAdapter(District.this,districtDataList);
                        //)Adapter(StartActivity.this,stateDataList);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        recyclerView.scrollBy(0,0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                  /*  for(int i=0;i<response.length();i++)
                    {

                        try {
                            JSONObject jso = (JSONObject) response.get(i);
                            //
                            String state = jso.getString("state");

                            if (stat == state) {

                            JSONArray jsonArray = jso.getJSONArray("districtData");


                            int a, c, r, d;
                            for (int j = 0; j < jsonArray.length(); j++) {
                                JSONObject Jsoo = (JSONObject) jsonArray.get(j);
                                DistrictData districtData;
                                a = Jsoo.getInt("active");
                                c = Jsoo.getInt("confirmed");
                                r = Jsoo.getInt("recovered");
                                d = Jsoo.getInt("deceased");
                                districtData = new DistrictData(Jsoo.getString("district"), Jsoo.getString("notes"),
                                        a, c, r, d);
                                districtDataList.add(districtData);
                                //Log.d("ggg",""+districtData);

                            }
                                Log.d("ggg",""+districtDataList.size());
                                for (int j=0;j<districtDataList.size();i++)
                                    Log.d("ggg",districtDataList.get(i).districtname);

                                //Log.d("ggg",String.valueOf(stateDataList.size()));
                        }


                        } catch (JSONException e) {

                            e.printStackTrace();

                        }
                    }*/

                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(jsonObjectRequest);


    }
}