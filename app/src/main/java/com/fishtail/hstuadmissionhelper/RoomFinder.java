package com.fishtail.hstuadmissionhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        txt_room_info.setVisibility(View.GONE);

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
