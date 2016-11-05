package me.itangqi.buildingblocks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.network.clas.NoticeInfo;
import me.itangqi.buildingblocks.ui.activity.FragmentActivity.NoticeActivity;

/**
 * Created by rain on 2016/4/13.
 */
public class NoticeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int ITEM_TYPE_CONTEN0 = 1;
    public  static  final  String URL = "";
    ArrayList<NoticeInfo> info=new ArrayList<NoticeInfo>();
    public String [] title;
    public String [] news_text;
    public String [] image_url;
    public JSONObject json;
    public Pair[] pairs;

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Integer minID;
    public NoticeListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    private void GetNotice() {
        try {
            JSONArray titleList = json.getJSONArray("college_notice_content");
            int length = titleList.length();
            for(int i = 0; i < length;i++)
            {
                JSONObject temp = titleList.getJSONObject(i);
                title[i] =temp.getString("college_notice_content");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void network(String url) {
        Type type = new TypeToken<ArrayList<NoticeInfo>>() {}.getType();
        ArrayList<NoticeInfo> jsonObjects = new Gson().fromJson(url, type);

        for (NoticeInfo infoitem : jsonObjects)
        {
            info.add(0,infoitem);
        }
    }

    public int returnID() {

        if(info.isEmpty())
        {
            return -1;
        }else{
            return info.get(0).getCollege_notice_id();

        }
    }



    //内容长度
    public int getContentItemCount(){
        return info.size();//得到item长度
    }
    //判断当前item是否是HeadView

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
            return ITEM_TYPE_CONTEN0;
    }
    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
       // @Bind(R.id.coordinatorLayout) CoordinatorLayout mContainer;
        private TextView title;
        private TextView news;
        public ContentViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_item_title);
            news = (TextView) itemView.findViewById(R.id.tv_item_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userActivity = new Intent(itemView.getContext(), NoticeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    userActivity.putExtra("title", title.getText());
                    userActivity.putExtra("content", news.getText());
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
            ((ContentViewHolder) holder).title.setText(info.get(position).getCollege_notice_title());
            ((ContentViewHolder)holder).news.setText(info.get(position).getCollege_notice_content());
    }

    @Override
    public int getItemCount() {
        return  getContentItemCount();
    }


}

