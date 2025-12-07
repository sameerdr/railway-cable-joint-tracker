package com.railway.cablejoint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.railway.cablejoint.models.JointData;
import com.railway.cablejoint.utils.SheetsHelper;
import java.util.ArrayList;
import java.util.List;

public class ShowMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<JointData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        loadData();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        
        // Default location (India)
        LatLng defaultLocation = new LatLng(20.5937, 78.9629);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 5));
    }

    private void loadData() {
        new LoadDataTask().execute();
    }

    private void displayMarkersOnMap() {
        if (mMap == null || dataList.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boolean hasValidLocation = false;

        for (JointData data : dataList) {
            if (data.getLatitude() != 0.0 && data.getLongitude() != 0.0) {
                LatLng position = new LatLng(data.getLatitude(), data.getLongitude());
                
                String title = data.getSection() + " - " + data.getSubSection();
                String snippet = "KM: " + data.getKm() + "\n" +
                        "Cable: " + data.getCableType() + "\n" +
                        "Joint: " + data.getJointType() + "\n" +
                        "Date: " + data.getDate();

                mMap.addMarker(new MarkerOptions()
                        .position(position)
                        .title(title)
                        .snippet(snippet));

                boundsBuilder.include(position);
                hasValidLocation = true;
            }
        }

        if (hasValidLocation) {
            try {
                LatLngBounds bounds = boundsBuilder.build();
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class LoadDataTask extends AsyncTask<Void, Void, List<JointData>> {
        @Override
        protected List<JointData> doInBackground(Void... voids) {
            try {
                // Note: Implement Google Sign-In first
                // SheetsHelper helper = new SheetsHelper(ShowMapActivity.this, credential);
                // return helper.getAllData();
                return new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        @Override
        protected void onPostExecute(List<JointData> result) {
            if (result != null && !result.isEmpty()) {
                dataList.clear();
                dataList.addAll(result);
                displayMarkersOnMap();
                Toast.makeText(ShowMapActivity.this, 
                        result.size() + " locations loaded", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShowMapActivity.this, 
                        "No location data found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
