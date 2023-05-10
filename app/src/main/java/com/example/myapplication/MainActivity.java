package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView diceImage1;
    ImageView diceImage2;
    TextView txt1;
    Button btn1;
    Button btn2;
    private SensorManager sm;
    private MediaParser mp;
    private float acelVal;
    private float acelLast;
    private float shake;
    public int z1 = 0;
    public int z2 = 0;
    public int zahl;
    Random r = new Random();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 00f;

        diceImage1= findViewById(R.id.dice_image1);
        diceImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1)
            { rotateDice1(); rotateDice2();}});

        diceImage2 = findViewById(R.id.dice_image2);
        diceImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {rotateDice2(); rotateDice1(); wurf();}});

        btn1 = (Button) findViewById(R.id.btnLuegen);
        btn1.setOnClickListener(this);

    }
    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x =sensorEvent.values[0];
            float y =sensorEvent.values[1];
            float z =sensorEvent.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double)(x*x+y*y+z*z));
            float delta = acelVal - acelLast;
            shake = shake*0.9f + delta;

            if(shake > 5)
            {
                rotateDice2(); rotateDice1(); wurf();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private void rotateDice1()
    {
        int i = r.nextInt(6)+1;
        z1=i;
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diceImage1.startAnimation(anim1);

        switch(i)
        {
            case 1: diceImage1.setImageResource(R.drawable.dice1); break;
            case 2: diceImage1.setImageResource(R.drawable.dice2); break;
            case 3: diceImage1.setImageResource(R.drawable.dice3); break;
            case 4: diceImage1.setImageResource(R.drawable.dice4); break;
            case 5: diceImage1.setImageResource(R.drawable.dice5); break;
            case 6: diceImage1.setImageResource(R.drawable.dice6); break;
        }
    }

    private void rotateDice2 ()
    {
        int j = r.nextInt(6)+1;
        z2=j;
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        diceImage2.startAnimation(anim2);

        switch(j)
        {
            case 1: diceImage2.setImageResource(R.drawable.dice1); break;
            case 2: diceImage2.setImageResource(R.drawable.dice2); break;
            case 3: diceImage2.setImageResource(R.drawable.dice3); break;
            case 4: diceImage2.setImageResource(R.drawable.dice4); break;
            case 5: diceImage2.setImageResource(R.drawable.dice5); break;
            case 6: diceImage2.setImageResource(R.drawable.dice6); break;
        }

    }
    public void wurf()
    {
            int dice1 = z1;
            int dice2 = z2;
            txt1 = findViewById(R.id.output1);
            int j = r.nextInt(6)+1;
            String str;


            if (dice1==1 && dice2==2 || dice1==2 && dice2==1)
            {
                switch(j)
                {
                   case 1: str = "MÄXXLE!"; txt1.setText(str); break;
                   case 2: str="Der Wahnsinn. Ein MÄXXLE!!"; txt1.setText(str); break;
                   case 3: str="Du Glückspilz! Ein MÄXXLE!"; txt1.setText(str); break;
                   case 4: str="Gib Flosse Genosse. Ein MÄXXLE"; txt1.setText(str); break;
                   case 5: str="mä- mäx- MÄXXLE!";txt1.setText(str); break;
                   case 6: str="Super ein MÄXXLE."; txt1.setText(str);break;
                }
            }

            else if(dice1>dice2)
            {
                zahl=dice1*10 + dice2;

                switch(j)
                {
                    case 1: str = "Grandios! Du hast eine " + zahl +"."; txt1.setText(str); break;
                    case 2: str="Super! Eine " +  zahl + "."; txt1.setText(str); break;
                    case 3: str="Eine " + zahl + ". Hervorragend!"; txt1.setText(str); break;
                    case 4: str="Gib Flosse Genosse. Eine "+ zahl + "."; txt1.setText(str); break;
                    case 5: str="Dat isch ja ne " + zahl + "."; txt1.setText(str);break;
                    case 6: str="Wahnsinn! Du hast eine " + zahl + "."; txt1.setText(str);break;
                }
            }

            else if(dice1<dice2)
            {
                zahl=dice2*10 + dice1;
                switch(j)
                {
                    case 1: str = "Grandios! Du hast eine " + zahl +"."; txt1.setText(str); break;
                    case 2: str="Super! Eine " +  zahl + "."; txt1.setText(str); break;
                    case 3: str="Eine " + zahl + ". Hervorragend!"; txt1.setText(str); break;
                    case 4: str="Gib Flosse Genosse. Eine "+ zahl + "."; txt1.setText(str); break;
                    case 5: str="Dat isch ja ne " + zahl + "."; txt1.setText(str);break;
                    case 6: str="Wahnsinn! Du hast eine " + zahl + "."; txt1.setText(str);break;
                }
            }

            else if (dice1==1 && dice2==2 || dice1==2 && dice2==1)
            {
                switch(j)
                {
                    case 1: str = "MÄXXLE"; txt1.setText(str); break;
                    case 2: str="Der Wahnsinn. Ein MÄXXLE!!"; txt1.setText(str); break;
                    case 3: str="Du Glückspilz! Ein MÄXXLE!"; txt1.setText(str); break;
                    case 4: str="Gib Flosse Genosse. Ein MÄXXLE"; txt1.setText(str); break;
                    case 5: str="mä- mäx- MÄXXLE!";txt1.setText(str); break;
                    case 6: str="Super ein MÄXXLE."; txt1.setText(str);break;
                }
            }

            else
            {
                zahl = dice1*10 + dice2;
                switch(j)
                {
                    case 1: str = "Grandios! Du hast eine " + dice1 + "er Pasch."; txt1.setText(str); break;
                    case 2: str="Super! Ein " + dice1 + "er Pasch."; txt1.setText(str); break;
                    case 3: str="Ein " +  dice1 + "er Pasch "  + ". Hervorragend!"; txt1.setText(str); break;
                    case 4: str="Gib Flosse Genosse. Ein "+  dice1 + "er Pasch."; txt1.setText(str); break;
                    case 5: str="Dat isch ja nh " +  dice1 + "er Pasch.";txt1.setText(str); break;
                    case 6: str="Wahnsinn! Du hast ein " + dice1 + "er Pasch.";txt1.setText(str); break;
                }
            }
    }


    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(this,Luegen.class);
        startActivity(intent1);
    }
}