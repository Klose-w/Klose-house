package me.itangqi.buildingblocks.ui.activity.Setting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.network.HttpThreadString;
import me.itangqi.buildingblocks.network.clas.Userinfo;

public class ZiliaoActivity extends Activity {
    private Spinner wcol;  //学院spinner
    private Spinner wcla;      //班级spinner
    private Spinner wprov;
    private Spinner whome;
    private int pro_id;
    private int C_id;
    ImageButton back = null;
    Button bn = null;
    EditText wname;
    TextView wnum;
    Spinner wsex;
    EditText wphone;
    Spinner wage;


    EditText wzil;
    private String[] age={"14","15","16","17","18","19","20","21","22",
            "23","24","25","26","27","28","29"};
    private String[] pro={"北京","天津","上海","重庆","河北省","山西省","辽宁省","吉林省","黑龙江",
            "江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省",
            "海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古","广西",
            "西藏","宁夏","新疆","香港","澳门"};
    private String[][] city={
            {"北京市"},
            {"天津市"},
            {"上海市"},
            {"重庆市"},
            {"石家庄","唐山","秦皇岛","邯郸","邢台","保定","张家口","承德","沧州","廊坊","衡水"},
            {"太原","大同","阳泉","长治","晋城","朔州","晋中","运城","忻州","临汾","吕梁"},
            {"沈阳","大连","鞍山","抚顺","本溪","丹东","锦州","营口","阜新","辽阳","盘锦","铁岭","朝阳","葫芦岛"},
            {"长春","吉林","四平","辽源","通化","白山","松原","白城","延边"},
            {"哈尔滨","齐齐哈尔","鸡西","鹤岗","双鸭山","大庆","伊春","佳木斯","七台河","牡丹江","黑河","绥化","大兴安岭"},
            {"南京","无锡","徐州","常州","苏州","南通","连云港","淮安","盐城","扬州","镇江","泰州","宿迁"},
            {"杭州","宁波","温州","嘉兴","湖州","绍兴","金华","衢州","舟山","台州","丽水"},
            {"合肥","芜湖","蚌埠","淮南","马鞍山","淮北","铜陵","安庆","黄山","滁州","阜阳" ,"宿州","巢湖","六安","亳州","池州","宣城"},
            {"福州","厦门","莆田","三明","泉州","漳州","南平","龙岩","宁德"},
            {"南昌","景德镇","萍乡","九江","新余","鹰潭","赣州","吉安","宜春","抚州","上饶"},
            {"济南","青岛","淄博","枣庄","东营","烟台","潍坊","威海","济宁","泰安","日照","莱芜","临沂","德州","聊城","滨州","菏泽"},
            {"郑州","开封","洛阳","平顶山","焦作","鹤壁","新乡","安阳","濮阳","许昌","漯河","三门峡","南阳","商丘","信阳","周口","驻马店"},
            {"武汉","黄石","襄樊","十堰","荆州","宜昌","荆门","鄂州","孝感","黄冈","咸宁","随州","恩施"},
            {"长沙","株洲","湘潭","衡阳","邵阳","岳阳","常德","张家界","益阳","郴州","永州","怀化","娄底","湘西"},
            {"广州","深圳","珠海","汕头","韶关","佛山","江门","湛江","茂名","肇庆","惠州","梅州","汕尾","河源","阳江","清远","东莞","中山","潮州","揭阳","云浮"},
            {"海口","三亚"},
            {"成都","自贡","攀枝花","泸州","德阳","绵阳","广元","遂宁","内江","乐山","南充","宜宾","广安","达州","眉山","雅安","巴中","资阳","阿坝","甘孜 ","凉山"},
            {"贵阳","六盘水","遵义","安顺","铜仁","毕节","黔西南","黔东南","黔南"},
            {"昆明","曲靖","玉溪","保山","昭通","丽江","普洱","临沧","文山","红河","西双版纳","楚雄","大理","德宏","怒江","迪庆"},
            {"西安","铜川","宝鸡","咸阳","渭南","延安","汉中","榆林","安康","商洛"},
            {"兰州","嘉峪关","金昌","白银","天水","武威","张掖","平凉","酒泉","庆阳","定西","陇南","临夏","甘南"},
            {"西宁","海东","海北","黄南","海南","果洛","玉树","海西"},
            {"台北","高雄","基隆","台中","台南","新竹","嘉义"},
            {"呼和浩特","包头","乌海","赤峰","通辽","鄂尔多斯","呼伦贝尔","巴彦淖尔","乌兰察布","兴安","锡林郭勒","阿拉善"},
            {"南宁","柳州","桂林","梧州","北海","防城港","钦州","贵港","玉林","百色","贺州","河池","来宾","崇左"},
            {"拉萨","昌都","山南","日喀则","那曲","阿里","林芝"},
            {"银川","石嘴山","吴忠","固原","中卫"},
            {"乌鲁木齐","克拉玛依","吐鲁番","哈密","和田","阿克苏","喀什","克孜勒苏柯尔克孜","巴音郭楞蒙古","昌吉","博尔塔拉蒙古","伊犁哈萨克","塔城","阿勒泰"},
            {"香港"},
            {"澳门"}
    };
    private String[] col={"农学系","植物保护学院","园艺学院","动物科技学院","动物医学","林学院","风景园林艺术学院","资源环境学院",
            "水利与建筑工程学院","机械与电子工程学院","信息工程学院","食品科学与工程学院","葡萄酒学院","生命科学学院",
            "理学院","经济管理学院","人文社会发展学院","外语系","创新学院"};
    private String[][] cla={
            {"农学系","植物科学","种子科学","未知"},
            {"园艺","设施农业科学","未知"},
            {"植物保护","制药工程","未知"},
            {"动物科学","草业科学","水产养殖学","未知"},
            {"动物医学"},
            {"林学","森林保护","林产化工","木材科学与工程","未知"},
            {"风景园林","园林","环境设计","地理信息科学","人文地理与城乡规划","水土保持与荒漠化防治","未知"},
            {"资源环境科","环境科学","环境工程","地理信息科学","人文地理与城乡规划","水土保持与荒漠化防治","未知"},
            {"农业水利工程","水文与水资源工程","水利水电工程","土木工程","能源与动力工程","电气工程及其自动化","未知"},
            {"机械设计制造及其自动化","农业机械化及其自动化","机械电子工程","电子信息工程","车辆工程","未知"},
            {"计算机科学与技术","软件工程","信息管理","电子商务","未知"},
            {"食品科学与工程","食品质量与安全","未知"},
            {"葡萄酒"},
            {"生物科学","生物技术","生物工程","未知"},
            {"应用化学","信息与计算科学","未知"},
            {"会计学","金融学","保险学","旅游管理","市场营销","国际经济与贸易","工商管理","土地资源","农林经济","经济学","未知"},
            {"法学","社会学","公共事业管理","社会工作","未知"},
            {"外语系"},
            {"生物技术基地班", "生物工程基地班", "创新实验班","未知"},
    };
    private String[] sex={"男","女"};
    private Toolbar toolbar;
    SharedPreferences sh;
    SharedPreferences sb;
    int user_avatar;
    private Animation change;
    private Animation change1;
    private Animation change2;
    ImageView iv;
    me.itangqi.buildingblocks.widget.CircleImageView circleImageView;
    LinearLayout myscrollLinearlayout;
    LinearLayout mainheadview; //顶部个人资料视图
    RelativeLayout mainactionbar; //顶部菜单栏
    RelativeLayout rela;
    LinearLayout backview;
    private TextView txname;
    private TextView txcity;
    private TextView txsex;
    private TextView txqianming;

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
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle b = msg.getData();
            String test=b.getString("state");

