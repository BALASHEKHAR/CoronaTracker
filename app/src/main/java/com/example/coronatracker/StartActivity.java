package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class StartActivity extends AppCompatActivity {
    Spinner spinner;
    Spinner spinner2;
    Button btn;
    ArrayList<StateData> stateDataList=new ArrayList<>();
    ArrayList<DistrictData> gdistDataList=new ArrayList<>();
    ProgressBar progressBar;
    LinearLayout linearLayout;
    ScrollView scrollView;
    int ss=0,tt=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        progressBar=findViewById(R.id.pg);
        progressBar.setVisibility(View.VISIBLE);
        spinner=findViewById(R.id.spinn);
        spinner2=findViewById(R.id.spin2);
        linearLayout=findViewById(R.id.rlinear);
        scrollView=findViewById(R.id.rscroll);

        spinner2.setEnabled(false);
        btn=findViewById(R.id.click);

        parse();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StartActivity.this,MainActivity2.class);
                i.putExtra("s",ss);
                i.putExtra("t",tt);
                startActivity(i);
            }
        });








    }




    public  void  parse()

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



                for(int i = 0; i<response.length(); i++)
                {

                    try {
                        JSONObject jso = (JSONObject) response.get(i);
                        //
                        String state = jso.getString("state");
                        JSONArray jsonArray = jso.getJSONArray("districtData");

                        int act = 0, con = 0, rec = 0, dec = 0;
                        int a,c,r,d;
                        ArrayList<DistrictData> districtDataList=new ArrayList<>();
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject Jsoo = (JSONObject) jsonArray.get(j);
                            DistrictData districtData;
                            a=Jsoo.getInt("active");
                            c=Jsoo.getInt("confirmed");
                            r= Jsoo.getInt("recovered");
                            d= Jsoo.getInt("deceased");
                            act = act + a;
                            con = con + c;
                            rec = rec +r;
                            dec = dec +d;
                            districtData=new DistrictData(Jsoo.getString("district"),Jsoo.getString("notes"),
                                    a,c,r,d);
                            gdistDataList.add(districtData);
                            districtDataList.add(districtData);
                     //      Log.d("ggg",""+districtData);

                        }
                        StateData sdd = new StateData(state, act, con, rec, dec,districtDataList);
                   //  Log.d("ggg",""+sdd);
                        //    stateDataList.add(sdd);
                        stateDataList.add(sdd);
                        linearLayout.setVisibility(View.GONE);
                        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

                    } catch (JSONException e) {

                        e.printStackTrace();
                        Log.d("ggg",String.valueOf(stateDataList.size()));
                    }
                }
                somestate.setstate(stateDataList);
                ArrayAdapter<StateData> adapter=new ArrayAdapter<>(StartActivity.this,android.R.layout.simple_spinner_item,stateDataList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //    adapter=new SpinnerAdapter(StartActivity.this,stateDataList);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        StateData dd= (StateData) parent.getItemAtPosition(position);
                       final String s=dd.getState();
                       ss=position;
                        Toast.makeText(StartActivity.this, s, Toast.LENGTH_SHORT).show();
                        spinner2.setEnabled(true);
                        ArrayList<DistrictData> arrr=dd.getListdistrictData();
                        ArrayAdapter<DistrictData> adapter2=new ArrayAdapter<>(StartActivity.this,android.R.layout.simple_spinner_item,arrr);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(adapter2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                DistrictData ss=(DistrictData)parent.getItemAtPosition(position);
                                String d=ss.getDistrictname();
                                tt=position;
                                Toast.makeText(StartActivity.this,s+" "+d,Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

             /*   layoutManager =new LinearLayoutManager(State.this);
                madapter=new StateAdapter(State.this,stateDataList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(madapter);
                recyclerView.scrollToPosition(0);*/
                // recyclerView.getLayoutManager().scrollToPosition(0);
                //Log.d("ggg",String.valueOf(stateDataList.size()));
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}