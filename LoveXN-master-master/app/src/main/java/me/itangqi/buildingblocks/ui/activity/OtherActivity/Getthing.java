package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.ui.activity.DialogActivity.ImageShower;
import me.itangqi.buildingblocks.widget.RefreshListView;

public class Getthing extends AppCompatActivity {


    RefreshListView swlistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getthing);
        swlistview = (RefreshListView ) findViewById(R.id.getviewsw);
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <Losething.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("thing", Losething[i]);
            listItem.put("jilu", Jilu[i]);
            listItem.put("intro", Lostintro[i]);
            listItem.put("Time", Losetime[i]);
            listItem.put("Place", Loseplace[i]);
            listItems.add(listItem);

        }
        SimpleAdapter simpleAdapter =new SimpleAdapter(this,listItems,R.layout.getlist,
                new String[]{"thing","jilu","intro","Time","Place"},
                new int[]{R.id.losething,R.id.losestate,R.id.loseintro,R.id.losetime,R.id.losespace});
        swlistview.setOnRefreshListener(new RefreshListView.RefreshListener() {
            @Override
            public Object refreshing() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void refreshed(Object obj) {

            }

            @Override
            public void more() {

            }
        });

        swlistview.setAdapter(simpleAdapter);
        swlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder diglog=new AlertDialog.Builder(Getthing.this);

                View view1= LayoutInflater.from(Getthing.this).inflate(R.layout.activity_lose_dialog,null);
                final TextView pn=(TextView)view1.findViewById(R.id.losephone);
                final ImageButton pt=(ImageButton)view1.findViewById(R.id.loseimage);
                final Button sit=(Button)view1.findViewById(R.id.site);
                pn.setText(arrnum1[position]);
                pt.setBackgroundResource(Images[position]);
                sit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "当前坐标为（108，40）", Toast.LENGTH_LONG);
                    }
                });
                pt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Getthing.this, ImageShower.class);
                        intent.putExtra("ima",Images[position]);

                        startActivity(intent);
                    }
                });
                diglog.setView(view1);
                final AlertDialog di=diglog.show();
            }
        });

    }
    private int[] Images=new int[]
            {
                    R.drawable.sthongfeng,
                    R.drawable.stqingnian,
                    R.drawable.stchuantong,
                    R.drawable.stkuaiji,
                    R.drawable.stqiushi,
                    R.drawable.stfaxue,
                    R.drawable.stwangq,
                    R.drawable.styumao


            };
    private String[] arrnum1 = {"15892878738", "18392412561", "546512258", "455451214","15892878738", "18392412561",
            "546512258", "455451214"};
    private int wz;
    private String[] Losething={
            "校园卡", "钱包", "身份证", " 银行卡"," U盘"," 钥匙"," 书包"," 校园卡"};
    private String[] Jilu=new String[]
            {
                    "未领取",
                    "未领取",
                    "已领取",
                    "未领取",
                    "已领取",
                    "未领取",
                    "未领取",
                    "未领取"

            };
    private String[] Lostintro=new String[]
            {
                    "本人今晚（3月14日晚9点—10点）与朋友在学校操场锻炼时，于操场双杠处丢失一女式单肩挎包，此包一面为镶满鱼鳞式的小圆晶片，在光照耀下比较闪，另一面为黑色；包内含有校园卡、宿舍钥匙及家中钥匙、笔袋（笔袋内有身份证、医保卡及优盘等物品），包内还有其他贵重物品",
                    "这次该我丢东西了，一把钥匙应该有四个钥匙，有一个钥匙头只有一半了。关键是还有个蓝色的魔霸优盘，里面有很多重要的东西。",
                    "各位童鞋帮帮忙啊~~~~~昨晚去3号楼303教室完了之后钥匙不见了！！！！！！路线是女生宿舍11号楼到3号楼这一段路，有一个金属环的钥匙链，有看见或拾到的童鞋麻烦给我打个电话，15109265672。。谢谢哈",
                    "本人最近两天丢失了身份证（证件号码：6127251994xxxx161X），有拾到者请联系18709236299（王伟），有重谢！！",
                    "本人今晚（3月14日晚9点—10点）与朋友在学校操场锻炼时，于操场双杠处丢失一女式单肩挎包，此包一面为镶满鱼鳞式的小圆晶片，在光照耀下比较闪，另一面为黑色；包内含有校园卡、宿舍钥匙及家中钥匙、笔袋（笔袋内有身份证、医保卡及优盘等物品），包内还有其他贵重物品",
                    "这次该我丢东西了，一把钥匙应该有四个钥匙，有一个钥匙头只有一半了。关键是还有个蓝色的魔霸优盘，里面有很多重要的东西。",
                    "各位童鞋帮帮忙啊~~~~~昨晚去3号楼303教室完了之后钥匙不见了！！！！！！路线是女生宿舍11号楼到3号楼这一段路，有一个金属环的钥匙链，有看见或拾到的童鞋麻烦给我打个电话，15109265672。。谢谢哈",
                    "本人最近两天丢失了身份证（证件号码：6127251994xxxx161X），有拾到者请联系18709236299（王伟），有重谢！！"
            };
    private String[] Losetime=new String[]
            {
                    "10月21日晚十点",
                    "周三(5.11)",
                    "2016年",
                    "10月21日晚十点",
                    "周三(5.11)",
                    "2016年",
                    "10月21日晚十点",
                    "周三(5.11)"
            };
    private String[] Loseplace=new String[]
            {
                    "北校田径场",
                    "八号楼",
                    "宿舍11号楼",
                    "北校田径场",
                    "八号楼",
                    "宿舍11号楼",
                    "北校田径场",
                    "八号楼"
            };
}

