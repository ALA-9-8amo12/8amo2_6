package com.example.amazighquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button oefenen;
    LinearLayout linearLayout;
    Animation animationbg;
    Animation amazighanim;
    TextView logotext;
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oefenen = findViewById(R.id.oefenen);

        oefenen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent1 = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent1);

            }

        });

        animationbg = AnimationUtils.loadAnimation(this, R.anim.opacity_bg_anim);
        amazighanim = AnimationUtils.loadAnimation(this, R.anim.text_amazigh_anim);

        linearLayout = findViewById(R.id.backgroundblue);
        logotext = findViewById(R.id.logotext);
        logo = findViewById(R.id.logo);

        setAnimation();
    }

    public void setAnimation(){
        linearLayout.setAnimation(animationbg);
        logotext.setAnimation(amazighanim);
        logo.setAnimation(animationbg);

    }

}
