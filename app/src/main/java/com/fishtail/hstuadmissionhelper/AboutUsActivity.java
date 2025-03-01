package com.fishtail.hstuadmissionhelper;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us);

        String devList[]={"Jannatul Ferthaous\nID: 2102022", "Eusha Sarwar Utal\nID: 2102045", "Zikrul Bari Tomal\nID: 2102056"};
        int devImages[]={R.drawable.dev_jannat, R.drawable.dev_utal, R.drawable.dev_tomal};


        ListView listView;
        listView=(ListView) findViewById(R.id.custom_listview);
        CustomBaseAdapter customBaseAdapter=new CustomBaseAdapter(this, devList, devImages);
        listView.setAdapter(customBaseAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}