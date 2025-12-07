package com.railway.cablejoint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.railway.cablejoint.adapters.JointDataAdapter;
import com.railway.cablejoint.models.JointData;
import com.railway.cablejoint.utils.ExportHelper;
import com.railway.cablejoint.utils.SheetsHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private Spinner spinnerSortBy;
    private Button btnExportExcel, btnExportWord, btnExportPDF;
    private RecyclerView recyclerView;
    private JointDataAdapter adapter;
    private List<JointData> dataList = new ArrayList<>();
    private List<JointData> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        initViews();
        setupSpinner();
        loadData();

        btnExportExcel.setOnClickListener(v -> exportToExcel());
        btnExportWord.setOnClickListener(v -> exportToWord());
        btnExportPDF.setOnClickListener(v -> exportToPDF());
    }

    private void initViews() {
        spinnerSortBy = findViewById(R.id.spinnerSortBy);
        btnExportExcel = findViewById(R.id.btnExportExcel);
        btnExportWord = findViewById(R.id.btnExportWord);
        btnExportPDF = findViewById(R.id.btnExportPDF);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JointDataAdapter(filteredList, data -> {});
        recyclerView.setAdapter(adapter);
    }

    private void setupSpinner() {
        String[] sortOptions = {"Sort by Section", "Sort by Sub-Section", "Sort by Date"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, sortOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSortBy.setAdapter(spinnerAdapter);

        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, 
                                       int position, long id) {
                sortData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void loadData() {
        new LoadDataTask().execute();
    }

    private void sortData(int sortType) {
        filteredList.clear();
        filteredList.addAll(dataList);

        switch (sortType) {
            case 0: // Sort by Section
                Collections.sort(filteredList, (a, b) -> 
                        a.getSection().compareTo(b.getSection()));
                break;
            case 1: // Sort by Sub-Section
                Collections.sort(filteredList, (a, b) -> 
                        a.getSubSection().compareTo(b.getSubSection()));
                break;
            case 2: // Sort by Date
                Collections.sort(filteredList, (a, b) -> 
                        a.getDate().compareTo(b.getDate()));
                break;
        }

        adapter.notifyDataSetChanged();
    }

    private void exportToExcel() {
        new ExportTask("excel").execute();
    }

    private void exportToWord() {
        new ExportTask("word").execute();
    }

    private void exportToPDF() {
        new ExportTask("pdf").execute();
    }

    private class LoadDataTask extends AsyncTask<Void, Void, List<JointData>> {
        @Override
        protected List<JointData> doInBackground(Void... voids) {
            try {
                // Note: Implement Google Sign-In first
                // SheetsHelper helper = new SheetsHelper(ReportActivity.this, credential);
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
                sortData(0);
            } else {
                Toast.makeText(ReportActivity.this, 
                        "No data found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ExportTask extends AsyncTask<Void, Void, String> {
        private String exportType;

        ExportTask(String exportType) {
            this.exportType = exportType;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(ReportActivity.this, 
                    "Exporting to " + exportType.toUpperCase() + "...", 
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                File exportDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "RailwayJointReports");
                if (!exportDir.exists()) {
                    exportDir.mkdirs();
                }

                String fileName = "joint_report_" + System.currentTimeMillis();
                File file;

                switch (exportType) {
                    case "excel":
                        file = new File(exportDir, fileName + ".xlsx");
                        ExportHelper.exportToExcel(filteredList, file);
                        break;
                    case "word":
                        file = new File(exportDir, fileName + ".docx");
                        ExportHelper.exportToWord(filteredList, file);
                        break;
                    case "pdf":
                        file = new File(exportDir, fileName + ".pdf");
                        ExportHelper.exportToPDF(filteredList, file);
                        break;
                    default:
                        return null;
                }

                return file.getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String filePath) {
            if (filePath != null) {
                Toast.makeText(ReportActivity.this, 
                        "Exported successfully to: " + filePath, 
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ReportActivity.this, 
                        "Export failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
