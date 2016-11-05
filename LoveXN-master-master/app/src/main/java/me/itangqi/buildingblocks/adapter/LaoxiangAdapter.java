package me.itangqi.buildingblocks.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.application.App;
import me.itangqi.buildingblocks.network.GetImage;
import me.itangqi.buildingblocks.network.clas.Comment;

/**
 * Created by oreo on 2016/10/20.
 */
public class LaoxiangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private String tie_text;
    private String content;
    private int id;
    private String url;
    public JSONObject json;
    ArrayList<Comment> info=new ArrayList<Comment>();
    private String pic_exist;
    ProgressDialog proDialog;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] pro={"北京","天津","上海","重庆","河北省","山西省","辽宁省","吉林省","黑龙江",
            "江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省",
            "海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古","广西",
            "西藏","宁夏","新疆","香港","澳门"};
    private SharedPreferences sp;
    private static final int MSG_NEW_PIC = 2;
    private static final int MSG_CACHE_PIC = 1;
    private static final int ERROR = 3;
    private static final int EXCEPTION = 4;
    private Drawable drawable1;
    private Drawable drawable2;
    private int biaozhi=0;
    private Handler handler=new Handler(){


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CACHE_PIC:
                    //3.处理消息 运行在主线程
                    Bitmap bitmap = (Bitmap) msg.obj;
                    // mIvShow.setImageBitmap(bitmap);
                    drawable1 =new BitmapDrawable(bitmap);
                    biaozhi=1;
                    notifyDataSetChanged();
                    System.out.println("(不用下载)缓存图片");
                    proDialog.dismiss();
                    break;
                case MSG_NEW_PIC:
                    Bitmap bitmap2 = (Bitmap) msg.obj;
                    // mIvShow.setImageBitmap(bitmap2);
                    biaozhi=2;
                    notifyDataSetChanged();
                    drawable2=new BitmapDrawable(bitmap2);
                    System.out.println("新下载(还没有缓存)下载的图片");
                    proDialog.dismiss();
                    break;
                case ERROR:
                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                    break;
                case EXCEPTION:
                    Toast.makeText(mContext, "发生异常，请求失败", Toast.LENGTH_SHORT).show();
                    break;
            }

            super.handleMessage(msg);
        }



    };
    public LaoxiangAdapter(Context context, String title, String content, int id, String pic_exist,int proid) {

        mContext = context;
        sp = context.getSharedPreferences("ziliao", context.MODE_PRIVATE);
        String userName = sp.getString("user_name", "tom");
        this.tie_text = pro[proid]+": "+userName+": "+title;
        this.id =  id;
        this.pic_exist=pic_exist;
        this.content = content;
        mLayoutInflater = LayoutInflater.from(context);
        String appurl=((App)mContext.getApplicationContext()).getUrl();
//        "http://172.17.66.131/love_nwsuaf/src/img/";
//        +="3_"+id+".jpg";
        this.url=appurl+"src/img/"+"4_"+id+".jpg";
        testPicure();
    }


    private void testPicure() {
        Log.i("tiebaadpter", "" + pic_exist);
        if(pic_exist==null){
            return;
        } else {
            //Log.i("tiebaadpter",""+url);
            new GetImage(url,handler,proDialog,mContext).start();
            createProgressBar();
            return;
        }

    }

    private void createProgressBar() {
        proDialog = android.app.ProgressDialog.show(mContext, "请等待", "数据传送中！");
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

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void network(String test) {
        Log.i("biaozhiwei1", "" + test);
        Type type = new TypeToken<ArrayList<Comment>>() {}.getType();
        ArrayList<Comment> jsonObjects = new Gson().fromJson(test, type);

        for(int i=info.size();i<jsonObjects.size();i++){
            info.add(jsonObjects.get(i));
        }


//        for (Comment infoitem : jsonObjects)
//        {
//            info.add(infoitem);
//        }
//
        notifyDataSetChanged();
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView reply;
        public ContentViewHolder(final View itemView) {
            super(itemView);
            reply = (TextView) itemView.findViewById(R.id.tv_item_text_laoxiang);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(mLayoutInflater.inflate(R.layout.laoxiang_item,parent,false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        // createProgressBar();
        if (biaozhi == 0) {
            if (position == 0) {
                ((ContentViewHolder) holder).reply.setText(tie_text);
            } else if (position == 1) {
                        ((ContentViewHolder) holder).reply.setText(content);
            }  else {
                ((ContentViewHolder) holder).reply.setText(info.get(position - 2).getUser_name() + ": " + info.get(position - 2).getComment_content());//reply_text[position - 2]
            }
        }else{
            if (position == 0) {
                ((ContentViewHolder) holder).reply.setText(tie_text);
            } else if (position == 1) {
                ((ContentViewHolder) holder).reply.setText(content);

            } else if (position == 2) {

                if (biaozhi == 1) {
                    ((ContentViewHolder) holder).reply.setBackground(drawable1);

                } else if(biaozhi==2){
                    ((ContentViewHolder) holder).reply.setBackground(drawable2);

                }
            } else {
                ((ContentViewHolder) holder).reply.setText(info.get(position - 3).getUser_name() + ": " + info.get(position - 3).getComment_content());//reply_text[position - 2]
            }
        }

    }
    @Override
    public int getItemCount() {
        if(biaozhi==0){
            return info.size()+2;
        }
    return info.size()+3;
}



}
