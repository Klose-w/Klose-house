package me.itangqi.buildingblocks.ui.activity.Setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.v5kf.client.lib.V5ClientAgent;
import com.v5kf.client.lib.V5ClientConfig;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.application.App;
import me.itangqi.buildingblocks.ui.activity.base.BaseActivity;
import me.itangqi.buildingblocks.ui.activity.loginAndRegister.LoginActivity;
import me.itangqi.buildingblocks.ui.fragment.ElseFragment;
import me.itangqi.buildingblocks.ui.fragment.LaoxiangFragment;
import me.itangqi.buildingblocks.ui.fragment.NewsListFragment;
import me.itangqi.buildingblocks.ui.fragment.NoticeListFragment;
import me.itangqi.buildingblocks.ui.fragment.TiebaListFragment;
import me.itangqi.buildingblocks.utils.Constants;
import me.itangqi.buildingblocks.widget.CircleImageView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.coordinatorLayout) CoordinatorLayout mContainer;
    @Bind(R.id.navigation_view) NavigationView mNavigationView;
    @Bind(R.id.tabs) TabLayout mTabLayout;
    @Bind(R.id.pager) ViewPager mViewPager;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.fab_add_comment)FloatingActionButton fab;
    private CircleImageView bt2;
    SharedPreferences sh;
    V5ClientConfig config;
    private CircleImageView profile_image;
    private TextView tvname;
    private TextView tvemail;
    int [] avatar=new int[]{
            R.drawable.toux,
            R.drawable.toux2,
            R.drawable.toux3,
            R.drawable.toux4,
            R.drawable.toux5,
            R.drawable.toux6,
            R.drawable.toux7,
            R.drawable.toux8,
            R.drawable.toux9,
            R.drawable.toux10,
            R.drawable.toux11,
            R.drawable.toux12,
            R.drawable.toux13,
            R.drawable.toux14,
            R.drawable.toux15,
            R.drawable.toux16
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutResID = R.layout.activity_main;
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        profile_image = (CircleImageView) findViewById(R.id.ccc);

        sh = getSharedPreferences("ziliao", MODE_PRIVATE);

        //initinfo();
        setSupportActionBar(mToolbar);
        /*bt2=(CircleImageView)findViewById(R.id.profile_image);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ZiliaoActivity.class);
                startActivity(intent);
            }
        });*/
        //判断网络连接
//        if (!NetworkUtils.isNetworkConnected(this)) {
//            Snackbar.make(mContainer, R.string.snack_network_error, Snackbar.LENGTH_LONG).show();
//        }
        initV5Chat();
        if (mNavigationView != null) {
            setupDrawerContent();
        }

        setupActionBarToggle();

        if (mViewPager != null) {
            setupViewPager();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发帖
                Intent intent = new Intent(MainActivity.this, ReleaseActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initinfo() {
        int user_avatar=sh.getInt("user_avatar", 1);
        profile_image.setImageResource(avatar[user_avatar]);
    }


    private void initV5Chat() {
        // V5客服系统客户端配置
        config = V5ClientConfig.getInstance(MainActivity.this);
        V5ClientConfig.USE_HTTPS = true; // 使用加密连接，默认true
        V5ClientConfig.SOCKET_TIMEOUT = 20000; // 请求超时时间
        config.setShowLog(true); // 是否打印日志， 默认为true
        config.setHeartBeatEnable(true); // 是否允许发送心跳包保活
        config.setHeartBeatTime(30000); // 心跳包间隔时间ms
        config.setLogLevel(V5ClientConfig.LOG_LV_DEBUG); // 日志级别默认为全部显示

        config.setUid(String.valueOf(sh.getInt("user_id", 1))); // 【必须】 ,设置用户ID，区分APP登录的不同账号
        config.setNickname(sh.getString("user_name", "")); // 设置用户昵称
        config.setGender(1); // 设置用户性别
        //config.setAvatar("http://debugimg-10013434.image.myqcloud.com/fe1382d100019cfb572b1934af3d2c04/thumbnail"); // 设置用户头像URL
//        String avaurl=sh.getString("loveXNurl","aaa");
//        String username=sh.getString("user_name","");
//        avaurl+="src/avatar/";
        String avaurl=((App)getApplication()).getUrl();
        config.setAvatar(avaurl + "src/avatar/" + String.valueOf(sh.getInt("user_avatar", 2) + 1) + ".jpg"); // 设置用户头像URL
      //  Log.i("mainactivity", "" + "http://172.17.66.131/love_nwsuaf/src/avatar/"  + String.valueOf(sh.getInt("user_avatar", 2) + 1) + ".jpg");
//        Log.i("mainactivity",""+username);
        config.setDefaultServiceByWorker(false); // 是否默认转人工客服
        config.setVip(0);
    }


        //侧滑其他设置选项连接
    private void setupDrawerContent() {
        //选中监听
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (menuItem.isChecked()) menuItem.setChecked(false);
                        else menuItem.setChecked(true);
                        //选中后关闭侧滑界面
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_settings:
                                return prepareIntent(PrefsActivity.class);
                            case R.id.nav_about:
                                return prepareIntent(AboutActivity.class);
                            case  R.id.nav_ziliao:
                                return prepareIntent(ZiliaoActivity.class);
                            case  R.id.nav_kefu:
                                String avaurl=((App)getApplication()).getUrl();
                                config.setAvatar(avaurl+"src/avatar/" + String.valueOf(sh.getInt("user_avatar",2)+1) + ".jpg"); // 设置用户头像URL
                                V5ClientAgent.getInstance().startV5ChatActivity(getApplicationContext());
                                return true;

                            default:
                                return true;
                        }
                    }
                });
    }

    //侧滑菜单
    private void setupActionBarToggle() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawerContentDescRes, R.string.closeDrawerContentDescRes) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setupViewPager() {
        mViewPager.setOffscreenPageLimit(Constants.PAGE_COUNT);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    //快捷设置
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //操作选项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.men_action_refresh:
                Snackbar.make(mContainer, R.string.snack_rest_over_to_you, Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.men_action_read_mode:
                Snackbar.make(mContainer, R.string.snack_rest_over_to_you, Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.menu_action_feedback:
                Logout();
                return true;
            case  R.id.menu_action_send:
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    //注销
    private void Logout() {
       SharedPreferences.Editor editer = sh.edit();
        editer.putBoolean("AUTO_ISCHECK",false);//忘记密码
        editer.putString("PASSWORD","");
        editer.commit();

        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private boolean prepareIntent(Class clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
        return true;
    }

    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Snackbar.make(mContainer, R.string.snack_exit_once_more, Snackbar.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                sh.edit().putBoolean("CONNECT",false).commit();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //标签下对应的项目
        @Override
        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();
            Fragment newFragment = null;

            switch (i)
            {
                case 0:
                    newFragment = new NoticeListFragment();
                    break;
                case 1:
                    newFragment = new NewsListFragment();
                    break;
                case 2:
                    newFragment = new TiebaListFragment();
                    break;
                case 3:
                    newFragment = new LaoxiangFragment();
                    break;
                case 4:
                    newFragment = new ElseFragment();
                    break;
                default:
                    break;
            }
            return newFragment;
        }

        //设置标签数量
        @Override
        public int getCount() {
            return Constants.PAGE_COUNT;
        }

        //得到标签的标题
        @Override
        public CharSequence getPageTitle(int position) {
            String titles[] = new String[]{"    公告    ", "    新闻    ", "    论坛    ",
                    "    老乡    ", "    其他    "};
            return titles[position];
        }
    }
}
