package com.railway.cablejoint.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.railway.cablejoint.R;
import com.railway.cablejoint.models.JointData;
import java.util.List;

public class JointDataAdapter extends RecyclerView.Adapter<JointDataAdapter.ViewHolder> {

    private List<JointData> dataList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(JointData data);
    }

    public JointDataAdapter(List<JointData> dataList, OnItemClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_joint_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JointData data = dataList.get(position);
        holder.tvDate.setText("Date: " + data.getDate());
        holder.tvSection.setText("Section: " + data.getSection());
        holder.tvSubSection.setText("Sub-Section: " + data.getSubSection());
        holder.tvKm.setText("KM: " + data.getKm());
        holder.tvCableType.setText("Cable: " + data.getCableType());
        holder.tvJointType.setText("Joint: " + data.getJointType());
        holder.tvLocation.setText(String.format("Location: %.6f, %.6f", 
                data.getLatitude(), data.getLongitude()));

        holder.itemView.setOnClickListener(v -> listener.onItemClick(data));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvSection, tvSubSection, tvKm, tvCableType, tvJointType, tvLocation;

        ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSection = itemView.findViewById(R.id.tvSection);
            tvSubSection = itemView.findViewById(R.id.tvSubSection);
            tvKm = itemView.findViewById(R.id.tvKm);
            tvCableType = itemView.findViewById(R.id.tvCableType);
            tvJointType = itemView.findViewById(R.id.tvJointType);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }
    }
}
