package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import me.itangqi.buildingblocks.R;

public class Com_Activity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;
    private Spinner spinner;
    private String[] arrnum1 = {"15892878738", "18392412561", "546512258", "455451214","15892878738", "18392412561",
            "546512258", "455451214","15892878738", "18392412561", "546512258", "455451214","455451214"};
    private int wz;
    private String[] com_array={
    "筛选", "社会公益类", "教育科学类", " 理论学习类"," 体育类"," 综艺类"," 文学类"," 其他"};
    private String[] names=new String[]
            {
                    "红风社",
                    "青年志愿者",
    "传统文化",
    "会计",
    "求是学社",
    "法学社",
    "网球协会",
    "羽毛球协会",
    "酷玩吉他社",
   "琴箫口琴社",
    "历史协会",
    "女子足球爱好者协会",
   "相声小品协会"

            };
    private String[] headers=new String[]
            {
                    "西北农林科技大学红凤社成立于2006年3月，是受校党委领导，在学校团委指导下的大学生社团。社团的成立缘起陕西省妇女联合会公益项目“红凤工程”，是受红凤工程资助的在校大学生发起成立的。目前在陕西诸高校都成立有红凤社。",
                    "西北农林科技大学青年志愿者协会成立于2005年9月23日。自成立之日起，协会以“奉献、友爱、互助、进步”为宗旨，以“每个人都有一个非营利的梦想”为口号，先后策划组织了各类公益活动。经过10年的发展，协会现有“牵守”帮扶留守儿童系列，“青春点亮夕阳”关爱老教授系列，“爱溢福利院”福利院送温暖系列等精品活动。兼有公益论坛交流、农高会志愿者、校运会裁判员助理、清明扫墓、文艺下乡、志愿精神内部培训、推广宣传等活动。2012年9月，协会提出“服务大学生”的精品活动项目，旨在为身边的大学生提供志愿服务。",
                    "西北农林科技大学传统文化学社，创建于2005年4月6日，原名“竹士社”，于2012年4月更名.社团坚持以“承先贤之至善，复汉唐之盛文”为宗旨，以“知行合一，道术兼备”为社训，为热爱传统文化的同学提供交流学习的平台，促进社员的知识储备和思想认识的提升；并在校园内开展丰富多彩的传统文化活动，推进全校师生对传统文化的学习理解。 多年来，社团的传统活动有：品国学经典，养浩然正气——经典晨读；智慧思想融合——传统文化知识竞赛；以文会友，以友辅仁——读书会；冠笄彰志，仪礼明德——汉服成人礼；追忆先贤，纪念先师——孔子诞辰释奠礼。",
                    "会计协会成立于2007年，协会宗旨在于传播会计知识，繁荣校园文化，协会基本职能包括举办各种会计类知识报告会讲座和会计类学术竞赛活动丰富学生的会计素养，帮助财会专业学生提高其专业素养，非财会专业学生了解会计；同时致力于帮助学生考取会计证件的服务，是一个专业性极强的科学教育类社团。会计协会是一个学生的天地，具有丰富的资源，是联系会计兴趣者的桥梁和纽带。协会秉承宗旨，明确“普及会计教育知识”的使命，目标是使其得到长期发展，不断创新，努力成为独具特色，服务学生的社团。为学子们提供一个更广泛的会计交流平台。",
                    "本社团为西北农林科技大学大学生社团联合会成员之一，接受西北农林科技大学党委的领导，接受西北农林科技大学团委的管理及指导。利用学生团体的优势，通过对毛泽东思想和中国特色社会主义理论的学习，进一步促进我校理论学习风气。同时，以提高学生的思想高度，培养学生的爱国情怀，拓展学生的综合素质为己任，为学校搭建培养人才的良好平台。社团成立于2014年10月，由主要学生干部及学生党员构成，思政部老师指导，在成立之初举办的“我为核心价值观代言”活动引发了全体师生的广泛讨论，随后积极发展社会主义核心价值观宣讲系列活动。并于今年9月代表学校参加了井冈情·中国梦暑期社会实践活动，获得优秀代表团队称号，属于西北农林科技大学学生社团联合会的品牌社团",
                    "法学社成立于2004年秋，它以专业踏实而又锐意进取的态度迎来了第十个年头。十年历练。十年积淀，法学社已经成为底蕴深厚，有着优秀传统的五星级知识性学生社团。",
                    "西北农林科技大学网球协会创立于2010年，可以说是一个很年轻的社团了，经过大家的共同努力，西农网球社是西农热爱网球运动的学子的大本营，我们始终秉承“以球会友，增进友谊，提高球技，挑战自我”的创办理念，让我校网球爱好者在网球的名义下彼此走近。协会从创办到现在有正式会员200余人，网球专业指导教练6名，每次活动参与人数众多，气氛和谐。\n",
                    "本协会是由西北农林科技大学团委管理和监督的学生组织，是由热爱羽毛球运动的学生组成的相互学习、相互交流、共同进步、培养自我能力与团队协作精神的学生团体。",
                    "酷玩吉他爱好者协会，是我们学校最早一批成立的社团之一。经过这么多年的发展，我们成为了最受新生欢迎的社团之一。每年我们会有70位组左右的新成员加入。我们吉他协会是一个以音乐为背景的个性社团，协会主要服务于在校大学生，给大家提供交流音乐、吉他、尤克里里、手鼓、非洲鼓等的平台。我们协会坚持着一个宗旨：“弦结知音，青春共鸣，以心抚琴，以塑树人”；坚持着“让吉他成为一门艺术”这一原则。\n",
                    "西北农林科技大学笛箫口琴协会是有全校对竹笛、洞箫、口琴有兴趣的学生自发组织的学生社团。社团主要弘扬竹笛、洞箫、口琴文化，将竹笛、洞箫、口琴搬上舞台，成为大众化的乐器。社团类型为艺术类社团，传播音乐文化，提高学生音乐修养。社团活动范围位于西北农林科技大学内部及周边范围。",
                    "西北农林科技大学历史文化追溯与研讨协会成立于2009年3月，协会是由资源环境学院学生王光洲同学(现在已经成功考取中国农业大学继续深造)自发组织成立，以宣传历史知识，组织历史活动，开展历史教育为主要内容。是一个融思想性、学术性和实践性为一体的大学生理论学习公益组织。现共有会员八十余名，属于一个正在不断发展壮大的年轻文化协会，有着健全的制度和积极向上的协会文化。\n",
                    "西北农林科技大学女子足球爱好者协会成立于2015年6月26日。本协会是在学生社团联合会的指导下，由热爱足球的同学发起，经院团委和社团联合会批准成立的。\n",
                    "浓乐相声小品协会是一个以相声小品为主以其他表演形式为辅的兴趣类表演性质的社团。人生最大的幸福就是开心，所以我们的宗旨就是“开心嘛”，这里集中了全校最开心人们，我们在一起是一个欢乐的大家庭，不仅为广大"

            };
    private int[] Images=new int[]
            {
                    R.drawable.sthongfeng,
                    R.drawable.stqingnian,
                    R.drawable.stchuantong,
                    R.drawable.stkuaiji,
                    R.drawable.stqiushi,
                    R.drawable.stfaxue,
                    R.drawable.stwangq,
                    R.drawable.styumao,
                    R.drawable.stjita,
                    R.drawable.stqinxiao,
                    R.drawable.stlishi,
                    R.drawable.stzuqiu,
                    R.drawable.stxiangsheng

            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_);
        listView = (ListView) findViewById(R.id.comView);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("社团联系");
        setSupportActionBar(toolbar);

        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++)
        {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("header",Images[i]);
            listItem.put("personName",names[i]);
            listItem.put("desc",headers[i]);
            listItems.add(listItem);
        }


        SimpleAdapter simpleAdapter =new SimpleAdapter(this,listItems,R.layout.com_item,
                new String[]{"personName","header","desc"},
                new int[]{R.id.name1,R.id.header,R.id.desc});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog(i);

            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,com_array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                  wz=0;
              }
              else if(i==1){
                    wz=0;
              }
              else if(i==2){
                    wz=2;
              }
              else if(i==3){
                  wz=4;
              }
              else if(i==4){
                  wz=6;
              }
              else if(i==5){
                  wz=8;
              }
              else if(i==6){
                  wz=10;
              }
                listView.setSelection(wz);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        data.add("红风社");
        data.add("青年志愿者");
        data.add("传统文化");
        data.add("会计");
        data.add("求是学社");
        data.add("法学社");
        data.add("裁判协会");
        data.add("羽毛球协会");
        data.add("酷玩吉他社");
        data.add("琴箫口琴社");
        data.add("历史协会");
        data.add("美术协会");
        data.add("漆木篆刻协会");




        return data;
    }

    protected void dialog(final int num) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(Images[num]).setTitle(names[num]).setMessage(headers[num]).setPositiveButton("拨打",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://" +arrnum1[num]));
                        startActivity(intent);
                    }
                }).setNegativeButton(arrnum1[num], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
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
