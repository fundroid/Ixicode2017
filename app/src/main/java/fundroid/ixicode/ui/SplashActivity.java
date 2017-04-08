package fundroid.ixicode.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import fundroid.ixicode.R;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Place;
import fundroid.ixicode.utils.Slog;

import static fundroid.ixicode.base.API_Requests.REQUEST_RECOM;
import static fundroid.ixicode.base.Apis.URL_RECOMENDED;

public class SplashActivity extends BaseActivity {

    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getRecommendations();
//        final Handler handler = new Handler();
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                gotoHome();
//            }
//        };
//        handler.postDelayed(r, 3000);
    }

    //calling the recommendations api to get recommended places.
    protected void getRecommendations(){
        volleyGetHit(URL_RECOMENDED, REQUEST_RECOM);
    }

    @Override
    protected void onResponse(int request_code, String response) {
        super.onResponse(request_code, response);
        if(request_code == REQUEST_RECOM){
            Slog.d("success ------------------");

            try {
                Gson gson = new Gson();
                JSONObject bObj = new JSONObject(response);
                JSONObject dataobj = bObj.getJSONObject("data");
                JSONArray flights = dataobj.getJSONArray("flight");
                places = gson.fromJson(flights.toString(), new TypeToken<ArrayList<Place>>() {}.getType());
                if(places != null && places.size() > 0){
                    gotoHome(places);
                }else{
                    gotoHome();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}