package com.fishtail.hstuadmissionhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TransportActivity extends AppCompatActivity {

    private Spinner spinnerDivision, spinnerDistrict;
    private TextView scrollText;

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

        // Initialize views
        spinnerDivision = findViewById(R.id.spinner_division);
        spinnerDistrict = findViewById(R.id.spinner_district);
        scrollText = findViewById(R.id.scrollText);

        // Setup Division Spinner
        ArrayAdapter<CharSequence> divisionAdapter = ArrayAdapter.createFromResource(
                this, R.array.divisions, android.R.layout.simple_spinner_item);
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDivision.setAdapter(divisionAdapter);

        // Division Spinner listener to update District Spinner
        spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateDistrictSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        // District Spinner listener to update ScrollText
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateScrollText(spinnerDistrict.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                scrollText.setText("");
            }
        });

        // Set default division selection to prevent empty district spinner
        spinnerDivision.setSelection(0);
        updateDistrictSpinner(0);
    }

    /**
     * Updates the district spinner based on selected division.
     */
    private void updateDistrictSpinner(int divisionIndex) {
        int districtArrayResId;
        switch (divisionIndex) {
            case 0: districtArrayResId = R.array.districts_default; break;
            case 1: districtArrayResId = R.array.dhaka_districts; break;
            case 2: districtArrayResId = R.array.chattogram_districts; break;
            case 3: districtArrayResId = R.array.khulna_districts; break;
            case 4: districtArrayResId = R.array.rajshahi_districts; break;
            case 5: districtArrayResId = R.array.barishal_districts; break;
            case 6: districtArrayResId = R.array.sylhet_districts; break;
            case 7: districtArrayResId = R.array.rangpur_districts; break;
            case 8: districtArrayResId = R.array.mymensingh_districts; break;
            default: districtArrayResId = -1;
        }

        if (districtArrayResId != -1) {
            ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(
                    this, districtArrayResId, android.R.layout.simple_spinner_item);
            districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDistrict.setAdapter(districtAdapter);
        }
    }

    /**
     * Updates the scrollText with transport information based on selected district.
     */
    private void updateScrollText(String district) {
        // Example: Set custom transport info based on selected district
        switch (district) {
            case "Dhaka":
                scrollText.setText(R.string.dhaka_to_dinajpur);
                break;
            case "Chattogram":
                scrollText.setText(R.string.chattogram_to_dinajpur);
                break;
            case "Rajshahi":
                scrollText.setText(R.string.rajshahi_to_dinajpur);
                break;
            case "Tangail":
            case "Sirajganj":
            case "Bogra":
                scrollText.setText(R.string.bogra_to_dinajpur);
                break;
            case "Cumilla":
                scrollText.setText(R.string.cumilla_to_dinajpur);
                break;

            case "Noakhali":
                scrollText.setText(R.string.noakhali_to_dinajpur);
                break;

            case "Chandpur":
                scrollText.setText(R.string.chandpur_to_dinajpur);
                break;

            case "Pabna":
            case "Nator":
                scrollText.setText(R.string.natore_to_dinajpur);
                break;


            case "Faridpur":
                scrollText.setText(R.string.faridpur_to_dinajpur);
                break;
            case "Jashore":
            case "Satkhira":
            case "Narail":
            case "Khulna":
                scrollText.setText(R.string.khulna_to_dinajpur);
                break;

            case "Jhenaidah":
            case "Meherpur":
            case "Kushtia":
                scrollText.setText(R.string.kushtia_to_dinajpur);
                break;

            case "Habiganj":
            case "Moulvibazar":
            case "Sunamganj":
            case "Sylhet":
                scrollText.setText(R.string.sylhet_to_dinajpur);
                break;

            case "Mymensingh":
                scrollText.setText(R.string.mymensingh_to_dinajpur);
                break;

            case "Kishoreganj":
                scrollText.setText(R.string.kishoreganj_to_dinajpur);
                break;
                
            default:
                scrollText.setText("Select a district to see transport options.");

        }
    }
}
