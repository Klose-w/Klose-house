package me.itangqi.buildingblocks.ui.activity.loginAndRegister;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.network.HttpThreadString;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.cv_add)
    CardView cvAdd;
    @Bind(R.id.bt_go)
    Button next;
    EditText et_user;
    EditText et_username;
    EditText et_passwd;
    String st_user;
    String st_username;
    String st_passwd;
    EditText et_repeatpassword;
    EditText et_emailpin;
    String st_repeatpassword;
    String returnString;
    Map map=new HashMap();
    String st_return="服务器错误";
    ProgressDialog proDialog;
    private Button bt_getpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //this.getWindow().setBackgroundDrawable(R.drawable.bg);  
        et_user= (EditText) findViewById(R.id.et_user);
        et_username= (EditText) findViewById(R.id.et_username);
        et_passwd= (EditText) findViewById(R.id.et_password);
        et_emailpin= (EditText) findViewById(R.id.et_emailpin);
        et_repeatpassword= (EditText) findViewById(R.id.et_repeatpassword);
        bt_getpin= (Button) findViewById(R.id.bt_getpin);
        returnString="成功了吗？";
        ButterKnife.bind(this);
        ShowEnterAnimation();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });


         final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                String test=b.getString("state");
                if(test.contains("23333"))
                {
                    proDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    animateRevealClose();
                }
                else if(test.contains("100003")){

                    Toast.makeText(RegisterActivity.this, "用户名已被使用", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else if(test.contains("100013")){
                    Toast.makeText(RegisterActivity.this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }else if(test.contains("100014")){
                    Toast.makeText(RegisterActivity.this, "请获取验证码", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }else{
                    Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    Log.i("ipceshi",""+test);
                }


            }
        };
        final Handler emailhandle=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                String test=b.getString("state");
                Log.i("regesttest",""+test);

                if(test.contains("100010")){
                    Toast.makeText(RegisterActivity.this, "请输入正确邮箱", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }else if(test.contains("100011")){
                    Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }else if(test.contains("100012")){
                    Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }else if(test.contains("23333")){
                    Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                } else{
                    Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    proDialog.dismiss();
                }

            }
        };
        bt_getpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=et_username.getText().toString();
                if(isEmail(email)){
                    Map map=new HashMap();
                    map.put("user_mail",email);
                    map.put("type","0");
                    map.put("method","sendEmail.php");
                    new HttpThreadString(emailhandle,getApplicationContext(),map,proDialog).start();
                    createProgressBar();
                }else {
                    Toast.makeText(RegisterActivity.this, "请输入正确邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_username.length()==0){
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(et_user.length()==0){
                    Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(et_passwd.length()==0){
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_repeatpassword.length()==0){
                    Toast.makeText(RegisterActivity.this, "重复密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(et_repeatpassword.length()<6){
                    Toast.makeText(RegisterActivity.this, "密码不小于6位", Toast.LENGTH_SHORT).show();
                    return;
                }else if(et_repeatpassword.length()>18){
                    Toast.makeText(RegisterActivity.this, "密码不大于18位", Toast.LENGTH_SHORT).show();
                    return;
                }

                st_username=et_username.getText().toString();
                st_user=et_user.getText().toString();
                st_passwd=et_passwd.getText().toString();
                String pin=et_emailpin.getText().toString();
                st_repeatpassword=et_repeatpassword.getText().toString();
                if(!st_passwd.equals(st_repeatpassword)){
                    Toast.makeText(RegisterActivity.this, "2次输入密码不同", Toast.LENGTH_SHORT).show();
                }


                map.put("reg_mail",st_username);
                map.put("reg_passwd", st_passwd);
                map.put("reg_name", st_user);
                map.put("code",pin);
                map.put("method", "register.php");
                new HttpThreadString(handler,getApplicationContext(),map,proDialog).start();

                createProgressBar();
            }
        });
    }

    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private void createProgressBar() {
        proDialog = android.app.ProgressDialog.show(RegisterActivity.this, "请等待", "数据传送中！");
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

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
}
