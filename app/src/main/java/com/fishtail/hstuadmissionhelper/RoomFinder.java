package com.fishtail.hstuadmissionhelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RoomFinder extends AppCompatActivity {

    EditText edt_roll;
    Spinner spinner_unit;
    TextView txt_room_info;
    Button btn_submit_roll;
    FirebaseFirestore db;
    ImageView img_map;
    Button btn_google_map;

    String selectedUnit = "A"; // Default unit selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_finder);

        db = FirebaseFirestore.getInstance();

        spinner_unit = findViewById(R.id.spinner_unit);
        edt_roll = findViewById(R.id.edt_roll);
        txt_room_info = findViewById(R.id.txt_room_info);
        btn_submit_roll = findViewById(R.id.btn_submit_roll);
        img_map = findViewById(R.id.img_map);
        btn_google_map = findViewById(R.id.btn_google_map);

        txt_room_info.setVisibility(View.GONE);
        img_map.setVisibility(View.GONE);
        btn_google_map.setVisibility(View.GONE);



        // Setup Spinner with unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unit_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(adapter);

        // Handle unit selection
        spinner_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedUnit = "A"; // Default to A if nothing is selected
            }
        });

        // Handle find button click
        btn_submit_roll.setOnClickListener(v -> {
            String roll = edt_roll.getText().toString().trim();
            if (!roll.isEmpty() && !selectedUnit.equals("Select Unit")) {
                fetchRoomData(selectedUnit, roll);
            } else {
                Toast.makeText(RoomFinder.this, "Please select a unit and enter a roll number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchRoomData(String unit, String roll) {
        DocumentReference docRef = db.collection(unit).document(roll);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String roomNumber = document.getString("room_number");
                    String floor = document.getString("floor");
                    String buildingId = document.getString("building_id");

                    String buildingName = getBuildingName(buildingId);

                    txt_room_info.setText("Room: " + roomNumber + "\nFloor: " + floor + "\nBuilding: " + buildingName);
                    txt_room_info.setVisibility(View.VISIBLE);

                    viewMap(buildingId);
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

    private void viewMap(String buildingId) {
        if (buildingId.equals("1")) {
            img_map.setImageResource(R.drawable.map_wazed);
            btn_google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    // wazed building
                    intent.setData(Uri.parse("https://maps.app.goo.gl/VHT1SnAdLicH9ipb6"));
                    startActivity(intent);
                }
            });
            img_map.setVisibility(View.VISIBLE);
            btn_google_map.setVisibility(View.VISIBLE);
        }
        else if (buildingId.equals("2")) {
            img_map.setImageResource(R.drawable.map_academic_1);
            btn_google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    // agri building
                    intent.setData(Uri.parse("https://maps.app.goo.gl/XGKVA7TuanBBpaqP9"));
                    startActivity(intent);
                }
            });
            img_map.setVisibility(View.VISIBLE);
            btn_google_map.setVisibility(View.VISIBLE);
        }
        else if (buildingId.equals("3")) {
            img_map.setImageResource(R.drawable.map_academic_2);
            btn_google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    // Engineering building
                    intent.setData(Uri.parse("https://maps.app.goo.gl/HW7xd7NLEp1oRFyM8"));
                    startActivity(intent);
                }
            });
            img_map.setVisibility(View.VISIBLE);
            btn_google_map.setVisibility(View.VISIBLE);
        }
        else if (buildingId.equals("4")) {
            img_map.setImageResource(R.drawable.map_dvm);

            btn_google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    // DVM building
                    intent.setData(Uri.parse("https://maps.app.goo.gl/1PJZEdzf9F7KFfLH6"));
                    startActivity(intent);
                }
            });
            img_map.setVisibility(View.VISIBLE);
            btn_google_map.setVisibility(View.VISIBLE);
        }
        else if (buildingId.equals("5")) {
            img_map.setImageResource(R.drawable.map_qudrate);
            btn_google_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    // Qudrat-i-Khuda building
                    intent.setData(Uri.parse("https://maps.app.goo.gl/xDUUHsou8TmFk31W8"));
                    startActivity(intent);
                }
            });

            img_map.setVisibility(View.VISIBLE);
            btn_google_map.setVisibility(View.VISIBLE);
        }
    }

    private String getBuildingName(String buildingId) {
        switch (buildingId) {
            case "1":
                return "Dr M A Wazed Bhaban";
            case "2":
                return "Academic Building 1";
            case "3":
                return "Academic Building 2";
            case "4":
                return "Academic Building 3";
            case "5":
                return "Qudrat-e-Khuda Bhaban";
            default:
                return "Unknown Building";
        }
    }
}
