package org.techtown.sampleapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity {

    Context context;
    public static final String TAG = MainActivity.class.getSimpleName();
    public EditText edit;
    public Button send;
    TextView status1;
    // http://www.data.go.kr 에서 로그인 후 API 조회하여 신청 승인받은 key 값
    String key = "ZdbB0Zeriv8Nvv8rbJb%2B%2BXwif813ubOy3Kbz696p7EbQJh%2BreDWP341ME00ibmKF3CEqpamX%2FakU98u4KYOwHg%3D%3D\n"; // 실제 키값이 아님 (변경처리함)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getBaseContext();

        initView();
    }

    private void initView() {
        status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!
        edit = (EditText) findViewById(R.id.message) ;
        send = (Button)findViewById(R.id.send);

        // message send action
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit.getText().toString().isEmpty()){
                    BusArriveTask(edit.getText().toString());
                    edit.setText(" ");
                }
            }
        });
    }

    private void BusArriveTask(String search){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String StationId = null; // 정류소 ID
        try {
            StationId = URLEncoder.encode(search,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 버스 도착정보 목록 조회
        String url = "http://openapi.gbis.go.kr/ws/rest/busarrivalservice/station?serviceKey="+key+"&stationId="+StationId+"";
        Log.d(TAG, "URL:"+url);

        StringRequest request= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        XMLtoJSONData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(request);
    }

    private void XMLtoJSONData(String xml){
        // https://androidfreetutorial.wordpress.com/2016/11/28/how-to-convert-xml-to-json-for-android/
        XmlToJson xmlToJson = new XmlToJson.Builder(xml).build();
        // convert to a JSONObject
        JSONObject jsonObject = xmlToJson.toJson();
        Log.d(TAG, "jsonObject:"+jsonObject);

        // JSON 에서 배열은 [] 대괄호 사용, Objext 는 {} 중괄호 사용
        try {
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject msgHeader = response.getJSONObject("msgHeader");
            String resultCode = msgHeader.optString("resultCode");
            Log.d(TAG, "String resultCode :"+resultCode);

            if(resultCode.equals("0")){
                JSONObject msgBody = response.getJSONObject("msgBody");
                Log.d(TAG, "jsonObject msgBody :"+msgBody);

                JSONArray array = msgBody.getJSONArray("busArrivalList");
                for(int i=0; i < array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                    // optString which returns the value mapped by name if it exists
                    String plateNo1 =obj.optString("plateNo1"); // 첫번째 차량 번호
                    String locationNo1 =obj.optString("locationNo1"); // 첫번째 차량 위치 정보
                    String plateNo2 =obj.optString("plateNo2"); // 두번째 차량 번호
                    String locationNo2 =obj.optString("locationNo2"); // 두번째 차량 위치 정보
                    Log.d(TAG, "jString plateNo1 :"+plateNo1);
                    Log.d(TAG, "jString plateNo2 :"+plateNo2);
                    Log.d(TAG, "jString locationNo1 :"+locationNo1);
                    Log.d(TAG, "jString locationNo2 :"+locationNo2);
                }
            } else if(resultCode.equals("1")){
                Toast.makeText(context, "시스템 에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            } else if(resultCode.equals("4")){
                Toast.makeText(context, "결과가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
            } else if(resultCode.equals("8")){
                Toast.makeText(context, "요청 제한을 초과하였습니다", Toast.LENGTH_SHORT).show();
            } else if(resultCode.equals("23")){
                Toast.makeText(context, "버스 도착 정보가 존재하지 않습니다", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
