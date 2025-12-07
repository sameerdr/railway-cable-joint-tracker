package com.railway.cablejoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddData, btnShowJointInfo, btnShowMap, btnReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddData = findViewById(R.id.btnAddData);
        btnShowJointInfo = findViewById(R.id.btnShowJointInfo);
        btnShowMap = findViewById(R.id.btnShowMap);
        btnReport = findViewById(R.id.btnReport);

        btnAddData.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddDataActivity.class));
        });

        btnShowJointInfo.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ShowJointInfoActivity.class));
        });

        btnShowMap.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ShowMapActivity.class));
        });

        btnReport.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ReportActivity.class));
        });
    }
}
