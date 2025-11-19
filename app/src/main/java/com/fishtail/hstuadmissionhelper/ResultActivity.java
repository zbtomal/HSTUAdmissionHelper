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

/**
 * This activity allows users to check their admission test results.
 * Users can enter their roll number, select a unit, and view their result.
 * The result data is fetched from a Firestore database.
 */
public class ResultActivity extends AppCompatActivity {

    EditText edt_roll_result;
    TextView txt_result;
    Button btn_show_result;
    Spinner spinner_unit;

    FirebaseFirestore db;  // Firestore instance

    /**
     * Called when the activity is first created.
     * This method initializes the UI elements, sets up the spinner for unit selection,
     * and defines the click listener for the "Show Result" button.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        edt_roll_result = findViewById(R.id.edt_roll_result);
        txt_result = findViewById(R.id.txt_result);
        btn_show_result = findViewById(R.id.btn_show_result);
        spinner_unit = findViewById(R.id.spinner_unit);

        txt_result.setVisibility(View.GONE); // Initially hide the result TextView

        final String[] selectedUnit = {"A"};  // Default unit is A

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
                selectedUnit[0] = parent.getItemAtPosition(position).toString();  // Get selected unit
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedUnit[0] = "A";  // Default to A if nothing is selected
            }
        });

        // Button click event
        btn_show_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll = edt_roll_result.getText().toString().trim();

                if (!roll.isEmpty()) {
                    // Fetch result based on selected unit
                    fetchResultData(roll, selectedUnit[0]);
                } else {
                    Toast.makeText(ResultActivity.this, "Please enter a roll number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Fetches the result data from the Firestore database based on the provided roll number and unit.
     * The result is then displayed in the `txt_result` TextView.
     *
     * @param roll The roll number of the student.
     * @param unit The academic unit (e.g., "A", "B", "C").
     */
    private void fetchResultData(String roll, String unit) {
        // Dynamically select the collection based on the unit
        String collectionName = "result_" + unit;  // result_A, result_B, result_C, or result_D

        // Get the reference to the student's document
        DocumentReference docRef = db.collection(collectionName).document(roll);

        // Fetch the result data from Firestore
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Get the result and student name
                    String result = document.getString("result");
                    String studentName = document.getString("student_name");

                    // Display result and student name
                    txt_result.setText("Name: " + studentName + "\nResult: " + result);
                    txt_result.setVisibility(View.VISIBLE);  // Show result
                } else {
                    txt_result.setText("No result found for this roll.");
                    txt_result.setVisibility(View.VISIBLE);  // Show message if no result found
                }
            } else {
                txt_result.setText("Error fetching data.");
                txt_result.setVisibility(View.VISIBLE);  // Show error message
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(ResultActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
