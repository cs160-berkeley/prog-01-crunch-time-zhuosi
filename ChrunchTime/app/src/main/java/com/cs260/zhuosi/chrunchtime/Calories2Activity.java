package com.cs260.zhuosi.chrunchtime;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import java.io.File;
import android.os.Environment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Zhuosi on 2/4/16.
 */
public class Calories2Activity extends Activity {
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
    private TextView inputText,weightInput;
    private Button confirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.calories2activity);
        confirmButton = (Button) findViewById(R.id.confirm_Button);
        inputText = (TextView) findViewById(R.id.input);
        weightInput = (TextView) findViewById(R.id.weightInput);

        //add button listener
        confirmButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if ( weightInput.getText().toString().equals("")){
                    StringBuilder sb = new StringBuilder();
                    if(weightInput.getText().toString().equals("") || Integer.parseInt(weightInput.getText().toString()) < 50){
                        sb.append("Minimum required weight is 50 lb.\n");
                    }
                    System.out.println(sb.toString());
                    Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
                }else {

                    int weight = Integer.parseInt(weightInput.getText().toString());
                    double calories = Integer.parseInt(inputText.getText().toString()) * 150 / weight;
                    Iterator it = calorieBurns.entrySet().iterator();

                    String textname = null, imagename = null;
                    TextView exerciseText = null;
                    ImageView exerciseImage = null;
                    for(int index = 1; index < 13; index++) {
                        System.gc();
                        Map.Entry pair = (Map.Entry) it.next();
                        textname = "Text" + index;
                        exerciseText = (TextView) findViewById(getResources().getIdentifier(textname, "id", getPackageName()));
                        exerciseText.setText(String.valueOf((int) calories * (Integer) pair.getValue() / 100) + " " + units.get(pair.getKey()) + "  of  ");

                        imagename = "Image" + index;
                        exerciseImage = (ImageView) findViewById(getResources().getIdentifier(imagename, "id", getPackageName()));
                        exerciseImage.setImageResource(getResources().getIdentifier("mipmap/" + pair.getKey(), null, getPackageName()));
                    }
                }
            }
        });
    }
}
