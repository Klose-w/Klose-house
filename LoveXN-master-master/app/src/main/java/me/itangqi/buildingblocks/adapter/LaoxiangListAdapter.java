package me.itangqi.buildingblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.network.clas.LaoxiangInfo;
import me.itangqi.buildingblocks.ui.activity.FragmentActivity.LaoxiangActivity;

/**
 * Created by oreo on 2016/10/20.
 */
public class LaoxiangListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_CONTEN0 = 1;
    public  static  final  String URL = "";
    private String[] pro={"北京","天津","上海","重庆","河北省","山西省","辽宁省","吉林省","黑龙江",
            "江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省",
            "海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古","广西",
            "西藏","宁夏","新疆","香港","澳门"};
    //    ArrayList<String> title = new ArrayList<String>();
//    ArrayList<String> news_text = new ArrayList<String>();
    ArrayList<LaoxiangInfo> info=new ArrayList<LaoxiangInfo>();

    // public String [] title;
    //public String [] news_text;
    public String [] image_url;
    public JSONObject json;
    public Pair[] pairs;

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SharedPreferences sp;
    private Integer minID;
    public LaoxiangListAdapter(Context context) {
        mContext = context;
//        for(int i=0;i<5;i++){
//                    info.add(new Luntaninfo(i,"test"+i,"test"+i,"null"));
//        }
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void network(String url) {
        Type type = new TypeToken<ArrayList<LaoxiangInfo>>() {}.getType();
        ArrayList<LaoxiangInfo> jsonObjects = new Gson().fromJson(url, type);

        for (LaoxiangInfo infoitem : jsonObjects)
        {
            info.add(0,infoitem);
        }
    }



    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE_CONTEN0;
    }

    public int returnID() {

        if(info.isEmpty())
        {
            return -1;
        }else{
            return info.get(0).getHometown_id();

        }
    };

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        // @Bind(R.id.coordinatorLayout) CoordinatorLayout mContainer;
        private TextView title;
        private TextView news;
        private int id;
        public String pic_exist;
        public int pro;

        public ContentViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_item_title);
            news = (TextView) itemView.findViewById(R.id.tv_item_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userActivity = new Intent(itemView.getContext(), LaoxiangActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    String[] sourceStrArray = title.getText().toString().split(":  ");

                    userActivity.putExtra("content", news.getText());
                    userActivity.putExtra("title",sourceStrArray[1]);
                    userActivity.putExtra("id",id);
                    userActivity.putExtra("pic_exist",pic_exist);
                    userActivity.putExtra("pro",pro);
                    itemView.getContext().startActivity(userActivity);
                }
            });
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(mLayoutInflater.inflate(R.layout.news_item, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ContentViewHolder) holder).title.setText(pro[info.get(position).getHometown_pro_id()]+":  "+info.get(position).getHometown_title());//title[position]
        ((ContentViewHolder)holder).news.setText(info.get(position).getHometown_content());
        ((ContentViewHolder)holder).id = info.get(position).getHometown_id();
        ((ContentViewHolder) holder).pic_exist=info.get(position).getHometown_picture_url();
        ((ContentViewHolder) holder).pro=info.get(position).getHometown_pro_id();
       // Log.i("tiebaadpter", "" + info.get(position).getTopic_picture_url());

    }

    @Override
    public int getItemCount() {
        return  info.size();
    }

}
