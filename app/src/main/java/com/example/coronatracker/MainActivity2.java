package com.example.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
int ss=0,tt=0;
TextView dee;
TextView state;
TextView district,helpline;
TextView et,ea,er,ec,ed;
Button mycountry,global;
TextView total,deaths,active,recovered,confirmed;
    ArrayList<StateData> stateDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
        ss=i.getIntExtra("s",0);
        tt=i.getIntExtra("t",0);
        stateDataArrayList=somestate.getstate();

        state=findViewById(R.id.textView8);
        district=findViewById(R.id.textView9);
        helpline=findViewById(R.id.textView10);
        state.setText(stateDataArrayList.get(ss).getState());
        district.setText(stateDataArrayList.get(ss).getListdistrictData().get(tt).getDistrictname());
        mycountry=findViewById(R.id.  button3);
        global=findViewById(R.id.button4);
        mycountry.setOnClickListener(this);
        global.setOnClickListener(this);
        state.setOnClickListener(this);
        district.setOnClickListener(this);
        helpline.setOnClickListener(this);

        total=findViewById(R.id.textView12);
        deaths=findViewById(R.id.textView14);
        active=findViewById(R.id.textView18);
        recovered=findViewById(R.id.textView20);
        confirmed=findViewById(R.id.textView16);


        //extras
        et=findViewById(R.id.et);
        ea=findViewById(R.id.ea);
        er=findViewById(R.id.er);
        ed=findViewById(R.id.ed);
        ec=findViewById(R.id.ec);
        ImageView fin=findViewById(R.id.imageView);
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button3:
                setbac(mycountry);
                int ta=0,tr=0,td=0,tc=0;
                for(StateData stateData:stateDataArrayList)
                {
                    ta+=stateData.getActive();
                    tr+=stateData.getRecovered();
                    td+=stateData.getDeceased();
                    tc+=stateData.getConfirmed();
                }
              int tot = ta + tr + td + tc;
                total.setText(String.valueOf(tot));
                active.setText(String.valueOf(ta));
                deaths.setText(String.valueOf(td));
                recovered.setText(String.valueOf(tr));
                confirmed.setText(String.valueOf(tc));

                et.setText(String.valueOf(tot));
                if(tot==0)
                    tot=1;
                ea.setText(String.valueOf(Math.round((float)ta/tot)));
                ed.setText(String.valueOf(Math.round((float)td/tot)));
                ec.setText(String.valueOf(Math.round((float)tc/tot)));
                er.setText(String.valueOf(Math.round((float)tr/tot)));


                break;
            case R.id.button4:
                setbac(global);
                int gta=-1,gtr=-1,gtd=-1,gtc=-1;
                int gog=gta+gtr+gtd+gtc;
                total.setText(String.valueOf(gog));
                active.setText(String.valueOf(gta));
                deaths.setText(String.valueOf(gtd));
                recovered.setText(String.valueOf(gtr));
                confirmed.setText(String.valueOf(gtc));

                et.setText(String.valueOf(gog));
                if(gog==0)
                    gog=1;
                ea.setText(String.valueOf(Math.round((float)gta/gog)));
                ed.setText(String.valueOf(Math.round((float)gtd/gog)));
                ec.setText(String.valueOf(Math.round((float)gtc/gog)));
                er.setText(String.valueOf(Math.round((float)gtr/gog)));
                break;

            case R.id.textView9:
                setbacb(district);


                int a,b,c,d;
                a=stateDataArrayList.get(ss).getListdistrictData().get(tt).getActive();
                       b= stateDataArrayList.get(ss).getListdistrictData().get(tt).getConfirmed();
                       c= stateDataArrayList.get(ss).getListdistrictData().get(tt).getRecovered();
                      d=  stateDataArrayList.get(ss).getListdistrictData().get(tt).getDeceased();
                      int y=a+b+c+d;


                total.setText(String.valueOf(y));
                active.setText(String.valueOf(a));
                deaths.setText(String.valueOf(d));
                recovered.setText(String.valueOf(c));
                confirmed.setText(String.valueOf(b));



                et.setText(String.valueOf(y));
                if(y==0)
                    y=1;
                ea.setText(String.valueOf(Math.round((float)a/y)));
                ed.setText(String.valueOf(Math.round((float)d/y)));
                ec.setText(String.valueOf(Math.round((float)b/y)));
                er.setText(String.valueOf(Math.round((float)c/y)));
                break;


            case R.id.textView8:
                setbacb(state);

                int aa=stateDataArrayList.get(ss).getActive(),
                        bb=stateDataArrayList.get(ss).getDeceased(),
                       cc= stateDataArrayList.get(ss).getRecovered(),
                        dd=stateDataArrayList.get(ss).getConfirmed();
                int yy=aa+bb+cc+dd;
                total.setText(String.valueOf(yy));
                active.setText(String.valueOf(aa));
                deaths.setText(String.valueOf(bb));
                recovered.setText(String.valueOf(cc));
                confirmed.setText(String.valueOf(dd));


                et.setText(String.valueOf(yy));
                if(yy==0)
                    yy=1;
                ea.setText(String.valueOf(Math.round((float)aa/yy)));
                ed.setText(String.valueOf(Math.round((float)bb/yy)));
                ec.setText(String.valueOf(Math.round((float)dd/yy)));
                er.setText(String.valueOf(Math.round((float)cc/yy)));
                break;
            case R.id.textView10:
                setbacb(helpline);


        }
    }


    private void setbacb(TextView sstate) {
        setAll();
        sstate.setBackground(ContextCompat.getDrawable(this,R.drawable.spinner_bg));
        sstate.setTextColor(Color.BLACK);
    }

    private void setAll() {
        mycountry.setBackground(ContextCompat.getDrawable(this,R.drawable.statistic_button));
        mycountry.setTextColor(Color.WHITE);
        global.setBackground(ContextCompat.getDrawable(this,R.drawable.statistic_button));
        global.setTextColor(Color.WHITE);
        state.setBackground(ContextCompat.getDrawable(this,R.drawable.statistic_button));
        state.setTextColor(Color.WHITE);
        district.setBackground(ContextCompat.getDrawable(this,R.drawable.statistic_button));
        district.setTextColor(Color.WHITE);
        helpline.setBackground(ContextCompat.getDrawable(this,R.drawable.statistic_button));
        helpline.setTextColor(Color.WHITE);
    }

    private void setbac(Button mymcountry) {
        setAll();
        mymcountry.setBackground(ContextCompat.getDrawable(this,R.drawable.spinner_bg));
        mymcountry.setTextColor(Color.BLACK);

    }
 /*   public void statewise(View v)
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
    }*/
}