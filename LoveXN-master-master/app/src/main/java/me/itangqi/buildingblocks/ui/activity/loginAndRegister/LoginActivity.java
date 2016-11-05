package me.itangqi.buildingblocks.ui.activity.loginAndRegister;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.network.HttpThreadString;
import me.itangqi.buildingblocks.network.clas.Userinfo;
import me.itangqi.buildingblocks.ui.activity.Setting.MainActivity;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_username)
    EditText username;
    @Bind(R.id.et_password)
    EditText userpassword;
    @Bind(R.id.bt_go)
    Button btGo;
    @Bind(R.id.cv)
    CardView cv;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.remember)
    CheckBox remember;
    ProgressDialog proDialog;
    String stUserName;
    String stPassWord;
    Handler handler;
    private TextView tv_losepasswd;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tv_losepasswd= (TextView) findViewById(R.id.tv_losepasswd);
        sp = getSharedPreferences("ziliao", MODE_PRIVATE);//获得实例对象
        handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b=msg.getData();
                String test=b.getString("state");
                Log.i("logintest",""+test);
                if(test.contains("user_id"))
                {
                    proDialog.dismiss();
                    Intent i2 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i2);

                    Gson gson = new Gson();
                    Userinfo userinfo = gson.fromJson(test, Userinfo.class);

                        sp.edit().putString("user_mail", stUserName).commit();
                        sp.edit().putString("user_password", stPassWord).commit();
                        sp.edit().putString("user_name", userinfo.getUser_name()).commit();
                        sp.edit().putString("user_phone", userinfo.getUser_phone()).commit();
                        sp.edit().putInt("user_academy_number", userinfo.getUser_academy_number()).commit();
                        sp.edit().putInt("user_id", userinfo.getUser_id()).commit();
                        sp.edit().putInt("user_address_province", userinfo.getUser_address_province()).commit();
                        sp.edit().putString("user_address_city", userinfo.getUser_address_city()).commit();
                        sp.edit().putString("user_sex", userinfo.getUser_sex()).commit();
                        sp.edit().putInt("user_avatar", userinfo.getUser_avatar()).commit();
                        sp.edit().putInt("user_type", userinfo.getUser_type()).commit();
                        sp.edit().putInt("user_id", userinfo.getUser_id()).commit();


                    finish();

                }else{
                    if(test.contains("100005")){
                        Toast.makeText(LoginActivity.this, "该用户未注册", Toast.LENGTH_SHORT).show();
                        proDialog.dismiss();
                    }else if(test.contains("100006")){
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        proDialog.dismiss();
                    }else {
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        if (sp.getBoolean("ISCHECK", false)) {
            username.setText(sp.getString("USER_NAME", ""));
            userpassword.setText(sp.getString("PASSWORD", ""));

            remember.setChecked(true);
        } else {
            username.setText(sp.getString("USER_NAME", ""));
            userpassword.setText("");
        }

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (remember.isChecked()) {
                    sp.edit().putBoolean("ISCHECK", true).commit();
                } else {
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
            }
        });
    }



    private void TestLogin(String stUserName, String stPassWord) {
        Map map=new HashMap();
        map.put("method","login.php");
        map.put("login_mail", "" + stUserName);
        map.put("login_passwd", "" + stPassWord);
        new HttpThreadString(handler,getApplicationContext(),map,proDialog).start();
        createProgressBar();



    }

    private void createProgressBar() {
        proDialog = android.app.ProgressDialog.show(LoginActivity.this, "请等待", "数据传送中！");
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

    @OnClick({R.id.bt_go, R.id.fab,R.id.tv_losepasswd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                break;
            case R.id.bt_go:
                 stUserName = username.getText().toString();
                 stPassWord = userpassword.getText().toString();
                TestLogin(stUserName, stPassWord);
                break;
            case R.id.tv_losepasswd:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);
                ActivityOptions options1 =ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                startActivity(new Intent(this, LostPasswdActivity.class), options1.toBundle());
                break;


        }
    }
}
