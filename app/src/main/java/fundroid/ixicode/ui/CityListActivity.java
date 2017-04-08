package fundroid.ixicode.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.CityRecAdapter;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.City;
import fundroid.ixicode.utils.VolleyHelper;
import fundroid.ixicode.utils.VolleyInterface;

import static fundroid.ixicode.base.API_Requests.REQUEST_CITY_SUGGEST;
import static fundroid.ixicode.base.Apis.URL_CITY_SUGGEST;

public class CityListActivity extends BaseActivity {

    RecyclerView rv_cities;
    EditText et_city;
    String query;

    VolleyInterface vi = new VolleyInterface() {
        @Override
        public void requestStarted(int request_code) {}

        @Override
        public void requestCompleted(int request_code, String response) {
            if (request_code == REQUEST_CITY_SUGGEST) {
                Gson gson = new Gson();
                try {
                    ArrayList<City> cities = gson.fromJson(response, new TypeToken<ArrayList<City>>() {}.getType());
                    setCityAdapter(cities);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void requestEndedWithError(int request_code, VolleyError error) {
        }
    };
    Handler handler = new Handler(Looper.getMainLooper() /*UI thread*/);
    Runnable workRunnable = new Runnable() {
        @Override
        public void run() {
            getCitySuggestions(query);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        rv_cities = (RecyclerView) findViewById(R.id.rv_cities);
        rv_cities.setHasFixedSize(false);
        rv_cities.setLayoutManager(new LinearLayoutManager(this));

        et_city = (EditText) findViewById(R.id.et_city);
        et_city.addTextChangedListener(new TextWatcher() {

            private final long DELAY = 500; // milliseconds

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query = s.toString();
                if (query.length() > 3) {
                    handler.removeCallbacks(workRunnable);
                    handler.postDelayed(workRunnable, DELAY /*delay*/);
                }
            }
        });
    }

    private void getCitySuggestions(String query) {
        String url = URL_CITY_SUGGEST.replace("<Q>", query);
        VolleyHelper.getRequestVolley(vi, url, REQUEST_CITY_SUGGEST);
    }

    private void setCityAdapter(ArrayList<City> cities) {
        CityRecAdapter cAdapter = new CityRecAdapter(bContext, cities);
        rv_cities.setAdapter(cAdapter);
    }

    public void goInCity(City city) {
        gotoCityDetails(city);
    }
}