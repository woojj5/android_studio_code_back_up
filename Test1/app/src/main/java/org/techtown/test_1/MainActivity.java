package org.techtown.test_1;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.techtown.test_1.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {

    EditText edit;
    EditText edit2;
    TextView text;
    String data;
    String armsg1;
    String armsg2;
    String adir;
    String stNm;
    String rtNm;
    String stId;
    String end1 = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName?serviceKey="; // 정류장 이름을 파라미터로 받는다
    String end2 = "http://ws.bus.go.kr/api/rest/stationinfo/getLowStationByUid?serviceKey="; // end1에서 얻은 stid값을 받아서 그 값을 파라미터로 사용
    String key = "iORkywxuz9aVxEKEHQqne9q5GfXw0EifQXDgFLtDFd%2BCyqWe%2BlMFrJJyDXd%2BVTctia9ifhr6gXsczAbHhgpVvA%3D%3D";
    //String key = "ZdbB0Zeriv8Nvv8rbJb%2B%2BXwif813ubOy3Kbz696p7EbQJh%2BreDWP341ME00ibmKF3CEqpamX%2FakU98u4KYOwHg%3D%3D";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.edit);
        edit2 = (EditText) findViewById(R.id.edit2);
        text = (TextView) findViewById(R.id.text);
    }

    //Button을 클릭했을 때 자동으로 호출되는 callback method
    public void mOnClick(View v) {

        switch (v.getId()) {
            case R.id.button:

                // 급하게 짜느라 소스가 지저분해요..
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기


                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                text.setText(data); //TextView에 문자열  data 출력
                            }
                        });

                    }
                }).start();

                break;
        }

    }


    String getXmlData() {

        StringBuffer buffer = new StringBuffer();

        String str = edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = URLEncoder.encode(str);//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding     //지역 검색 위한 변수


        String queryUrl = end1 + key + "&stSrch=" + location;

        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//태그 이름 얻어오기
                        if (tag.equals("itemList")) ;// 첫번째 검색결과
                        else if (tag.equals("arsId")) {
                            xpp.next();
                            stId = xpp.getText();
                        } else if (tag.equals("stNm")) {
                            xpp.next();
                            stNm = xpp.getText();
                            buffer.append("정류소 id : ");
                            buffer.append(stId);
                            buffer.append("\n");
                            buffer.append("정류소 이름 : ");
                            buffer.append(stNm);
                            buffer.append("\n");
                            buffer.append(getextradata(stId));
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //태그 이름 얻어오기
                        if (tag.equals("busRouteList")) {
                            buffer.append("\n");
                            buffer.append("줄 바꿈 확인");
                        }
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
        }
        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
    String  getextradata(String dat) {
        StringBuffer buffer = new StringBuffer();
        String str = edit2.getText().toString();//EditText에 작성된 Text얻어오기
        String queryUrl = end2 + key + "&arsId=" + dat;
        String tag;
        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기
            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//태그 이름 얻어오기
                        if (tag.equals("itemList")) ;// 첫번째 검색결과
                        else if(tag.equals("adirection")){
                            xpp.next();
                            adir = xpp.getText();
                        }
                        else if (tag.equals("arrmsg1")) {
                            xpp.next();
                            armsg1 = xpp.getText();
                        } else if (tag.equals("arrmsg2")) {
                            xpp.next();
                            armsg2 = xpp.getText();
                        } else if (tag.equals("rtNm")) {
                            xpp.next();
                            if(str.equals(xpp.getText())) {
                                buffer.append("버스 방향 : ");
                                buffer.append(adir);
                                buffer.append("\n");
                                buffer.append("첫번째 버스 : ");
                                buffer.append(armsg1);
                                buffer.append("\n");
                                buffer.append("두번째 버스 : ");
                                buffer.append(armsg2);
                                buffer.append("\n");
                                buffer.append("버스 번호 :");
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                            }
                            else if(str.equals("")){
                                buffer.append("버스 방향 : ");
                                buffer.append(adir);
                                buffer.append("\n");
                                buffer.append("첫번째 버스 : ");
                                buffer.append(armsg1);
                                buffer.append("\n");
                                buffer.append("두번째 버스 : ");
                                buffer.append(armsg2);
                                buffer.append("\n");
                                buffer.append("버스 번호 :");
                                buffer.append(xpp.getText());
                                buffer.append("\n");
                            }
                        }
                        break;
                    case XmlPullParser.TEXT: break;
                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //태그 이름 얻어오기
                        if (tag.equals("itemList"))
                            break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {}
        buffer.append("\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
}