package com.example.rainingcontrol;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Activity2 extends AppCompatActivity {
    private TableLayout tableLayout;
    private TableRow tableRow;
    private SharedPreferences catchmentTypePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        initData();
        initView();
    }

    void initData(){
        catchmentTypePreferences = getSharedPreferences("汇水面", MODE_PRIVATE);
        SharedPreferences.Editor editor = catchmentTypePreferences.edit();
        editor.putFloat("绿化屋面(绿色屋顶,基质层厚度≥300mm)", 0.35f);
        editor.putFloat("硬屋面、未铺石子的平屋面、沥青屋面", 0.85f);
        editor.putFloat("铺石子的平屋面", 0.65f);
        editor.putFloat("混凝土或沥青路面及广场", 0.85f);
        editor.putFloat("大块石等铺砌路面及广场", 0.55f);
        editor.putFloat("沥青表面处理的碎石路面及广场", 0.50f);
        editor.putFloat("级配碎石路面及广场",0.40f);
        editor.putFloat("干砌砖石或碎石路面及广场",0.40f);
        editor.putFloat("非铺砌土路面",0.30f);
        editor.putFloat("绿地",0.15f);
        editor.putFloat("下沉式绿地",0.15f);
        editor.putFloat("水面",1.00f);
        editor.putFloat("地下建筑覆土绿地（覆土≥500mm）",0.15f);
        editor.putFloat("地下建筑覆土绿地（覆土＜500mm）",0.35f);
        editor.putFloat("透水铺装地面",0.20f);
        editor.commit();
    }

    void initView(){
        tableLayout = findViewById(R.id.tableLayout);
        tableLayout.setBackgroundColor(Color.BLUE);
        Map map = catchmentTypePreferences.getAll();
        float value;
        for (Object key : map.keySet()){
            value = catchmentTypePreferences.getFloat(String.valueOf(key), -1);
            //createRow(String.valueOf(key), String.valueOf(value));
        }
    }

    void createRow(String str0, String str1) {
        ArrayList<String> list = new ArrayList<>();
        list.add(str0);
        list.add(str1);

        TableRow tableRow = new TableRow(Activity2.this);
        TableLayout.LayoutParams rowParams = (TableLayout.LayoutParams) tableRow.getLayoutParams();
        rowParams.setMargins(1,1,0,1);
        tableRow.setLayoutParams(rowParams);
        tableLayout.addView(tableRow);

        TableRow.LayoutParams tvParams = (TableRow.LayoutParams) tableRow.getLayoutParams();
        tvParams.setMargins(0,0,1,0);
        for (int i=0;i<2;i++) {
            TextView tv = new TextView(Activity2.this);
            tv.setBackgroundColor(Color.WHITE);
            tv.setText(list.get(i));
            tv.setLayoutParams(tvParams);
            tableRow.addView(tv);
        }
        EditText et = new EditText(Activity2.this);
        et.setText("0");
        et.setLayoutParams(tvParams);
        tableRow.addView(et);
    }
}
