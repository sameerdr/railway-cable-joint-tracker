package com.railway.cablejoint;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.railway.cablejoint.adapters.JointDataAdapter;
import com.railway.cablejoint.models.JointData;
import com.railway.cablejoint.utils.SheetsHelper;
import java.util.ArrayList;
import java.util.List;

public class ShowJointInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private JointDataAdapter adapter;
    private ProgressBar progressBar;
    private List<JointData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joint_info);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new JointDataAdapter(dataList, this::onItemClick);
        recyclerView.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        new LoadDataTask().execute();
    }

    private void onItemClick(JointData data) {
        Intent intent = new Intent(this, EditJointActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    private class LoadDataTask extends AsyncTask<Void, Void, List<JointData>> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<JointData> doInBackground(Void... voids) {
            try {
                // Note: Implement Google Sign-In first
                // SheetsHelper helper = new SheetsHelper(ShowJointInfoActivity.this, credential);
                // return helper.getAllData();
                return new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        @Override
        protected void onPostExecute(List<JointData> result) {
            progressBar.setVisibility(View.GONE);
            if (result != null && !result.isEmpty()) {
                dataList.clear();
                dataList.addAll(result);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(ShowJointInfoActivity.this, 
                        "No data found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
