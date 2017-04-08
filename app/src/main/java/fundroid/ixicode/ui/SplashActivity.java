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
import fundroid.ixicode.model.RecomPlaces;
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
    }

    @Override
    protected void onResponse(int request_code, String response) {
        super.onResponse(request_code, response);
        if(recom_places != null){
            gotoHome(recom_places);
        }else{
            gotoHome();
        }
    }
}