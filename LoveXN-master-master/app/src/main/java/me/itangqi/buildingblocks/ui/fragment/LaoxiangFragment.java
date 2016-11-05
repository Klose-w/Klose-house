package me.itangqi.buildingblocks.ui.fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.itangqi.buildingblocks.R;
import me.itangqi.buildingblocks.adapter.LabelListAdapter;
import me.itangqi.buildingblocks.adapter.LaoxiangListAdapter;
import me.itangqi.buildingblocks.network.HttpThreadString;
import me.itangqi.buildingblocks.widget.SimpleDividerItemDecoration;

/**
 * Created by oreo on 2016/10/20.
 */
public class LaoxiangFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout_laoxiang)
    SwipeRefreshLayout swipe_refresh_layout;
    private LabelListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Bind(R.id.cardList_laoxiang)
    RecyclerView mRecyclerView;
    SharedPreferences sh;

    LaoxiangListAdapter laoxiangadpter;
    ProgressDialog proDialog;
    //链表
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laoxiang, container, false);
        ButterKnife.bind(this, view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        swipe_refresh_layout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        laoxiangadpter=new LaoxiangListAdapter(getActivity());
        mRecyclerView.setAdapter(laoxiangadpter);//设置Adapter
        onRefresh();
        return view;
    }

    //重绘时刷新
    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    //刷新
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundler=msg.getData();
            String url=bundler.getString("state");
            Log.i("tiebaliest", "" + url);
            if(url.contains("hometown_id")){

                laoxiangadpter.network(url);
                laoxiangadpter.notifyDataSetChanged();
                //  proDialog.dismiss();
                swipe_refresh_layout.setRefreshing(false);
                Log.i("tiebalist", "" + url);
            }else if (url.contains("100009")){
                Toast.makeText(getActivity(), "当前已是最新", Toast.LENGTH_SHORT).show();
                swipe_refresh_layout.setRefreshing(false);
            }
            else{
                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                swipe_refresh_layout.setRefreshing(false);
            }
        }
    };

//    MyHandler mhandler=new MyHandler();

    @Override
    public void onRefresh() {

        int id=laoxiangadpter.returnID();
        Map map=new HashMap();
        Log.i("tibalist","当前id"+id);
        map.put("hometown_id",String.valueOf(id));
        map.put("method", "getHometown.php");
        new HttpThreadString(handler,this.getActivity(),map, proDialog).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
