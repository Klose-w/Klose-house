package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import me.itangqi.buildingblocks.R;

public class Lose_Get extends TabActivity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper mSwipeBackLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose__get);
        TabHost tabHost=getTabHost();
        TabWidget tabWidget=tabHost.getTabWidget();
        Intent intent = new Intent(getApplicationContext(),Lostthing.class);
        Intent intent1 = new Intent(getApplicationContext(),Getthing.class);
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("我要寻物").setContent(intent));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("我要招领").setContent(intent1));
        for (int i =0; i < tabWidget.getChildCount(); i++) {
            //修改Tabhost高度和宽度
            tabWidget.getChildAt(i).getLayoutParams().height = 100;
            //tabWidget.getChildAt(i).getLayoutParams().width = 65;
            //修改显示字体大小
            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(18);
            tv.setTextColor(this.getResources().getColorStateList(android.R.color.holo_blue_light));
        }
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("失物招领");
        //setSupportActionBar(toolbar);
        mSwipeBackLayout = new SwipeBackActivityHelper(this);
        mSwipeBackLayout.onActivityCreate();

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
