package com.fishtail.hstuadmissionhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;


public class RoomFinder extends AppCompatActivity {

    EditText edt_roll;
    TextView txt_room_info;
    Button btn_submit_roll;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_finder);



        edt_roll = findViewById(R.id.edt_roll);
        txt_room_info = findViewById(R.id.txt_room_info);
        btn_submit_roll = findViewById(R.id.btn_submit_roll);

        // set the visibilty of the textview to GONE
        txt_room_info.setVisibility(View.GONE);



        btn_submit_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll = edt_roll.getText().toString().trim();

                if (!roll.isEmpty()) {
                    fetchRoomData(roll);
                } else {
                    Toast.makeText(RoomFinder.this, "Please enter a roll number", Toast.LENGTH_SHORT).show();
                }
            }
        });




        btn_submit_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the roll number from the edit text
                String roll = edt_roll.getText().toString();

                // make the textview visible
                txt_room_info.setVisibility(View.VISIBLE);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchRoomData(String roll) {
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("room_assignments").document(roll);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String roomNumber = document.getString("room_number");
                    txt_room_info.setText("Assigned Room: " + roomNumber);
                    txt_room_info.setVisibility(View.VISIBLE);
                } else {
                    txt_room_info.setText("No room assigned for this roll.");
                    txt_room_info.setVisibility(View.VISIBLE);
                }
            } else {
                txt_room_info.setText("Error fetching data.");
                txt_room_info.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(RoomFinder.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

}