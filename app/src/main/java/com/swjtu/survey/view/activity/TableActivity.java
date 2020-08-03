package com.swjtu.survey.view.activity;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.floatview.base.BaseActivity;
import com.google.gson.Gson;
import com.swjtu.survey.R;
import com.swjtu.survey.adapter.TableAdapter;
import com.swjtu.survey.bean.TableBean;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableActivity extends BaseActivity {
    private static final String TAG = "TableActivity";
    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_table;
    }

    @Override
    protected void initData() {
        recyclerView = findViewById(R.id.rv_table);
    }

    @Override
    protected void initView() {
        tableAdapter = new TableAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(tableAdapter);

        List<TableBean.DataBean> dataBeans = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            TableBean.DataBean dataBean = new TableBean.DataBean();
            List<Integer> integers = new ArrayList<>();
            Random rand = new Random();
            int number = rand.nextInt(10);
            int hide = rand.nextInt(10);
            List<String> content = new ArrayList<>();

            for (int i1 = 0; i1 < number; i1++) {
                integers.add(i1);
                if (i1 == 0) {
                    content.add("标题 = "+i);
                }else {
                    content.add(hide == i1?"":"内容 = "+i1);
                }
                dataBean.setData(content);
            }
            dataBeans.add(dataBean);
        }
        tableAdapter.setList(dataBeans);
    }

    public void back(View view){
        this.finish();
    }

}
