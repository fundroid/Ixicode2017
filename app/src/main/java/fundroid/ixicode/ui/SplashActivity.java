package fundroid.ixicode.ui;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import fundroid.ixicode.R;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Place;
import fundroid.ixicode.utils.NetworkUtil;

public class SplashActivity extends BaseActivity {

    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (NetworkUtil.isNetworkAvailable(this)) {

            Handler h = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    getRecommendations();
                }
            };
            h.postDelayed(r, 2000);
        } else {
            showSnackbar("Internet not available");
        }
    }

    @Override
    protected void onResponse(int request_code, String response) {
        super.onResponse(request_code, response);
        if (recom_places != null) {

//            for (int i = 0; i < recom_places.getFlight().size(); i++) {
//                Place place = recom_places.getFlight().get(i);
//                getLocationFromAddress(i, this, place.getName()+","+place.getCityName()+"," + place.getStateName() +","+ place.getCountryName());
//            }
            gotoHome(recom_places);
        } else {
            getRecommendations();
        }
    }

   /* public LatLng getLocationFromAddress(int position, Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            recom_places.getFlight().get(position).setLat(lat);
            recom_places.getFlight().get(position).setLng(lng);

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }*/
}