            if(test.contains("2333"))
            {
                proDialog.dismiss();
                Toast.makeText(ZiliaoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Log.i("ziliao",""+test);
                Toast.makeText(ZiliaoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();

            }
        }
    };
    ProgressDialog proDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziliao);
        WindowManager wm = this.getWindowManager();
        Toast.makeText(ZiliaoActivity.this, "向上滑动修改资料", Toast.LENGTH_SHORT).show();

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        sh=this.getSharedPreferences("ziliao", MODE_PRIVATE);
        bn=(Button)findViewById(R.id.xiugai);
        user_avatar=sh.getInt("user_avatar", 2);

        txname= (TextView) findViewById(R.id.textView);
        txcity= (TextView) findViewById(R.id.textView6);
        txsex= (TextView) findViewById(R.id.textView7);
        iv=(ImageView)findViewById(R.id.backbf);
        AnimationDrawable ad = (AnimationDrawable) getResources().getDrawable(R.anim.backstory1);
        iv.setBackgroundDrawable(ad);
        ad.start();

        //Object ob=iv.getBackground();
        //anim= (AnimationDrawable) ob;
        //anim.start();
        change1= AnimationUtils.loadAnimation(this,R.anim.sitechange);
        change2=AnimationUtils.loadAnimation(this,R.anim.sitechange1);
        change=AnimationUtils.loadAnimation(this,R.anim.sitechange2);

        txname.startAnimation(change);
        txcity.startAnimation(change1);
        txsex.startAnimation(change2);

        txqianming= (TextView) findViewById(R.id.textView8);

        wname=(EditText)findViewById(R.id.Textname);
        wnum=(TextView)findViewById(R.id.Textnum);
        wsex=(Spinner)findViewById(R.id.Textsex);
        wphone=(EditText)findViewById(R.id.Textphone);
        wage=(Spinner)findViewById(R.id.Textage);
        wzil=(EditText)findViewById(R.id.Textzil);
        wcol=(Spinner)findViewById(R.id.zcol);
        wcla=(Spinner)findViewById(R.id.zcla);
        whome=(Spinner)findViewById(R.id.zhome);
        wprov=(Spinner)findViewById(R.id.zpro);

    //初始化
        wname.setText(sh.getString("user_name", ""));
        wnum.setText(sh.getString("user_mail", ""));
        wphone.setText(sh.getString("user_phone", ""));
        wzil.setText(sh.getString("user_shuo", ""));

        txname.setText(sh.getString("user_name", ""));
        txcity.setText(pro[sh.getInt("user_address_province",1)]);
        txqianming.setText(sh.getString("user_shuo", "此生已入江湖，莫回顾，君行君去处"));
        if(sh.getString("user_sex","1").equals("1"))
        {
            txsex.setText("女");
        }
        else{
            txsex.setText("男");
        }

        Log.i("ziliaoshezhi", "" + sh.getInt("user_age", 99));



        final ListView listView = new ListView(ZiliaoActivity.this);
        listView.post(new Runnable() {
            @Override
            public void run() {
                wage.requestFocusFromTouch();//获取焦点

                wage.setSelection(sh.getInt("user_age", 2), true);//10是你需要定位的位置
                wsex.requestFocusFromTouch();
                wsex.setSelection(Integer.valueOf(sh.getString("user_sex", "0")), true);
                wcla.requestFocusFromTouch();
                wcla.setSelection(sh.getInt("user_major", 0), true);
                wcol.requestFocusFromTouch();
                wcol.setSelection(sh.getInt("user_academy_number", 0), true);
                whome.requestFocusFromTouch();
                whome.setSelection(sh.getInt("user_city", 0), true);
                wprov.requestFocusFromTouch();
                wprov.setSelection(sh.getInt("user_address_province", 0), true);

            }
        });


        //toolbar = (Toolbar)findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        circleImageView=(me.itangqi.buildingblocks.widget.CircleImageView)findViewById(R.id.iV);
        circleImageView.setImageResource(avatar[user_avatar]);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ZiliaoActivity.this, TouxActivity.class);
                startActivity(intent);
            }
        });


        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sh.edit();
                editor.putString("user_name", wname.getText().toString());
                editor.putString("user_phone", wphone.getText().toString());
                editor.putInt("user_age", wage.getSelectedItemPosition());
                editor.putInt("user_major", wcla.getSelectedItemPosition());
                editor.putInt("user_city", whome.getSelectedItemPosition());
                editor.putString("user_sex", String.valueOf(wsex.getSelectedItemPosition()));
                editor.putInt("user_academy_number", wcol.getSelectedItemPosition());
                editor.putInt("user_address_province", wprov.getSelectedItemPosition());
                editor.putString("user_shuo", wzil.getText().toString());
                editor.commit();


                Userinfo userinfo;
                Map map =new HashMap();
                map.put("method","updateUser.php");
                map.put("user_id",String.valueOf(sh.getInt("user_id",1)));
                map.put("user_name",wname.getText().toString());
                map.put("user_phone",wphone.getText().toString());
                map.put("user_academy_number",String.valueOf(wcol.getSelectedItemPosition()));
                map.put("user_address_province",String.valueOf(wprov.getSelectedItemPosition()));
                map.put("user_address_city",String.valueOf(whome.getSelectedItemPosition()));
                map.put("user_sex",String.valueOf(wsex.getSelectedItemPosition()));
                map.put("user_avatar",String.valueOf(sh.getInt("user_avatar", 2)));

                new HttpThreadString(handler,ZiliaoActivity.this,map,proDialog).start();
                createProgressBar();
            }
            private void createProgressBar() {
                proDialog = android.app.ProgressDialog.show(ZiliaoActivity.this, "请等待", "数据传送中！");
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
        ArrayAdapter<String> age_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,age);
        age_adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        wage.setAdapter(age_adapter);
        ArrayAdapter<String> pro_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,pro);
        pro_adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        wprov.setAdapter(pro_adapter);
        wprov.setOnItemSelectedListener(new SelectProvince());
        ArrayAdapter<String> col_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,col);
        col_adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        wcol.setAdapter(col_adapter);
        wcol.setOnItemSelectedListener(new Selectcollage());

    }
    int width;
    int height;
