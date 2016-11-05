package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import me.itangqi.buildingblocks.R;

public class School_Activity extends AppCompatActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mSwipeBackLayout;
    private ListView listView;
    private Spinner spinner;
    private Toolbar toolbar;

    private String[] arrnum = {"029－87082845 ",
            "029-87082710",
            "029-87082613 ",
            "029-87092102",
            "029-87091117",
            "029-87082216",
            "87080272",
            "029-87080055",
            "029-87082902",
            "029-87092391",
            "029-87092352 ",
            "",
            "029-87091994",
            "",
            "029-87092226",
            "029-87081209",
            "029-87092324　",
            "029-87092629",
            "029-87091915",
            "029-87092466",
            "029-87091122",
            "029-87092448",
            "029—87082876",
            "",
            "029-87080130",
            "029-87092397 ",
            "029-87082976"};
    private int wz;
    private String[] com_array={
            "筛选", "教务处", "保卫处", " 思政部"," 各个学院","卡务中心"," 右任书院"," 学生会","其他"};

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private String[] title=new String[]
            {
                    "农",
                    "植",
                    "园",
                    "动",
                    "动",
                    "林",
                    "风",
                    "资",
                    "水",
                    "机",
                    "信",
                    "食",
                    "葡",
                    "生",
                    "理",
                    "经",
                    "人",
                    "外",
                    "创",
                    "体",
                    "教",
                    "学",
                    "团",
                    "图",
                    "校",
                    "网"

            };
    private String[] names=new String[]
            {
                    "农学系",
                    "植物保护学院",
                    "园艺学院",
                    "动物科技学院",
                    "动物医学",
                    "林学院",
                    "风景园林艺术学院",
                    "资源环境学院",
                    "水利与建筑工程学院",
                    "机械与电子工程学院",
                    "信息工程学院",
                    "食品科学与工程学院",
                    "葡萄酒学院",
                    "生命科学学院",
                    "理学院",
                    "经济管理学院",
                    "人文社会发展学院",
                    "外语系",
                    "创新学院",
                    "体育部",
                    "教务处",
                    "学生工作处",
                    "团委",
                    "图书馆",
                    "校医院",
                    "网教中心"


            };
    private String[] headers=new String[]
            {
                    "联系电话：029－87082845 ",
                    "联系电话：029-87082710",
                    "联系电话：029-87082613 ",
                    "联系电话：029-87092102",
                    "联系电话：029-87091117",
                    "联系电话：029-87082216",
                    "联系电话：87080272",
                    "联系电话：029-87080055",
                    "联系电话：029-87082902",
                    "联系电话：029-87092391",
                    "联系电话：029-87092352 ",
                    "联系电话：",
                    "联系电话：029-87091994",
                    "联系电话：",
                    "联系电话：029-87092226",
                    "联系电话：029-87081209",
                    "联系电话：029-87092324　",
                    "联系电话：029-87092629",
                    "联系电话：029-87091915",
                    "联系电话：029-87092466",
                    "联系电话：029-87091122",
                    "联系电话：029-87092448",
                    "联系电话：029—87082876",
                    "联系电话：",
                    "联系电话：029-87080130",
                    "联系电话：029-87092397 ",
                    "联系电话：029-87082976"



            };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_);
        listView = (ListView) findViewById(R.id.schoolView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("校园组织");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++)
        {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("header",title[i]);
            listItem.put("personName",names[i]);
            listItem.put("desc",headers[i]);
            listItems.add(listItem);
        }


        SimpleAdapter simpleAdapter =new SimpleAdapter(this,listItems,R.layout.coll_item,
                new String[]{"personName","header","desc"},
                new int[]{R.id.name1,R.id.biaoti,R.id.desc});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(arrnum[i]);
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
                    wz = 2;
                } else if (i == 3) {
                    wz = 4;
                } else if (i == 4) {
                    wz = 6;
                } else if (i == 5) {
                    wz = 8;
                } else if (i == 6) {
                    wz = 10;
                }
                listView.setSelection(wz);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mSwipeBackLayout = new SwipeBackActivityHelper(this);
        mSwipeBackLayout.onActivityCreate();

    }
    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        data.add("教务处");
        data.add("教务处电话");
        data.add("保卫处");
        data.add("保卫处1");
        data.add("思政部");
        data.add("思政部1");
        data.add("信息学院");
        data.add("资环学院");
        data.add("卡务中心");
        data.add("琴箫口琴社");
        data.add("右任书院");
        data.add("校体育部");
        data.add("修车");




        return data;
    }

    protected void dialog(final String num) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认拨打" + num + "吗？").setTitle("提示").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://" + num));
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeBackLayout.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackLayout.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

}

