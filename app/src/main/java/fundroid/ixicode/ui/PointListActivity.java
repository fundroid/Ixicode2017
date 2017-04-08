package fundroid.ixicode.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.PointListVerAdapter;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.model.PointContainer;
import fundroid.ixicode.utils.NetworkUtil;
import fundroid.ixicode.utils.Slog;
import fundroid.ixicode.utils.VolleyHelper;
import fundroid.ixicode.utils.VolleyInterface;

import static fundroid.ixicode.base.API_Requests.REQUEST_CITY_POINT;
import static fundroid.ixicode.base.Apis.URL_CITY_POINTS;
import static fundroid.ixicode.base.Apis.pointTypes;

public class PointListActivity extends BaseActivity {

    private RecyclerView rv_points;
    private PointListVerAdapter adapter;
    private ArrayList<Point> points;
    private String cid;
    private boolean loadinmore;

    private int typePos = 0;

    VolleyInterface vi = new VolleyInterface() {
        @Override
        public void requestStarted(int request_code) {
            if (loadinmore) {
                points.add(new Point(true));
            } else {
                showProgress("Loading...");
            }
        }

        @Override
        public void requestCompleted(int request_code, String response) {

            if (loadinmore) {
                points.remove(points.size() - 1);
            } else {
                hideProgress();
            }

            try {
                Gson gson = new Gson();
                JSONObject bObj = new JSONObject(response);
                JSONObject dataobj = bObj.getJSONObject("data");
                JSONArray pointArr = null;
                PointContainer pc = new PointContainer();
                pointArr = dataobj.getJSONArray(pointTypes[typePos]);
                pc.setName(pointTypes[typePos]);
                if (pointArr != null) {
                    ArrayList<Point> pointList = gson.fromJson(pointArr.toString(), new TypeToken<ArrayList<Point>>() {
                    }.getType());
                    if (pointList == null || pointList.size() < 1) {
                        Slog.d("No More Points Available");
                        adapter.setMoreDataAvailable(false);
                    } else {
                        points.addAll(pointList);
                    }
                } else {
                    Slog.e("points is null line 100 cityDetailsActivity");
                    adapter.setMoreDataAvailable(false);
                }

                adapter.notifyDataChanged();
            } catch (JSONException e) {
                e.printStackTrace();
                adapter.setMoreDataAvailable(false);
            }
        }

        @Override
        public void requestEndedWithError(int request_code, VolleyError error) {
            if (loadinmore) {
                points.remove(points.size() - 1);
            } else {
                hideProgress();
            }
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_list);

        cid = getIntent().getStringExtra("_id");
        typePos = getIntent().getIntExtra("typePos", 0);

        rv_points = (RecyclerView) findViewById(R.id.rv_points);
        rv_points.setHasFixedSize(false);
        rv_points.setLayoutManager(new LinearLayoutManager(this));

        setPointAdapter();
        if (NetworkUtil.isNetworkAvailable(bContext)) {
            load();
        } else {
            showToast("Network not available!");
        }
    }

    private void setPointAdapter() {
        points = new ArrayList<>();
        adapter = new PointListVerAdapter(bContext, points);
        adapter.setLoadMoreListener(new PointListVerAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                rv_points.post(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                    }
                });
            }
        });

        rv_points.setAdapter(adapter);
    }

    protected void load() {
        loadinmore = false;
        points.clear();
        adapter.notifyDataSetChanged();
        getCitySuggestions();
    }

    protected void loadMore() {
        loadinmore = true;
        getCitySuggestions();
    }

    private void getCitySuggestions() {

        if(points!=null){}

        int skip = points.size();
        String url = URL_CITY_POINTS
                .replace("<CITY>", cid)
                .replace("<TYPE>", pointTypes[typePos])
                .replace("<SKIP>", ""+ skip)
                .replace("<LIMIT>", "20")
                .replace(" ", "%20");
        VolleyHelper.getRequestVolley(vi, url, REQUEST_CITY_POINT);
    }
}