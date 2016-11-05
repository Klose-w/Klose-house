package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import me.itangqi.buildingblocks.R;


public class Shop_Activity extends AppCompatActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mSwipeBackLayout;
    private ListView listView;
    private Toolbar toolbar;
    private String[] arrnum={
            "13759888517",
            "",
            "15729596600",
            "18991290425",
            "",
            "02965650999",
            "02987048688",
            "",
            "",
            "",
            "02987076555"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_);
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++)
        {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("header",Images[i]);
            listItem.put("personName",names[i]);
            listItem.put("desc",headers[i]);
            listItems.add(listItem);
        }
        listView=(ListView)findViewById(R.id.schoolView);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("商家");
        setSupportActionBar(toolbar);
        SimpleAdapter simpleAdapter =new SimpleAdapter(this,listItems,R.layout.simple_item,
                new String[]{"personName","header","desc"},
                new int[]{R.id.name1,R.id.header,R.id.desc});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(arrnum[i]);
            }
        });
        mSwipeBackLayout = new SwipeBackActivityHelper(this);
        mSwipeBackLayout.onActivityCreate();

    }


    private String[] names=new String[]
            {
                    "川渝大排档",
                    "东北人家",
                    "喜阿婆",
                    "千言万语DIY",
                    "桌球室",
                    "中影时代电影院",
                    "恒大电影院",
                    "杨陵火车站",
                    "杨陵汽车站",
                    "锦江之星",
                    "景濠酒店"

            };
    private String[] headers=new String[]
            {
                    "地址：西农北校小东门 电话：13759888517",
                    "地址：神农路中段 电话：",
                    "地址：西农北校东门 电话：15729596600",
                    "地址：杨职西校北 电话：18991290425",
                    "地址：康乐路4号 电话：",
                    "地址：杨陵火车站旁 电话：02965650999",
                    "地址：邰城南路 电话：02987048688",
                    "地址：渭惠路 电话：",
                    "地址：火车站西 电话",
                    "地址：邰城高铁南站西南角 电话：",
                    "地址：后稷北路88号 电话：02987076555"

            };
    private int[] Images=new int[]
            {
                    R.drawable.tb2,
                    R.drawable.tb2,
                    R.drawable.tb2,
                    R.drawable.tb2,
                    R.drawable.bt1,
                    R.drawable.bt1,
                    R.drawable.bt1,
                    R.drawable.tb4,
                    R.drawable.tb4,
                    R.drawable.tb3,
                    R.drawable.tb3

            };
    protected void dialog(final String num) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认拨打"+num+"吗？").setTitle("提示").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+num));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
