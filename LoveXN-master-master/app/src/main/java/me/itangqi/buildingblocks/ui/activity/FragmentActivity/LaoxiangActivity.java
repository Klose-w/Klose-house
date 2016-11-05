package me.itangqi.buildingblocks.ui.activity.FragmentActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.adapter.LaoxiangAdapter;
import me.itangqi.buildingblocks.network.HttpThreadString;

/**
 * Created by oreo on 2016/10/20.
 */
public class LaoxiangActivity extends SwipeBackActivity {
    @Bind(R.id.tiecard_list_laoxiang)
    RecyclerView cardList;
    @Bind(R.id.toolbar_laoxiang)
    Toolbar toolbar;
    @Bind(R.id.fab_add_comment_laoxiang)
    FloatingActionButton fab;
    private String[] pro={"北京","天津","上海","重庆","河北省","山西省","辽宁省","吉林省","黑龙江",
            "江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省",
            "海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古","广西",
            "西藏","宁夏","新疆","香港","澳门"};
    private SwipeBackLayout mSwipeBackLayout;
    private Intent intent;
    private RecyclerView.LayoutManager mLayoutManager;
    private String reply;
    private LaoxiangAdapter adapter;
    private EditText replyText;
    private SharedPreferences sh;
    private int user_id;
    private  AlertDialog.Builder builder;
    private AlertDialog ad;
    int id;
    private ProgressDialog proDialog;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=msg.getData();
            String test=b.getString("state");
            Log.i("logintest", "" + test);
            if(test.contains("23333")){
                proDialog.dismiss();
                initComment();
                proDialog.dismiss();
                //Toast.makeText(TiebaActivity.this, "回复成功", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(LaoxiangActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                proDialog.dismiss();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laoxiang);
        ButterKnife.bind(this);
        intent = getIntent();//得到老乡具体内容
        int proid=intent.getIntExtra("pro",1);
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String pic_exist=intent.getStringExtra("pic_exist");
        id = intent.getIntExtra("id", 0);
        sh=getSharedPreferences("ziliao", MODE_PRIVATE);
        user_id=sh.getInt("user_id",1);
        //初始化Adapter
        adapter=new LaoxiangAdapter(this,title,content,id,pic_exist,proid);
        mLayoutManager = new LinearLayoutManager(this);
        cardList.setHasFixedSize(true);
        cardList.setLayoutManager(mLayoutManager);
        cardList.setAdapter(adapter);
        //start toolbar
        CreateToolbar();
        //start swipBacklayout
        CreateSwipeBackLayout();
        //new reply and text
        reply = new String();
        replyText = new EditText(this);
        // create reply dialog
        CreateDialog();
        //create floatButton
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.show();
            }
        });
        initComment();
    }



    //create Handler
    Handler chandle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b=msg.getData();
            String test=b.getString("state");
            Log.i("logintestaa", "" + test+id);
            if(test.contains("comment_id")){
                adapter.network(test);
               // adapter.notifyDataSetChanged();
                proDialog.dismiss();
            }else if(test.contains("100008")){
                proDialog.dismiss();
            }else{
                Toast.makeText(LaoxiangActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                proDialog.dismiss();
            }
        }
    };

    private void createProgressBar() {
        proDialog = android.app.ProgressDialog.show(LaoxiangActivity.this, "请等待", "数据传送中！");
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
            }
        };
        thread.start();
    }

    private void initComment() {
        Map map=new HashMap();
        map.put("comment_type","4");
        map.put("notice_id",String.valueOf(id));
        map.put("method", "getComment.php");
        new HttpThreadString(chandle,LaoxiangActivity.this,map,proDialog).start();
        createProgressBar();
    }


    private void CreateDialog(){
        builder = new AlertDialog.Builder(LaoxiangActivity.this);
        builder.setTitle("回复楼主");
        builder.setView(replyText, 20, 20, 20, 20);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("回复", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                reply = replyText.getText().toString();
                if (reply.isEmpty()) {
                    Toast.makeText(LaoxiangActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Map map = new HashMap();
                    map.put("user_id", String.valueOf(user_id));
                    map.put("comment_content", reply);
                    map.put("comment_type", "4");
                    map.put("notice_id", String.valueOf(id));
                    map.put("method", "addComment.php");
                    replyText.setText("");
                    new HttpThreadString(handler, LaoxiangActivity.this, map, proDialog).start();
                    createProgressBar();
                }
            }
        });
        ad = builder.create();
    }

    private void CreateToolbar() {
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(true);
    }

    private void CreateSwipeBackLayout() {
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }


}
