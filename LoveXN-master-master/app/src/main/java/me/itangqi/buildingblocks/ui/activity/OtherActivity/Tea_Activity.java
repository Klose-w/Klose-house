package me.itangqi.buildingblocks.ui.activity.OtherActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import me.itangqi.buildingblocks.R;
public class Tea_Activity extends AppCompatActivity {
    private Spinner spinner;
    private int wz;
    private ListView listView;
    private Toolbar toolbar;
    private String[] com_array={
            "筛选", "信息工程", "思政部", " 理学院"," 资环学院","体育部"};
    private String[] name={"方勇","耿楠","耿耀军","方健斌","胡刚","杜俊丽","王燕","刘昊","李虎","张莎莎","丘处机"};
    private String[] sex={"男","男","男","男","男","女","女","男","男","女","男"};
    private String[] col={"信息工程","信息工程","信息工程","思政部","思政部","理学院","理学院","资环学院","资环学院","资环学院","体育部"};
    private String[] pho={"15892878738","18392412561","546512258","455451214","15892878738",
            "18392412561","546512258","455451214","15892878738","18392412561","546512258"};
    private String[] adr={"信息工程","信息工程","信息工程","思政部","思政部","理学院","理学院","资环学院","资环学院","资环学院","体育部"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_);
        listView=(ListView)findViewById(R.id.schoolView);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("教师信息");
        listView=(ListView)findViewById(R.id.schoolView);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,name);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, com_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    wz = 0;
                } else if (i == 1) {
                    wz = 0;
                } else if (i == 2) {
                    wz = 3;
                } else if (i == 3) {
                    wz = 5;
                } else if (i == 4) {
                    wz = 7;
                } else if (i == 5) {
                    wz = 10;
                }
                listView.setSelection(wz);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void dialog(final int num) {
        new AlertDialog.Builder(this).setIcon(R.drawable.tea)
                .setTitle("教师信息").setMessage("  姓名：  "+name[num]+"\n\n"+"  性别：  "+sex[num]+"\n\n"+
                        "  学院：  "+col[num]+"\n\n"+"  电话：  "+pho[num]+"\n\n"+"  办公室：  "+adr[num]+"\n\n").setPositiveButton("拨打",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://" + pho[num]));
                        startActivity(intent);
                    }
                }).create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


