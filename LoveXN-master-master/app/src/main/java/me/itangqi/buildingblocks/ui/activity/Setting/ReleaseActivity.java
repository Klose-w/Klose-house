package me.itangqi.buildingblocks.ui.activity.Setting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.application.App;
import me.itangqi.buildingblocks.network.HttpThreadString;
import me.itangqi.buildingblocks.network.UploadFile;
import me.itangqi.buildingblocks.network.clas.InsertId;
import me.itangqi.buildingblocks.ui.activity.base.BaseActivity;

/**
 * Created by rain on 2016/9/24.
 */
public class ReleaseActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    Button addbt;
    Spinner spinner;

    private ArrayAdapter<String> adapter;
    private static int  RESULT_LOAD_IMAGE=1;
    private int HAVE_PIVTURE=0;
    private ImageView imageView;
    private SharedPreferences sp;
    private  String picturePath;
    private int top_type=0;
    private List<String> list = new ArrayList<String>();
    private EditText et_biaoti;
    private EditText et_neirong;
    private SharedPreferences sh;
    private Button bt_addpicture;
    private int user_id;
    private int id;
    ProgressDialog proDialog;
    ProgressDialog picProDialog;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           super.handleMessage(msg);
            Bundle bundler=msg.getData();
            String state=bundler.getString("state");
            Log.i("releasetest",""+state);
            if(state.contains("23333")){
                if(HAVE_PIVTURE==0){
                    proDialog.dismiss();
                    finish();
                }else{
                    proDialog.dismiss();
                    Gson gson=new Gson();
                    InsertId insertid=gson.fromJson(state,InsertId.class);
                    int top_id=insertid.getInsertId();
                    String avaurl=((App)getApplication()).getUrl();
                    if(top_type==0){
                        new UploadFile(avaurl+"Controller/receivePicture.php",picturePath,pichandler,id,3,top_id).start();
                    }else if(top_type==1){
                        new UploadFile(avaurl+"Controller/receivePicture.php",picturePath,pichandler,id,4,top_id).start();
                    }else if(top_type==2){
                        new UploadFile(avaurl+"Controller/receivePicture.php",picturePath,pichandler,id,2,top_id).start();
                    }else {
                        new UploadFile(avaurl+"Controller/receivePicture.php",picturePath,pichandler,id,1,top_id).start();
                    }
                    piccreateProgressBar();

                }

            }else{
                Toast.makeText(ReleaseActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private Handler pichandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String test=bundle.toString();
            Log.i("releasetestaa",""+test);
            if(test.contains("23333")){
                picProDialog.dismiss();
                finish();
            }else{
                Toast.makeText(ReleaseActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void piccreateProgressBar() {
        picProDialog = android.app.ProgressDialog.show(ReleaseActivity.this, "请等待", "数据传送中！");
        Thread thread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(10000);
                } catch (InterruptedException e)
                {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                picProDialog.dismiss();//万万不可少这句，否则会程序会卡死。
            }
        };
        thread.start();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        sh=getSharedPreferences("ziliao",MODE_PRIVATE);
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("论坛");
        list.add("老乡");
        list.add("新闻");
        list.add("公告");
        et_biaoti= (EditText) findViewById(R.id.release_biaoti);
        imageView= (ImageView) findViewById(R.id.release_imgview);
        bt_addpicture= (Button) findViewById(R.id.activity_release_picture);
        et_neirong= (EditText) findViewById(R.id.release_neirong);
        addbt= (Button) findViewById(R.id.release_addbt);
        spinner= (Spinner) findViewById(R.id.toolbaoSpinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);//第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //第三步：为适配器设置下拉列表下拉时的菜单样式。
        spinner.setAdapter(adapter);


        bt_addpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);                     //打开图库选择界面
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

                TextView v1 = (TextView) v;
                v1.setTextColor(Color.WHITE);
                Log.i("tiebaliest",""+position);
                top_type=position;
                parent.setVisibility(View.VISIBLE);  /* 将mySpinner 显示*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                arg0.setVisibility(View.VISIBLE);
            }


        });




        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_biaoti.getText().toString().equals("")) {
                    Toast.makeText(ReleaseActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_neirong.getText().toString().equals("")) {
                    Toast.makeText(ReleaseActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                String biaoti = et_biaoti.getText().toString();
                String neirong = et_neirong.getText().toString();
                id = sh.getInt("user_id", 0);
                Map map = new HashMap();

                if(top_type==0){
                    map.put("top_user_id", String.valueOf(id));
                    map.put("top_tit", biaoti);
                    map.put("top_con", neirong);
                    map.put("top_pic", String.valueOf(HAVE_PIVTURE));
                    map.put("method", "addTopic.php");
                }else if(top_type==1){
                    int proid=sh.getInt("user_address_province",1);
                    Log.i("releasetest", "" + String.valueOf(proid) + " -- " + String.valueOf(id));
                    map.put("hometown_user_id", String.valueOf(id));
                    map.put("hometown_title", biaoti);
                    map.put("hometown_content", neirong);
                    map.put("hometown_pro_id",String.valueOf(proid));
                    map.put("hometown_picture_url", String.valueOf(HAVE_PIVTURE));
                    map.put("method", "addHometown.php");
                }else if(top_type==2){
                    map.put("news_user_id", String.valueOf(id));
                    map.put("news_title", biaoti);
                    map.put("news_content", neirong);
                    map.put("news_picture_url", String.valueOf(HAVE_PIVTURE));
                    map.put("method", "addNews.php");
                }else if(top_type==3){
                    map.put("college_notice_user_id", String.valueOf(id));
                    map.put("college_notice_title", biaoti);
                    map.put("college_notice_content", neirong);
                    map.put("college_notice_picture_url", String.valueOf(HAVE_PIVTURE));
                    map.put("method", "addCollegeNotice.php");
                }

                new HttpThreadString(handler, ReleaseActivity.this, map, proDialog).start();
                createProgressBar();
            }



            private void createProgressBar() {
                proDialog = android.app.ProgressDialog.show(ReleaseActivity.this, "请等待", "数据传送中！");
                Thread thread = new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            sleep(5000);
                        } catch (InterruptedException e)
                        {
                            // TODO 自动生成的 catch 块
                            e.printStackTrace();
                        }
                        proDialog.dismiss();//万万不可少这句，否则会程序会卡死。
                    }
                };
                thread.start();

            }






        });



         /*下拉菜单弹出的内容选项触屏事件处理*/
        spinner.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        spinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });



        sp = getSharedPreferences("userinfo", MODE_ENABLE_WRITE_AHEAD_LOGGING);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            HAVE_PIVTURE=1;
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        if (sp.getInt("LEVEL", 1) != 0) {
//            getMenuInflater().inflate(R.menu.menu_send, menu);
//            return  true;
//        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//            case R.id.tieba:
//
//                return true;
//            case R.id.gonggao:
//
//                return true;
//            case R.id.xinwen:
//
//                return true;
       }
        return super.onOptionsItemSelected(item);
    }
}
