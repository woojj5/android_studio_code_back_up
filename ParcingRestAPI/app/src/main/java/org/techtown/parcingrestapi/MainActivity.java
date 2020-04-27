package org.techtown.parcingrestapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText edit;
    EditText edit2;


    final String TAG = "MainActivity";
    String end1 = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey=";
    String end2 = "http://ws.bus.go.kr/api/rest/stationinfo/getLowStationByUid?serviceKey=";
    public String key = "iORkywxuz9aVxEKEHQqne9q5GfXw0EifQXDgFLtDFd%2BCyqWe%2BlMFrJJyDXd%2BVTctia9ifhr6gXsczAbHhgpVvA%3D%3D";
    private EditText xmlBusNum;
    private final String endPoint = "http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll?serviceKey=";
    private final String endPoint2 = "http://openapi.gbis.go.kr/ws/rest/busrouteservice?serviceKey";
    private String requestUrl;
    ArrayList<Item> list = null;
    Item  bus = null;
    RecyclerView recyclerView;
    public static String str;
    public static String location;
    public static String str2;
    Button button;
    //http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll?ServiceKey=ZdbB0Zeriv8Nvv8rbJb%2B%2BXwif813ubOy3Kbz696p7EbQJh%2BreDWP341ME00ibmKF3CEqpamX%2FakU98u4KYOwHg%3D%3D&busRouteId=100100118

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.edit);
        edit2 = (EditText) findViewById(R.id.edit2);
        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(true);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }


        //str = edit.getText().toString();
        //str2 = edit2.getText().toString();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.button:
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        MyAsyncTasks myAsyncTask = new MyAsyncTasks();
//                                        myAsyncTask.execute();
//                                    }
//                                });
//                            }
//                        }).start();
//                }
//            }
//        });

    public class MyAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = end1 +key+ "&stSrch=" + "%EB%B0%98%ED%8F%AC%EC%97%AD";
            try {
                boolean b_stId = false;
                boolean b_stNm =false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<Item>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("itemList") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("itemList")){
                                bus = new Item();
                            }
                            if (parser.getName().equals("ardsid")) b_stId = true;
                            if (parser.getName().equals("stNm")) b_stNm = true;
                            break;
                        case XmlPullParser.TEXT:
                            if(b_stId){
                                bus.setStId(parser.getText());
                                b_stId = false;
                            } else if(b_stNm) {
                                bus.setStNm(parser.getText());
                                b_stNm = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
        }
    }

//        protected String doInBackground2(String data) {
//            String requestUrl =   end2 + key + "&arsId=" + data;
//
//            try{
//                boolean b_armsg1 = false;
//                boolean b_armsg2 = false;
//                boolean b_adir = false;
//                boolean b_rtNm = false;
//                URL url = new URL(requestUrl);
//                InputStream is = url.openStream();
//                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//                XmlPullParser parser = factory.newPullParser();
//                parser.setInput(new InputStreamReader(is, "UTF-8"));
//                String tag;
//                int eventType = parser.getEventType();
//
//                while(eventType != XmlPullParser.END_DOCUMENT){
//                    switch (eventType){
//                        case XmlPullParser.START_DOCUMENT:
//                            list = new ArrayList<Item>();
//                            break;
//                        case XmlPullParser.END_DOCUMENT:
//                            break;
//                        case XmlPullParser.END_TAG:
//                            if(parser.getName().equals("itemList") && bus != null) {
//                                list.add(bus);
//                            }
//                            break;
//                        case XmlPullParser.START_TAG:
//                            if(parser.getName().equals("itemList")){
//                                bus = new Item();
//                            }
//                            if (parser.getName().equals("armsg1")) b_armsg1 = true;
//                            if (parser.getName().equals("armsg2")) b_armsg2 = true;
//                            if (parser.getName().equals("adir")) b_adir = true;
//                            if (parser.getName().equals("rtNm")) b_rtNm = true;
//                            break;
//                        case XmlPullParser.TEXT:
//                            if(b_adir){
//                                bus.setAdir(parser.getText());
//                                b_adir = false;
//                            } else if(b_armsg1) {
//                                bus.setArmsg1(parser.getText());
//                                b_armsg1 = false;
//                            }
//                            else if(b_armsg2) {
//                                bus.setArmsg2(parser.getText());
//                                b_armsg2 = false;
//                            }
//                            else if(b_rtNm) {
//                                bus.setRtNm(parser.getText());
//                                b_rtNm = false;
//                            }
//                            break;
//                    }
//                    eventType = parser.next();
//                }
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }

//    }
}
