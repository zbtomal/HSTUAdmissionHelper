package com.fishtail.hstuadmissionhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class More extends AppCompatActivity {

    Button btn_about_hstu;
    Button btn_about_us;
    Button btn_faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        // Initialize buttons
        btn_about_hstu=findViewById(R.id.btn_about_HSTU);
        btn_about_us=findViewById(R.id.btn_about_us);
        btn_faq=findViewById(R.id.btn_FAQ);

        btn_about_hstu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(More.this, AboutHSTUActivity.class);
                startActivity(intent);
            }
        });

        btn_about_us.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(More.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        btn_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(More.this, FAQActivity.class);
                startActivity(intent);
            }
        });



        // Adjust for system bars (e.g., status and navigation bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
