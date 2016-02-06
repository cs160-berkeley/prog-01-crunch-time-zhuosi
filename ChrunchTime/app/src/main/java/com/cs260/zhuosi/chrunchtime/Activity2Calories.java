package com.cs260.zhuosi.chrunchtime;

/**
 * Created by Zhuosi on 2/4/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Activity2Calories extends Activity{

    private static final String[] types = {"pushup", "situp", "jumping_jacks", "jogging", "squats","pullup",
            "leg_lift", "plank", "cycling", "walking", "swimming","stair_climbing"
    };
    private static final HashMap<String, String> units = new HashMap<String, String>(){{
        put("pushup","Reps"); put("situp","Reps"); put("jumping_jacks","Minutes"); put("jogging","Minutes");
        put("squats","Reps"); put("pullup","Reps"); put("leg_lift","Minutes"); put("plank","Minutes");
        put("cycling","Minutes"); put("walking","Minutes"); put("swimming","Minutes"); put("stair_climbing","Minutes");
    }};
    private static final HashMap<String, Integer> calorieBurns = new HashMap<String, Integer>(){{
        put("pushup",350); put("situp",200); put("jumping_jacks",10); put("jogging",12);
        put("squats",225); put("pullup",100); put("leg_lift",25); put("plank",25);
        put("cycling",12); put("walking",20); put("swimming",13); put("stair_climbing",15);
    }};
    private TextView spinnerText, unitText, confirmText, exerciseInput,weightInput ;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private Button confirmButton;
    private String curExerciese = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity2calories);
        spinnerText = (TextView) findViewById(R.id.spinnerText);
        unitText = (TextView) findViewById(R.id.unit);
        confirmButton = (Button) findViewById(R.id.confirm_Button);
        confirmText = (TextView) findViewById(R.id.confirmText);
        exerciseInput = (TextView) findViewById(R.id.exerciseInput);
        weightInput = (TextView) findViewById(R.id.weightInput);
        //add spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setVisibility(View.VISIBLE);

        //add button listener
        confirmButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (exerciseInput.getText().toString().equals("") || weightInput.getText().toString().equals("")){
                    StringBuilder sb = new StringBuilder();
                    if(exerciseInput.getText().toString().equals("")){
                        sb.append("Minimum exerce should be 1 Minutes/Reps.\n");
                    }
                    if(weightInput.getText().toString().equals("") || Integer.parseInt(weightInput.getText().toString()) < 50){
                        sb.append("Minimum required weight is 50 lb.\n");
                    }
                    System.out.println(sb.toString());
                    Toast.makeText(getApplicationContext(),sb.toString(), Toast.LENGTH_LONG).show();
                }else {
                    int excerciseTime = Integer.parseInt(exerciseInput.getText().toString());
                    int weight =  Integer.parseInt(weightInput.getText().toString());
                    double calories = 100 * excerciseTime / calorieBurns.get(curExerciese);
                    calories = (calories * weight) / 150;
                    StringBuilder message = new StringBuilder("Congratulation!\nYou have burned ");
                    message.append(((int) calories));
                    message.append(" Calories!\n which is equivalent to :\n");
                    Iterator it = calorieBurns.entrySet().iterator();
                    confirmText.setText(message.toString());

                    String textname = null, imagename = null;
                    TextView exerciseText = null;
                    ImageView exerciseImage = null;

                    int index = 1;
                    while (it.hasNext()) {
                        System.gc();
                        Map.Entry pair = (Map.Entry) it.next();
                        if (pair.getKey().equals(curExerciese)) {
                            continue;
                        }
                        textname = "Text" + index;
                        exerciseText = (TextView) findViewById(getResources().getIdentifier(textname, "id", getPackageName()));
                        exerciseText.setText(String.valueOf((int) calories * (Integer) pair.getValue() / 100) + " " + units.get(pair.getKey()) + "  of  ");

                        imagename = "Image" + index;
                        exerciseImage = (ImageView) findViewById(getResources().getIdentifier(imagename, "id", getPackageName()));
                        exerciseImage.setImageResource(getResources().getIdentifier("mipmap/" + pair.getKey(), null, getPackageName()));
                        index++;
                    }

                }
            }
        });
    }

    public class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            spinnerText.setText("I have down : ");
            curExerciese = types[arg2];
            unitText.setText(units.get(curExerciese));
        }
        public void onNothingSelected(AdapterView<?> arg0){

        }
    }

}
