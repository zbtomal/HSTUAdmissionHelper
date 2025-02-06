package com.fishtail.hstuadmissionhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TransportActivity extends AppCompatActivity {

    private Spinner spinnerDivision, spinnerDistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transport);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Spinners
        spinnerDivision = findViewById(R.id.spinner_division);
        spinnerDistrict = findViewById(R.id.spinner_district);

        // Setup Division Spinner
        ArrayAdapter<CharSequence> divisionAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.divisions,
                android.R.layout.simple_spinner_item
        );
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDivision.setAdapter(divisionAdapter);

        // Division Spinner listener to update District Spinner
        spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> districtAdapter;

                switch (position) {
                    case 0: // Dhaka Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.dhaka_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 1: // Chattogram Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.chattogram_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 2: // Khulna Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.khulna_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 3: // Rajshahi Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.rajshahi_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 4: // Barishal Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.barishal_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 5: // Sylhet Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.sylhet_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 6: // Rangpur Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.rangpur_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    case 7: // Mymensingh Division
                        districtAdapter = ArrayAdapter.createFromResource(
                                TransportActivity.this,
                                R.array.mymensingh_districts,
                                android.R.layout.simple_spinner_item
                        );
                        break;
                    default:
                        districtAdapter = null;
                }

                if (districtAdapter != null) {
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDistrict.setAdapter(districtAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when nothing is selected (optional)
            }
        });
    }
}
