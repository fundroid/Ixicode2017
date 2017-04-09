package fundroid.ixicode.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.PlaceCatAdapter;
import fundroid.ixicode.base.Apis;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Place;
import fundroid.ixicode.utils.AppUtils;
import fundroid.ixicode.utils.Slog;

import static fundroid.ixicode.base.Apis.URL_FOR_MAP;

public class PlaceDetailsActivity extends BaseActivity {

    private Place place;
    private TextView header_text1, header_text2, header_text3, tv_loc, tv_dir, tv_web;
    private TextView tv_why, tv_how, tv_desc, tv_desc_more;
    private ImageView header_vi;
    private ImageView iv_mapv;
    private LinearLayout ll_det_part;
    private LinearLayout ll_mapview;

    RecyclerView rv_place_cats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        place = (Place) getIntent().getSerializableExtra("place");

        if (place == null) {
            finish();
        }

        setBasicToolBar(place.getName(), R.id.toolbar);
        initViews();
        setPointData();
    }

    private void initViews() {
        header_vi = (ImageView) findViewById(R.id.header_vi);
        iv_mapv = (ImageView) findViewById(R.id.iv_mapv);
        header_text1 = (TextView) findViewById(R.id.header_text1);
        header_text2 = (TextView) findViewById(R.id.header_text2);
        header_text3 = (TextView) findViewById(R.id.header_text3);
        tv_loc = (TextView) findViewById(R.id.tv_location);
        tv_loc.setVisibility(View.GONE);
        tv_dir = (TextView) findViewById(R.id.tv_direction);
        tv_web = (TextView) findViewById(R.id.tv_web);

        tv_why = (TextView) findViewById(R.id.tv_why);
        tv_how = (TextView) findViewById(R.id.tv_how);
        tv_desc = (TextView) findViewById(R.id.tv_desc);

        ll_det_part= (LinearLayout) findViewById(R.id.ll_det_part);
        ll_mapview= (LinearLayout) findViewById(R.id.ll_mapview);
        rv_place_cats= (RecyclerView) findViewById(R.id.rv_place_cats);

        rv_place_cats.setHasFixedSize(false);
        rv_place_cats.setLayoutManager(new LinearLayoutManager(bContext, LinearLayoutManager.HORIZONTAL, false));// Setting the layout Manager

        rv_place_cats.setAdapter(new PlaceCatAdapter(bContext, place.getDestinationCategories()));
    }

    public void clickCity(View v){
        gotoCityDetails(place.getCityId());
    }

    private void setPointData() {
        ll_det_part.setVisibility(View.VISIBLE);
        setBasicToolBar(place.getName(), R.id.toolbar);
        header_text1.setText(place.getName());
        header_text2.setText(place.getStateName() + ", " + place.getCountryName());
        header_text3.setText("Price >" + Apis.RSymbol + place.getData());
        AppUtils.setImageUrl(header_vi, place.getImage(), R.drawable.def_back_w);

        tv_how.setText(place.getCityName());
        tv_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCityDetails(place.getCityId());
            }
        });
        tv_why.setText(place.getStateName());

        String murl = URL_FOR_MAP.replace("<LAT>",""+ place.getLat()).replace("<LNG>","" + place.getLng());
        Slog.d("murl ; " + murl);
        AppUtils.setImageUrl(iv_mapv, murl, R.drawable.def_back_w);
    }
}