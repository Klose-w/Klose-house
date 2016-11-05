package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.ui.activity.DialogActivity.Fbdialog;

public class Schoolplace extends TabActivity {


    Button fb;
    int[] zhuangtai={0,1,2,3};
    ListView lvplay;
    String[] pname = {"王志豪", "张三", "孙仲谋", "吴承恩", "唐三藏"};
    String[] pftime = {"9月22日 13:25", "9月22日 23:25", "9月23日 09:23", "9月23日 13:40", "9月24日 11:05"};
    String[] playname = {"习近平主席西农报告会", "社团换届大会", "新生才艺大赛", "名家讲座中心之我心中的九一八", "葡萄酒晚会加交谊舞"};
    String[] playtime = {"9月25日晚7点-9点", "9月26日晚7点-9点", "9月27日晚7点-9点", "9月27日晚7点-9点", "9月28日晚7点-9点"};
    String[] pla = {"北秀大厅", "北秀206", "北秀大厅", "北秀南报告厅", "北秀大厅"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolplace);
        //lvplay = (ListView) findViewById(R.id.lvplay);
        TabHost tabHost = getTabHost();
        fb=(Button)findViewById(R.id.fabu);
//        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
//        for (int i = 0; i < pname.length; i++) {
//            Map<String, Object> listItem = new HashMap<String, Object>();
//            listItem.put("pname", pname[i]);
//            listItem.put("pftime", pftime[i]);
//            listItem.put("playname", playname[i]);
//            listItem.put("playtime", playtime[i]);
//            listItem.put("pla", pla[i]);
//            listItems.add(listItem);
//        }
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Fbdialog diglog=new Fbdialog(Schoolplace.this);
//                diglog.setdjListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(Schoolplace.this,diglog.Playn(),Toast.LENGTH_LONG).show();
//                    }
//                });
                diglog.show();

            }
        });
        Intent intent = new Intent(getApplicationContext(),Playlist_Activity.class);
        tabHost.addTab(tabHost.newTabSpec("one").setIndicator("活动").setContent(intent));
        tabHost.addTab(tabHost.newTabSpec("two").setIndicator("吐槽").setContent(R.id.talk));
        tabHost.addTab(tabHost.newTabSpec("three").setIndicator("简介").setContent(R.id.intro));
        TabWidget tabWidget = tabHost.getTabWidget();

//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.play,
//                new String[]{"pname", "pftime", "playname", "playtime", "pla"},
//                new int[]{R.id.pname, R.id.pftime, R.id.playname, R.id.playtime, R.id.pla});
//        lvplay.setAdapter(simpleAdapter);
//        l



    }

    private void dianzan()
    {
        for(int i=0;i<5;i++)
        {

        }
    }
//    private class ButtonAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return 5;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            final int pos = position;
//            View view = convertView;
//            if (view == null) {
//                LayoutInflater inflater = LayoutInflater.from(Schoolplace.this);
//                view = inflater.inflate(R.layout.play, null);
//            }
//            final ImageButton zan = (ImageButton) view.findViewById(R.id.zan);
//            final ImageButton dzan = (ImageButton) view.findViewById(R.id.dzan);
//            final ImageButton wen = (ImageButton) view.findViewById(R.id.wen);
//            int i = 0;
//            //赞
//            zan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    zan.setBackgroundResource(R.drawable.zan1);
//                }
//            });
//            //不赞
//            dzan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dzan.setBackgroundResource(R.drawable.dzan1);
//                }
//            });
//            //问好
//            wen.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    wen.setBackgroundResource(R.drawable.wen1);
//                }
//            });
//            return view;
//        }
//    }
}
