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

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActivity extends AppCompatActivity {

    EditText edt_roll_result;
    TextView txt_result;
    Button btn_show_result;

    FirebaseFirestore db;  // Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        edt_roll_result = findViewById(R.id.edt_roll_result);
        txt_result = findViewById(R.id.txt_result);
        btn_show_result = findViewById(R.id.btn_show_result);

        txt_result.setVisibility(View.GONE);

        // Button click event
        btn_show_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll = edt_roll_result.getText().toString().trim();

                if (!roll.isEmpty()) {
                    fetchResultData(roll);
                } else {
                    Toast.makeText(ResultActivity.this, "Please enter a roll number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchResultData(String roll) {
        DocumentReference docRef = db.collection("student_results").document(roll);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String result = document.getString("result"); // Fetch the result field
                    txt_result.setText("Result: " + result);
                    txt_result.setVisibility(View.VISIBLE);
                } else {
                    txt_result.setText("No result found for this roll.");
                    txt_result.setVisibility(View.VISIBLE);
                }
            } else {
                txt_result.setText("Error fetching data.");
                txt_result.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(ResultActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
