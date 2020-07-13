package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
int ss=0,tt=0;
TextView ac,re,co,dee;
TextView sa,sr,sd,sc,state;
TextView da,dr,dd,dc,district;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
        ArrayList<StateData> stateDataArrayList=somestate.getstate();
        Log.d("sss",somestate.getstate().size()+"");
        int ta=0,tr=0,td=0,tc=0;
        for(StateData stateData:stateDataArrayList)
        {
            ta+=stateData.getActive();
            tr+=stateData.getRecovered();
            td+=stateData.getDeceased();
            tc+=stateData.getConfirmed();

        }

            ss=i.getIntExtra("s",0);
            tt=i.getIntExtra("t",0);


        state=findViewById(R.id.statename);
        district=findViewById(R.id.districtname);
        state.setText(stateDataArrayList.get(ss).getState());
       district.setText(stateDataArrayList.get(ss).getListdistrictData().get(tt).getDistrictname());


        sa=findViewById(R.id.sa);
        sr=findViewById(R.id.sr);
        sd=findViewById(R.id.sd);
        sc=findViewById(R.id.sc);
        sa.setText(String.valueOf(stateDataArrayList.get(ss).getActive()));
        sr.setText(String.valueOf(stateDataArrayList.get(ss).getRecovered()));
        sd.setText(String.valueOf(stateDataArrayList.get(ss).getDeceased()));
        sc.setText(String.valueOf(stateDataArrayList.get(ss).getConfirmed()));










        da=findViewById(R.id.da);
       dr=findViewById(R.id.dr);
      dd=findViewById(R.id.dd);
      dc=findViewById(R.id.dc);
      da.setText(String.valueOf(stateDataArrayList.get(ss).getListdistrictData().get(tt).getActive()));
        dr.setText(String.valueOf(stateDataArrayList.get(ss).getListdistrictData().get(tt).getRecovered()));
        dd.setText(String.valueOf(stateDataArrayList.get(ss).getListdistrictData().get(tt).getDeceased()));
        dc.setText(String.valueOf(stateDataArrayList.get(ss).getListdistrictData().get(tt).getConfirmed()));



        ac=findViewById(R.id.tact);
        re=findViewById(R.id.trec);
        co=findViewById(R.id.tcnf);
        dee=findViewById(R.id.tdea);
        ac.setText(String.valueOf(ta));
        re.setText(String.valueOf(tr));
        dee.setText(String.valueOf(td));
        co.setText(String.valueOf(tc));



    }
    public void statewise(View v)
    {
        Intent i=new Intent(this,State.class);
        startActivity(i);
    }
    public void districtwise(View v)
    {
        Intent i=new Intent(this,District.class);
        i.putExtra("pos",ss);
        i.putExtra("sop",tt);
        startActivity(i);
    }
}