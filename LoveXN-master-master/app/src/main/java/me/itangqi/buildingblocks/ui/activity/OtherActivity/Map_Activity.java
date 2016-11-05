package me.itangqi.buildingblocks.ui.activity.OtherActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.List;

import me.itangqi.buildingblocks.R;

public class Map_Activity extends AppCompatActivity {

    // 路线
    RouteLine route = null;
    // 路线搜索相关
    RoutePlanSearch mSearch = null;
    Button line;
    Button south;
    double longitude;//当前位置维度
    double latitude;//当前位置经度
    String searchText;
    SearchView searchView;
    InfoWindow mInfoWindow;
    MapView mapView;
    BaiduMap baiduMap;
    double transLat;
    double transLon;
    BitmapDescriptor mCurrentMarker = null;
    double zoomLevel;
    OverlayOptions textOption;
    LatLng[] Lstens;
    List<String> name;
    Overlay[] Stens;
    LatLng Lsten;
    String[] site;
    ListView listView;
    private LocationClient mLocationClient=null;
    private BDLocationListener mBDLocationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        // 初始化路线查询对象
        mSearch = RoutePlanSearch.newInstance();
        // 设置路线查询结果监听
        mSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult result) {
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(baiduMap);


                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap(); // 将所有Overlay 添加到地图上
                overlay.zoomToSpan(); // 缩放地图，使所有Overlay都在合适的视野内 注：

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        });
        mLocationClient = new LocationClient(getApplicationContext());
        mBDLocationListener = new MyBDLocationListener();
        // 注册监听
        mLocationClient.registerLocationListener(mBDLocationListener);
        this.setLocationOption();
        mLocationClient.start();
        south=(Button)findViewById(R.id.south);
        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(south.getText().equals("定位南校"))
                {
                    Lsten = new LatLng(34.272096, 108.080415);
                    south.setText("定位北校");
                }
                else
                {
                    Lsten = new LatLng(34.292096, 108.070415);;
                    south.setText("定位南校");
                }
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(Lsten, 17);   //设置地图中心点以及缩放级别
                baiduMap.animateMapStatus(u);
            }
        });

        line=(Button)findViewById(R.id.line);
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 设置起终点信息，对于tranist search 来说，城市名无意义
                PlanNode stNode = null;
                PlanNode enNode = null;
                // 根据坐标查询 我的坐标 ---> 目标marker

                stNode = PlanNode.withLocation(new LatLng(latitude,longitude));
                enNode = PlanNode.withLocation(Lsten);
                dialog(Double.toString(latitude));
                    // 显示Loading
                    mSearch.drivingSearch((new DrivingRoutePlanOption()).from(
                            stNode).to(enNode));
                mLocationClient.stop();

            }

        });
        listView=(ListView)findViewById(R.id.lv);
        //搜索框
        searchView=(SearchView)findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        mapView = (MapView) findViewById(R.id.mapview);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        MyLocationData locationData = new MyLocationData.Builder().direction(1000).latitude(transLat)
                .longitude(transLon).build();
        baiduMap.setMyLocationData(locationData);
        Lsten = new LatLng(34.291909, 108.073439);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(Lsten, 17);   //设置地图中心点以及缩放级别
        baiduMap.animateMapStatus(u);
        zoomLevel = baiduMap.getMapStatus().zoom;

        //构建文字Option对象，用于在地图上添加文字
        textstart();
        baiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            public void onTouch(MotionEvent event) {
                zoomLevel = baiduMap.getMapStatus().zoom;
                //构建文字Option对象，用于在地图上添加文字
                if (zoomLevel > 18) {
                    //在地图上添加该文字对象并显示
                    for (int i = 0; i < 5; i++) {
                        Stens[i].setVisible(true);
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        Stens[i].setVisible(false);
                    }
                }

            }
        });
        final ArrayAdapter<String> adapter=new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, site);

        //这里开启ListView的过滤功能，必须开启，否则不会过滤
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                searchView.setQuery(((TextView) view).getText().toString(), false);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // dialog("搜索");
                dingwei(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listView.setAdapter(adapter);
                if(TextUtils.isEmpty(newText)){
                    // 清除ListVie的过滤
                    //listView.setFilterText(" ");
                    adapter.getFilter().filter("哈哈");
                }else{
                    //listView.setFilterText(newText);
                    adapter.getFilter().filter(newText.toString());
                }
                return false;
            }
        });

         //点击事件
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                Button location = new Button(getApplicationContext());
                location.setText(marker.getTitle());
                mInfoWindow = new InfoWindow(location, marker.getPosition(), -47);
                baiduMap.showInfoWindow(mInfoWindow);
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(Map_Activity.this,Schoolplace.class);
                        startActivity(intent);
                    }
                });
                return true;
            }
        });

    }
    //获取经纬度
    private class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            // 非空判断
            if (bdLocation == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            // baiduMap.setMyLocationData(locData);    //设置定位数据
            LatLng latLng = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(latLng, 16);   //设置地图中心点以及缩放级别
            latitude=locData.latitude;
            longitude=locData.longitude;
        }
    }
    public void dialog(String num) {
        new AlertDialog.Builder(this).setIcon(R.drawable.tea)
                .setTitle("教师信息").setMessage("  姓名"+num).create().show();



    }

    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向
        mLocationClient.setLocOption(option);
    }
    public void dingwei(String sit)
{
    for(int i=0;i<5;i++)
    {
        if(sit.equals(site[i]))
        {
            //dialog(sit);
            Lsten=Lstens[i];
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(Lsten, 19);   //设置地图中心点以及缩放级别
            baiduMap.animateMapStatus(u);
            for (int k = 0; k < 5; k++) {
                Stens[k].setVisible(true);
            }
            break;
        }
    }
}

public void textstart()
{
    site=new String[]{"西区10号宿舍","信息学院","西区超市","北秀","八号教学楼"};
    Lstens=new LatLng[5];
    Lstens[0]=new LatLng(34.291909, 108.073439);
    Lstens[1]=new LatLng(34.292096, 108.080415);
    Lstens[2]=new LatLng(34.291979, 108.074139);
    Lstens[3]=new LatLng(34.293159, 108.074439);
    Lstens[4]=new LatLng(34.290909, 108.073939);
    Stens=new Overlay[5];
    for(int i=0;i<5;i++)
    {
            textOption = new TextOptions()
            .bgColor(0xAAFFFF00)
            .fontSize(24)
            .fontColor(0xFFFF00FF)
            .text(site[i])
            .position(Lstens[i]);
             Stens[i]=baiduMap.addOverlay(textOption);
             Stens[i].setVisible(false);
             setOnePointToMap(Lstens[i],site[i]);
    }



}
    private void setOnePointToMap(LatLng latLng,String str) {

        // 2 描述其
        BitmapDescriptor descriptor = BitmapDescriptorFactory
                .fromResource(R.drawable.dingwei);

        // 3 位置 纬经度
        // 116.396364,39.916097


        // 1 覆盖一层 透视的 图层！
        MarkerOptions markerOptions = new MarkerOptions().title(str)
                .icon(descriptor).position(latLng).perspective(true).zIndex(18);

        // 向地图添加一个 Overlay
        baiduMap.addOverlay(markerOptions);

    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
      //locationClient.stop();
        // 关闭定位图层

        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(mBDLocationListener);
        }
    }
}
