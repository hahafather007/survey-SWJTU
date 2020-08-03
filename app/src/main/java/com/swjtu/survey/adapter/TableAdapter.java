package com.swjtu.survey.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.swjtu.survey.R;
import com.swjtu.survey.bean.TableBean;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private Context mContext;
    public TableAdapter(Context context){
        mContext = context;
        list = new ArrayList<>();
    }

    private List<TableBean.DataBean> list;
    private static final String TAG = "TableAdapter";

    public void setList(List<TableBean.DataBean> list) {
        this.list = list;
        Log.i(TAG, "setList: "+new Gson().toJson(this.list));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_table,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.linearLayout.removeAllViews();

        if (list.get(position).getData() != null) {
            holder.linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 0; i < list.get(position).getData().size(); i++) {
                TextView textView = new TextView(mContext);
                textView.setTextSize(16);
                textView.setTextColor(Color.parseColor("#333333"));
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundResource(R.drawable.table_line);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(dip2px(100),dip2px(50));
                layoutParams.height = dip2px(50);
                layoutParams.width = dip2px(100);
                textView.setLayoutParams(layoutParams);
                textView.setText(list.get(position).getData().get(i));
                holder.linearLayout.addView(textView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ll_item_table);
        }
    }

    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}