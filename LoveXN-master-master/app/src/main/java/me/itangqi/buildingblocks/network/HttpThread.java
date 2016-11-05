package me.itangqi.buildingblocks.network;

import android.content.Context;
import android.os.Handler;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rain on 2016/4/23.
 */
public class HttpThread extends Thread {

    private String url;
    private Handler handler;
    private Context context;
    private Pair<String,String>[] pairs;
    public String returnString;


    public HttpThread(String url,Context Context,Pair<String,String>[] pairs) {
        this.url = url;
        this.context = context;
        this.pairs= pairs;
    }

    public JSONObject createJSON() {
        JSONObject json = new JSONObject();
        try {
            for( Pair<String,String> temp : pairs)
            {
                json.put(temp.first,temp.second);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("POST");
            //建立JSON
            JSONObject jsonData = createJSON();

            String result = String.valueOf(jsonData);
            System.out.println(result);
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            //把JSON传入输出流，传给服务器
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(result.getBytes());
            outputStream.close();

            //获取POST的返回码，200意味上传成功
            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (connection.getInputStream()));
                String line = null;
                final StringBuffer stringBuffer = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
                //返回
                returnString = stringBuffer.toString();
            } else {
               // Snackbar.make(context.getClass(), "传输失败", Snackbar.LENGTH_LONG).show();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //得到返回值
    public String GetString()
    {
        return this.returnString;
    }
}
