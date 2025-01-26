package com.fishtail.hstuadmissionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //ImageButton btn_room;
    //ImageButton btn_transport;
    //ImageButton btn_important;
    //Button btn_notice;

    CardView roomCard;
    CardView transportCard;
    CardView importantDatesCard;
    CardView noticeCard;
    CardView resultCard;
    CardView moreCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);




      /*  // Initialize the buttons
        btn_room = findViewById(R.id.imgbtn_room);
        btn_transport = findViewById(R.id.imgbtn_transport);
        btn_important = findViewById(R.id.imgbtn_important_dates);
        //btn_notice = findViewById(R.id.btn_Notice);

        // Set click listeners for the buttons
        btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomFinder.class);
                startActivity(intent);
            }
        });

        btn_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransportActivity.class);
                startActivity(intent);
            }
        });

        btn_important.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImportantDatesActivity.class);
                startActivity(intent);
            }
        });

        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeBoardActivity.class);
                startActivity(intent);
            }
        }); */

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}