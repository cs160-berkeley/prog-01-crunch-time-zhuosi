package com.cs260.zhuosi.chrunchtime;

//import android.support.v7.app.AppCompatActivity;
import android.app.TabActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.content.res.Resources;


public class MainActivity extends TabActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, Activity2Calories.class);
        spec = tabHost.newTabSpec("A2C");
        spec.setContent(intent);
        spec.setIndicator("", resources.getDrawable(R.drawable.icon_a2ctab_config));
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Calories2Activity.class);
        spec = tabHost.newTabSpec("C2A");
        spec.setContent(intent);
        spec.setIndicator("", resources.getDrawable(R.drawable.icon_c2atab_config));
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}