//    int Y;
//    int position = 0; //拖动Linearlayout的距离Y轴的距离
//    int scrollviewdistancetotop = 0; //headView的高
//    int menubarHeight = 0;
//    int chufaHeight = 0; //需要触发动画的高
//    float scale; //像素密度
//    int headViewPosition = 0;
//    ImageView userinfo_topbar;
    static boolean flag = true;
    static boolean topmenuflag = true;

    @Override
    protected void onResume() {
        super.onResume();

        user_avatar=sh.getInt("user_avatar",3);
        circleImageView.setImageResource(avatar[user_avatar]);

    }



    class SelectProvince implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            // TODO Auto-generated method stub
            //获得省份ID
            pro_id=position;
            whome.setAdapter(getAdapter2());

        }
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    }
    class Selectcollage implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            // TODO Auto-generated method stub
            //获得省份ID
            C_id=position;
            wcla.setAdapter(getAdapter());

        }
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    }
    public ArrayAdapter<String> getAdapter2(){
        int length=city[pro_id].length;
        String[] a=new String[length];
        for(int i=0;i<length;i++){
            a[i]=city[pro_id][i];
        }
        ArrayAdapter<String> adapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,a);
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_item);
        return adapter1;
    }
    public ArrayAdapter<String> getAdapter(){
        int length=cla[C_id].length;
        String[] a=new String[length];
        for(int i=0;i<length;i++){
            a[i]=cla[C_id][i];
        }
        ArrayAdapter<String> adapter1=new ArrayAdapter(this, android.R.layout.simple_spinner_item,a);
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_item);
        return adapter1;
    }
}
