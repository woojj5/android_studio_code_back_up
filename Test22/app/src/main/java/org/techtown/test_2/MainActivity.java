package org.techtown.test_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<item> arrayList = new ArrayList<>();
    XmlPullParserFactory pullParserFactory;
    RecyclerView myRecyclerView;
    DataAdapter dataAdapter;

    String end1 = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey=";
    String key = "iORkywxuz9aVxEKEHQqne9q5GfXw0EifQXDgFLtDFd%2BCyqWe%2BlMFrJJyDXd%2BVTctia9ifhr6gXsczAbHhgpVvA%3D%3D";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = "반포역";//EditText에 작성된 Text얻어오기
        String location = URLEncoder.encode(str);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding     //지역 검색 위한 변수
        String queryUrl = end1 + key + "&stSrch=" + location;
        myRecyclerView = findViewById(R.id.mrecyclerview);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.setHasFixedSize(true);
        try{
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = getApplicationContext().getAssets().open(queryUrl);
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            parseXML(parser);
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    private void parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<item> countries = null;
        int eventType = parser.getEventType();
        item country = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name;
            name = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    countries = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.END_TAG:
                    if (name.equals("itemList")) {
                        Log.e("VALUE", parser.getAttributeValue(null, "stNm") + "");
                        Log.e("VALUE", parser.getAttributeValue(null, "stId") + "");
//                        Log.e("VALUE", parser.getAttributeValue(null, "") + "");
//                        Log.e("VALUE", parser.getAttributeValue(null, "") + "");
//                        Log.e("VALUE", parser.getAttributeValue(null, "") + "");

                        item bookmark = new item();
                        bookmark.setStNm(parser.getAttributeValue(null, "stNm"));
                        bookmark.setStId(parser.getAttributeValue(null, "stId"));
//                        bookmark.setId(parser.getAttributeValue(null, "id"));
//                        bookmark.setSearchUrl(parser.getAttributeValue(null, "searchUrl"));
//                        bookmark.setNativeUrl(parser.getAttributeValue(null, "nativeUrl"));
                        arrayList.add(bookmark);
                    }
                    break;
            }
            eventType = parser.next();
        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        int eventType = parser.getEventType();
        item bookmark = null;
    }
}
