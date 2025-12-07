package com.railway.cablejoint;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.location.*;
import com.google.android.material.textfield.TextInputEditText;
import com.railway.cablejoint.models.JointData;
import com.railway.cablejoint.utils.SheetsHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddDataActivity extends AppCompatActivity {

    private TextInputEditText etDate, etSection, etSubSection, etKm, etOheMast;
    private TextInputEditText etOffset, etCableType, etJointType, etReason;
    private TextInputEditText etStaffPresent, etRemark, etLatitude, etLongitude;
    private ImageView ivPhoto;
    private Button btnSelectPhoto, btnGetLocation, btnSubmit;
    private Uri photoUri;
    private FusedLocationProviderClient fusedLocationClient;
    private double currentLatitude = 0.0, currentLongitude = 0.0;
    private static final int LOCATION_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        initViews();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        
        etDate.setOnClickListener(v -> showDatePicker());
        btnSelectPhoto.setOnClickListener(v -> selectPhoto());
        btnGetLocation.setOnClickListener(v -> getCurrentLocation());
        btnSubmit.setOnClickListener(v -> submitData());
        
        // Auto-get location on start
        getCurrentLocation();
    }

    private void initViews() {
        etDate = findViewById(R.id.etDate);
        etSection = findViewById(R.id.etSection);
        etSubSection = findViewById(R.id.etSubSection);
        etKm = findViewById(R.id.etKm);
        etOheMast = findViewById(R.id.etOheMast);
        etOffset = findViewById(R.id.etOffset);
        etCableType = findViewById(R.id.etCableType);
        etJointType = findViewById(R.id.etJointType);
        etReason = findViewById(R.id.etReason);
        etStaffPresent = findViewById(R.id.etStaffPresent);
        etRemark = findViewById(R.id.etRemark);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        ivPhoto = findViewById(R.id.ivPhoto);
        btnSelectPhoto = findViewById(R.id.btnSelectPhoto);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        btnSubmit = findViewById(R.id.btnSubmit);
        
        // Set today's date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        etDate.setText(sdf.format(Calendar.getInstance().getTime()));
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String date = String.format(Locale.getDefault(), "%02d/%02d/%04d", 
                            dayOfMonth, month + 1, year);
                    etDate.setText(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void selectPhoto() {
        ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            photoUri = data.getData();
            ivPhoto.setImageURI(photoUri);
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 
                    LOCATION_PERMISSION_CODE);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        currentLatitude = location.getLatitude();
                        currentLongitude = location.getLongitude();
                        etLatitude.setText(String.valueOf(currentLatitude));
                        etLongitude.setText(String.valueOf(currentLongitude));
                        Toast.makeText(this, "Location captured!", Toast.LENGTH_SHORT).show();
                    } else {
                        requestNewLocation();
                    }
                });
    }

    private void requestNewLocation() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(0);
        locationRequest.setFastestInterval(0);
        locationRequest.setNumUpdates(1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            if (location != null) {
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                etLatitude.setText(String.valueOf(currentLatitude));
                etLongitude.setText(String.valueOf(currentLongitude));
            }
        }
    };

    private void submitData() {
        if (!validateInputs()) return;

        JointData data = new JointData();
        data.setDate(etDate.getText().toString());
        data.setSection(etSection.getText().toString());
        data.setSubSection(etSubSection.getText().toString());
        data.setKm(etKm.getText().toString());
        data.setOheMast(etOheMast.getText().toString());
        data.setOffsetFromTrack(etOffset.getText().toString());
        data.setCableType(etCableType.getText().toString());
        data.setJointType(etJointType.getText().toString());
        data.setPhotoUrl(photoUri != null ? photoUri.toString() : "");
        data.setLatitude(currentLatitude);
        data.setLongitude(currentLongitude);
        data.setReason(etReason.getText().toString());
        data.setStaffPresent(etStaffPresent.getText().toString());
        data.setRemark(etRemark.getText().toString());

        new SaveDataTask().execute(data);
    }

    private boolean validateInputs() {
        if (etDate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etSection.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter section", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (currentLatitude == 0.0 || currentLongitude == 0.0) {
            Toast.makeText(this, "Please capture location", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private class SaveDataTask extends AsyncTask<JointData, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            btnSubmit.setEnabled(false);
            btnSubmit.setText("Saving...");
        }

        @Override
        protected Boolean doInBackground(JointData... params) {
            try {
                // Note: You need to implement Google Sign-In first
                // SheetsHelper helper = new SheetsHelper(AddDataActivity.this, credential);
                // helper.appendData(params[0]);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            btnSubmit.setEnabled(true);
            btnSubmit.setText("Submit");
            if (success) {
                Toast.makeText(AddDataActivity.this, "Data saved successfully!", 
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddDataActivity.this, "Failed to save data", 
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, 
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
}
