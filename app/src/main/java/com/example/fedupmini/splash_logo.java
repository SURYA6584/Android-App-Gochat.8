package com.example.fedupmini;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash_logo extends AppCompatActivity {


    Animation topAnim,botAnim;
    ImageView image1,image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_logo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_logo);
        botAnim= AnimationUtils.loadAnimation(this,R.anim.bot_logo);

        image1 =findViewById(R.id.IM1T65);
        image2 =findViewById(R.id.IM2T65);

        image1.setAnimation(topAnim);
        image2.setAnimation(botAnim);



        new Handler().postDelayed(() -> {
            // Replace with your next activity class name
            Intent intent = new Intent(splash_logo.this, OTP_send.class);
            startActivity(intent);
            finish(); // Optionally finish this activity
        }, 3500); //




    }
}