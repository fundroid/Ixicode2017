package fundroid.ixicode.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fundroid.ixicode.R;
import fundroid.ixicode.base.Apis;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.utils.AppUtils;
import fundroid.ixicode.utils.Slog;

import static fundroid.ixicode.base.Apis.URL_FOR_MAP;

public class PointDetailsActivity extends BaseActivity {

    private Point point;
    private TextView header_text1, header_text2, header_text3,tv_loc, tv_dir, tv_web;
    private TextView tv_why, tv_how, tv_desc, tv_desc_more;
    private ImageView header_vi;
    private LinearLayout ll_det_part;
    private LinearLayout ll_mapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_details);
        point = (Point) getIntent().getSerializableExtra("point");
        setBasicToolBar("" + point.getName(), R.id.toolbar);

        if (point == null) {
            finish();
        }

        initViews();
        setPointData();
    }

    private void initViews(){
        header_vi = (ImageView) findViewById(R.id.header_vi);
        header_text1 = (TextView) findViewById(R.id.header_text1);
        header_text2 = (TextView) findViewById(R.id.header_text2);
        header_text3 = (TextView) findViewById(R.id.header_text3);
        tv_loc = (TextView) findViewById(R.id.tv_location);
        tv_loc.setVisibility(View.GONE);
        tv_dir = (TextView) findViewById(R.id.tv_direction);
        tv_web = (TextView) findViewById(R.id.tv_web);

        tv_why  = (TextView) findViewById(R.id.tv_why );
        tv_how  = (TextView) findViewById(R.id.tv_how );
        tv_desc = (TextView) findViewById(R.id.tv_desc);

//        ll_desc_more= (LinearLayout) findViewById(R.id.ll_desc_more);
        ll_det_part= (LinearLayout) findViewById(R.id.ll_det_part);
        ll_mapview= (LinearLayout) findViewById(R.id.ll_mapview);
    }
    private void setPointData(){
        ll_det_part.setVisibility(View.VISIBLE);

        setBasicToolBar(point.getName(), R.id.toolbar);
        header_text1.setText(point.getName());
        header_text2.setText(point.getStateName() +", " + point.getCountryName());
        header_text3.setText("Price >"+ Apis.RSymbol + point.getMinimumPrice());
        AppUtils.setImageUrl(header_vi, point.getKeyImageUrl(), R.drawable.def_back_w);

        tv_how.setText(point.getAddress());
        tv_why.setText(point.getShortDescription());
        tv_desc.setText(Html.fromHtml(point.getDescription().replace("\n", "").replace("\r", "")));
    }
}