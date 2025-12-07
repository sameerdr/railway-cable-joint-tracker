package com.railway.cablejoint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.railway.cablejoint.models.JointData;
import com.railway.cablejoint.utils.SheetsHelper;

public class EditJointActivity extends AppCompatActivity {

    private TextInputEditText etDate, etSection, etSubSection, etKm, etOheMast;
    private TextInputEditText etOffset, etCableType, etJointType, etReason;
    private TextInputEditText etStaffPresent, etRemark;
    private Button btnUpdate;
    private JointData currentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_joint);

        initViews();
        
        // Get data from intent
        currentData = (JointData) getIntent().getSerializableExtra("data");
        if (currentData != null) {
            populateFields();
        }

        btnUpdate.setOnClickListener(v -> updateData());
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
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void populateFields() {
        etDate.setText(currentData.getDate());
        etSection.setText(currentData.getSection());
        etSubSection.setText(currentData.getSubSection());
        etKm.setText(currentData.getKm());
        etOheMast.setText(currentData.getOheMast());
        etOffset.setText(currentData.getOffsetFromTrack());
        etCableType.setText(currentData.getCableType());
        etJointType.setText(currentData.getJointType());
        etReason.setText(currentData.getReason());
        etStaffPresent.setText(currentData.getStaffPresent());
        etRemark.setText(currentData.getRemark());
    }

    private void updateData() {
        currentData.setDate(etDate.getText().toString());
        currentData.setSection(etSection.getText().toString());
        currentData.setSubSection(etSubSection.getText().toString());
        currentData.setKm(etKm.getText().toString());
        currentData.setOheMast(etOheMast.getText().toString());
        currentData.setOffsetFromTrack(etOffset.getText().toString());
        currentData.setCableType(etCableType.getText().toString());
        currentData.setJointType(etJointType.getText().toString());
        currentData.setReason(etReason.getText().toString());
        currentData.setStaffPresent(etStaffPresent.getText().toString());
        currentData.setRemark(etRemark.getText().toString());

        new UpdateDataTask().execute(currentData);
    }

    private class UpdateDataTask extends AsyncTask<JointData, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            btnUpdate.setEnabled(false);
            btnUpdate.setText("Updating...");
        }

        @Override
        protected Boolean doInBackground(JointData... params) {
            try {
                // Note: Implement Google Sign-In first
                // SheetsHelper helper = new SheetsHelper(EditJointActivity.this, credential);
                // helper.updateData(params[0]);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            btnUpdate.setEnabled(true);
            btnUpdate.setText("Update");
            if (success) {
                Toast.makeText(EditJointActivity.this, 
                        "Data updated successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditJointActivity.this, 
                        "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
