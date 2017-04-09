package fundroid.ixicode.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.CityPointVerAdapter;
import fundroid.ixicode.base.Apis;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.City;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.model.PointContainer;
import fundroid.ixicode.utils.AppUtils;
import fundroid.ixicode.utils.JsonUtils;
import fundroid.ixicode.utils.Slog;

import static fundroid.ixicode.base.API_Requests.REQUEST_CITY_POINT;
import static fundroid.ixicode.base.API_Requests.REQUEST_ENT_DETAIL;
import static fundroid.ixicode.base.Apis.URL_CITY_POINTS;
import static fundroid.ixicode.base.Apis.URL_ENT_DETAIL;
import static fundroid.ixicode.base.Apis.URL_FOR_MAP;
import static fundroid.ixicode.base.Apis.pointTypes;

public class CityDetailsActivity extends BaseActivity {

    private City city;
    private RecyclerView rv_city_points;
    private ArrayList<PointContainer> cityPoints;
    private CityPointVerAdapter cpva;

    private TextView header_text1, header_text2, header_text3,tv_loc, tv_dir, tv_web;
    private TextView tv_why, tv_how, tv_desc, tv_desc_more;
    private ImageView header_vi;
    private ImageView iv_map;
    private TextView tv_full_desc;
    private LinearLayout ll_desc_more;
    private LinearLayout ll_det_part;
    private LinearLayout ll_mapview;
    private String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_det);

        cid = getIntent().getStringExtra("cid");

        if (cid == null) {
            finish();
        }

        city = new City();

        getCityDetails();

        initViews();
        fetchPoints();
    }

    private void initViews() {
        header_vi = (ImageView) findViewById(R.id.header_vi);
        iv_map = (ImageView) findViewById(R.id.iv_map);
        header_text1 = (TextView) findViewById(R.id.header_text1);
        header_text2 = (TextView) findViewById(R.id.header_text2);
        header_text3 = (TextView) findViewById(R.id.header_text3);
        tv_loc = (TextView) findViewById(R.id.tv_location);
        tv_dir = (TextView) findViewById(R.id.tv_direction);
        tv_web = (TextView) findViewById(R.id.tv_web);

        tv_why  = (TextView) findViewById(R.id.tv_why );
        tv_how  = (TextView) findViewById(R.id.tv_how );
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_full_desc = (TextView) findViewById(R.id.tv_full_desc);
        tv_desc_more= (TextView) findViewById(R.id.tv_desc_more);
        ll_desc_more= (LinearLayout) findViewById(R.id.ll_desc_more);
        ll_det_part= (LinearLayout) findViewById(R.id.ll_det_part);
        ll_mapview= (LinearLayout) findViewById(R.id.ll_mapview);

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

    private void getCityDetails(){
        String url = URL_ENT_DETAIL.replace("<ENT>", cid);
        volleyGetHit(url, REQUEST_ENT_DETAIL);
    }

    private void getCitySuggestions(int pos) {
        String url = URL_CITY_POINTS.replace("<CITY>", cid)
                .replace("<TYPE>", pointTypes[pos])
                .replace("<SKIP>", "0")
                .replace("<LIMIT>", "10")
                .replace(" ","%20");
        volleyGetHit(url, REQUEST_CITY_POINT + pos );
    }

    @Override
    protected void onResponse(int request_code, String response) {
        super.onResponse(request_code, response);
        if(request_code == REQUEST_ENT_DETAIL){

            try{
                JSONObject jobj = new JSONObject(response.replace("</div>", "").replace("<div>", "").replace("<br />", "").replace("   ", ""));
                JSONObject dataObj  = JsonUtils.getJsonObjFromJSON(jobj, "data");

                city.setName(JsonUtils.getStringFromJSON(dataObj, "name"));
                city.setAddress(JsonUtils.getStringFromJSON(dataObj, "address"));
                city.setStateName(JsonUtils.getStringFromJSON(dataObj, "stateName"));
                city.setCountryName(JsonUtils.getStringFromJSON(dataObj, "countryName"));
                city.setKeyImageUrl(JsonUtils.getStringFromJSON(dataObj, "keyImageUrl"));
                city.setWhyToVisit(JsonUtils.getStringFromJSON(dataObj, "whyToVisit"));
                city.setHowToReach(JsonUtils.getStringFromJSON(dataObj, "howToReach"));
                city.setDescription(JsonUtils.getStringFromJSON(dataObj, "description"));
                city.setShortDescription(JsonUtils.getStringFromJSON(dataObj, "shortDescription"));
                city.setXid(JsonUtils.getIntFromJSON(dataObj, "xid"));
                city.setLat(JsonUtils.getFloatFromJSON(dataObj, "latitude"));
                city.setLon(JsonUtils.getFloatFromJSON(dataObj, "longitude"));
                city.setCid(JsonUtils.getStringFromJSON(dataObj, "id"));

            }catch (JSONException e){
                e.printStackTrace();
            }

            setCityData();

            return ;
        }

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
                ArrayList<Point> pointList = gson.fromJson(points.toString(), new TypeToken<ArrayList<Point>>() {}.getType());
                pc.setPoints(pointList);
                cityPoints.add(pc);
                cpva.notifyDataSetChanged();
            }else{
                Slog.e("points is null line 176 cityDetailsActivity");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCityData(){
        ll_det_part.setVisibility(View.VISIBLE);

        setBasicToolBar(city.getName(), R.id.toolbar);
        header_text1.setText(city.getName());
        header_text2.setText(city.getStateName() +", " + city.getCountryName());
        header_text3.setText(city.getShortDescription());
        AppUtils.setImageUrl(header_vi, city.getKeyImageUrl(), R.drawable.sand);
        AppUtils.setImageUrl(iv_map, URL_FOR_MAP.replace("<LAT>",""+ city.getLat()).replace("<LNG>","" + city.getLon()), R.drawable.def_back_w);

        tv_how.setText(city.getHowToReach());
        tv_why.setText(city.getWhyToVisit());
        Slog.d("desc:   " + city.getDescription());
        tv_desc.setText(Html.fromHtml(city.getDescription().replace("\n", "").replace("\r", "")));
        tv_full_desc.setText(Html.fromHtml(city.getDescription().replace("\n", "").replace("\r", "")));

        tv_desc_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_desc_more.setVisibility(View.VISIBLE);
            }
        });

        tv_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_mapview.setVisibility(View.VISIBLE);
            }
        });

        tv_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap(city.getLat(), city.getLon());
            }
        });

        tv_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Slog.d("url : " + city.getUrl());
                gotoWeb(Apis.BASE_URL + city.getUrl());
            }
        });
    }

    public void closeDescription(View v){
        ll_desc_more.setVisibility(View.GONE);
    }

    public void closeMapView(View v){
        ll_mapview.setVisibility(View.GONE);
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