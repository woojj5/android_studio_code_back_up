package org.techtown.testapi_3;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Context ctx;
    public String end1 = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey="; // 정류장 이름을 파라미터로 받는다
    public String end2 = "http://ws.bus.go.kr/api/rest/stationinfo/getLowStationByUid?serviceKey="; // end1에서 얻은 stid값을 받아서 그 값을 파라미터로 사용
    public String key = "iORkywxuz9aVxEKEHQqne9q5GfXw0EifQXDgFLtDFd%2BCyqWe%2BlMFrJJyDXd%2BVTctia9ifhr6gXsczAbHhgpVvA%3D%3D";
    public String str;
    public String loc;


    final String TAG = "MainActivity";
    public String dataKey = "ZdbB0Zeriv8Nvv8rbJb%2B%2BXwif813ubOy3Kbz696p7EbQJh%2BreDWP341ME00ibmKF3CEqpamX%2FakU98u4KYOwHg%3D%3D";
    private String requestUrl;
    ArrayList<item> list = null;
    item bus = null;
    public RecyclerView recyclerView;
    public EditText editText;
    public EditText editText2;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit);
        editText2 = (EditText)findViewById(R.id.edit2);
        button = (Button)findViewById(R.id.button);
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // data = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MyAsyncTask myAsyncTask = new MyAsyncTask();
                                        myAsyncTask.execute(editText.getText().toString(), editText2.getText().toString());
                                    }
                                });
                            }
                        }).start();
                        break;
                }
            }
        });
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... args) {
            if (args[0].length() > 0 && args[1].length() <= 0) {
                loc = URLEncoder.encode(args[0]);
                requestUrl = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey=" + key + "&stSrch=" + loc;
                try {
                    boolean b_locationNo1 = false;
                    boolean b_plateNo1 = false;
                    boolean b_routeId = false;
                    boolean b_stationId = false;

                    URL url = new URL(requestUrl);
                    InputStream is = url.openStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setInput(new InputStreamReader(is, "UTF-8"));

                    String tag;
                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                list = new ArrayList<item>();
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.END_TAG:
                                if (parser.getName().equals("itemList") && bus != null) {
                                    list.add(bus);
                                }
                                break;
                            case XmlPullParser.START_TAG:
                                if (parser.getName().equals("itemList")) {
                                    bus = new item();
                                }
                                if (parser.getName().equals("arsId")) b_locationNo1 = true;
                                if (parser.getName().equals("stNm")) b_plateNo1 = true;
                                if (parser.getName().equals("stId")) b_routeId = true;
                                if (parser.getName().equals("tmX")) b_stationId = true;
                                break;
                            case XmlPullParser.TEXT:
                                if (b_locationNo1) {
                                    bus.setLocationNo1(parser.getText());
                                    b_locationNo1 = false;
                                } else if (b_plateNo1) {
                                    bus.setPlateNo1(parser.getText());
                                    b_plateNo1 = false;
                                } else if (b_routeId) {
                                    bus.setRouteId(parser.getText());
                                    //doInBackground2(parser.getText(), args[1]);
                                    b_routeId = false;
                                } else if (b_stationId) {
                                    bus.setStationId(parser.getText());
                                    b_stationId = false;
                                }
                                break;
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    }
                }
            else if(args[0].length() <= 0 && args[1].length() > 0) {

                String loc2 = URLEncoder.encode(args[1]);
                requestUrl = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey=" +key+ "&stId=" + loc2;
                try {
                    boolean b_predictTime1 = false;
                    boolean b_predictTime2 =false;
                    boolean b_locationNo2 = false;

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
                                list = new ArrayList<item>();
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
                                    bus = new item();
                                }
                                if (parser.getName().equals("arrmsg1")) b_predictTime1 = true;
                                if (parser.getName().equals("arrmsg2")) b_predictTime2 = true;
                                if (parser.getName().equals("stNm")) b_locationNo2 = true;
                                break;
                            case XmlPullParser.TEXT:
                                if(b_predictTime1){
                                    bus.setPredictTime1(parser.getText());
                                    b_predictTime1 = false;
                                } else if(b_predictTime2) {
                                    bus.setPredictTime2(parser.getText());
                                    b_predictTime2 = false;
                                } else if (b_locationNo2) {
                                    bus.setLocationNo2(parser.getText());
                                    b_locationNo2 = false;
                                }
                                break;
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                return null;
            }
//        protected String doInBackground2(String... args) {
//
//            return null;
//        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Myadapter adapter = new Myadapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
        }
    }
}