package fundroid.ixicode.ui;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.PlaceHorAdapter;
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

            for (int i = 0; i < recom_places.getFlight().size(); i++) {
                Place place = recom_places.getFlight().get(i);
                getLocationFromAddress(i, this, place.getName()+","+place.getCityName()+","
                        + place.getStateName() +","+ place.getCountryName());
            }
            gotoHome(recom_places);
        }else{
            getRecommendations();
        }
    }


    public LatLng getLocationFromAddress(int position, Context context, String strAddress) {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            recom_places.getFlight().get(position).setLat(lat);
            recom_places.getFlight().get(position).setLng(lng);

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }
}