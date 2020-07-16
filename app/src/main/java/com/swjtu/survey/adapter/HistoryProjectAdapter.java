package com.swjtu.survey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swjtu.survey.R;
import com.swjtu.survey.bean.SurveyProjectBean;
import com.swjtu.survey.widget.RoundImageView;

import java.util.ArrayList;

public class HistoryProjectAdapter extends RecyclerView.Adapter<HistoryProjectAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SurveyProjectBean> datas = new ArrayList<>();
    private OnProjectItemClickListener listener;

    public HistoryProjectAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_project, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvProjectName.setText(datas.get(position).getProjectName());
        holder.tvCreateTime.setText(datas.get(position).getCreateTime());
        holder.tvUpdateTime.setText(datas.get(position).getCreateTime());
        holder.tvSize.setText("120MB");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(datas.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RoundImageView ivProjectHead;
        TextView tvProjectName;
        TextView tvCreateTime;
        TextView tvUpdateTime;
        TextView tvSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            ivProjectHead = itemView.findViewById(R.id.iv_project_head);
            tvCreateTime = itemView.findViewById(R.id.tv_create_time);
            tvProjectName = itemView.findViewById(R.id.tv_project_name);
            tvUpdateTime = itemView.findViewById(R.id.tv_update_time);
            tvSize = itemView.findViewById(R.id.tv_size);
        }
    }

    public void updateData(ArrayList<SurveyProjectBean> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    public interface OnProjectItemClickListener {
        void onItemClick(SurveyProjectBean surveyProjectBean);
    }

    public void setListener(OnProjectItemClickListener listener) {
        this.listener = listener;
    }
}
