package fundroid.ixicode.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.CityPointVerAdapter;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.City;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.model.PointContainer;
import fundroid.ixicode.utils.Slog;

import static fundroid.ixicode.base.API_Requests.REQUEST_CITY_POINT;
import static fundroid.ixicode.base.Apis.URL_CITY_POINTS;
import static fundroid.ixicode.base.Apis.pointTypes;

public class CityDetailsActivity extends BaseActivity {

    City city;
    private RecyclerView rv_city_points;
    private ArrayList<PointContainer> cityPoints;
    private CityPointVerAdapter cpva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        city = (City) getIntent().getSerializableExtra("city");

        if (city == null) {
            finish();
        }

        initViews();
        fetchPoints();
    }

    private void initViews() {
        rv_city_points = (RecyclerView) findViewById(R.id.rv_city_points);
        rv_city_points.setHasFixedSize(false);
        rv_city_points.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchPoints() {
        cityPoints = new ArrayList<>();
        setRecAdapter();
        for (int i = 0; i < pointTypes.length; i++) {
            getCitySuggestions(i);
        }
    }

    private void getCitySuggestions(int pos) {
        String url = URL_CITY_POINTS.replace("<CITY>", city.get_id())
                .replace("<TYPE>", pointTypes[pos])
                .replace("<SKIP>", "0")
                .replace("<LIMIT>", "10")
                .replace(" ","%20");
        volleyGetHit(url, REQUEST_CITY_POINT + pos );
    }

    @Override
    protected void onResponse(int request_code, String response) {
        super.onResponse(request_code, response);
        try {
            Gson gson = new Gson();
            JSONObject bObj = new JSONObject(response);
            JSONObject dataobj = bObj.getJSONObject("data");
            JSONArray points = null;
            PointContainer pc = new PointContainer();
            switch (request_code) {
                case REQUEST_CITY_POINT: // for  places to visit
                    points = dataobj.getJSONArray(pointTypes[0]);
                    pc.setName(pointTypes[0]);
                    break;
                case REQUEST_CITY_POINT + 1: // for hotel
                    points = dataobj.getJSONArray(pointTypes[1]);
                    pc.setName(pointTypes[1]);
                    break;
                case REQUEST_CITY_POINT + 2: // for things to do
                    points = dataobj.getJSONArray(pointTypes[2]);
                    pc.setName(pointTypes[2]);
                    break;
            }

            if(points != null) {
                ArrayList<Point> pointList = gson.fromJson(points.toString(), new TypeToken<ArrayList<Point>>() {
                }.getType());
                pc.setPoints(pointList);
                cityPoints.add(pc);
                cpva.notifyDataSetChanged();
            }else{
                Slog.e("points is null line 100 cityDetailsActivity");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRecAdapter (){
        cpva = new CityPointVerAdapter(bContext, cityPoints);
        rv_city_points.setAdapter(cpva);
    }

    public void showAllPoints(int pos){
        Intent intent = new Intent(bContext, PointListActivity.class);
        intent.putExtra("_id", city.get_id());
        intent.putExtra("typePos", pos);
        startActivity(intent);
    }
}