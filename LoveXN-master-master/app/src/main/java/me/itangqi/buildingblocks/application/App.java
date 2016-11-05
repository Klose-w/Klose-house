package me.itangqi.buildingblocks.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.v5kf.client.lib.V5ClientAgent;
import com.v5kf.client.lib.callback.V5InitCallback;

/**
 * Created by tangqi on 7/20/15.
 */
public class App extends Application {

    public static Context mContext;
    String url="http://123.206.213.110/love_nwsuaf/";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        V5ClientAgent.init(this, new V5InitCallback() {
            @Override
            public void onSuccess(String response) {
// TODO Auto-generated method stub
                Log.i("MyApplication", "init success: " + response);
            }

            @Override
            public void onFailure(String response) {
// TODO Auto-generated method stub
                Log.e("MyApplication", "init failed: " + response);
            }
        });

            }
        }
