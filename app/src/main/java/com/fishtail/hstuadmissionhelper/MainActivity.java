package com.fishtail.hstuadmissionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CardView roomCard;
    CardView transportCard;
    CardView importantDatesCard;
    CardView noticeCard;
    CardView resultCard;
    CardView moreCard;

    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //forced light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        roomCard = findViewById(R.id.roomCard);
        transportCard = findViewById(R.id.transportCard);
        importantDatesCard = findViewById(R.id.importantDatesCard);
        noticeCard = findViewById(R.id.noticeCard);
        resultCard = findViewById(R.id.resultCard);
        moreCard = findViewById(R.id.moreCard);

        roomCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomFinder.class);
                startActivity(intent);
            }
        });

        transportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TransportActivity.class);
                startActivity(intent);
            }
        });

        importantDatesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImportantDatesActivity.class);
                startActivity(intent);
            }
        });

        noticeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoticeBoardActivity.class);
                startActivity(intent);
            }
        });

        resultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        moreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, More.class);
                startActivity(intent);
            }
        });


        // Image Slider
        imageSlider=findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.hstu_main_gate, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hstu_img2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hstu_img3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hstu_img4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hstu_img5, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}