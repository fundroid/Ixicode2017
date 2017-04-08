package fundroid.ixicode.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import fundroid.ixicode.R;
import fundroid.ixicode.adapters.PlaceHorAdapter;
import fundroid.ixicode.base.BaseActivity;
import fundroid.ixicode.model.RecomPlaces;

import static fundroid.ixicode.base.API_Requests.REQUEST_RECOM;

public class HomeActivity extends BaseActivity {

    RecyclerView rv_recom;
//    EditText et_from, et_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setscreenLayout(R.layout.activity_home_new);

        recom_places = (RecomPlaces) getIntent().getSerializableExtra("recom");
        initViews();

        setRecomAdapter();
    }

    private void initViews() {

//        et_from = (EditText)findViewById(R.id.et_from);
//        et_to = (EditText)findViewById(R.id.et_to);

//        et_from.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotoCity(1);
//            }
//        });
//        et_to.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                gotoCity(2);
//            }
//        });
        rv_recom = (RecyclerView) findViewById(R.id.rv_recom);
        rv_recom.setHasFixedSize(false);
        rv_recom.setLayoutManager(new LinearLayoutManager(bContext, LinearLayoutManager.HORIZONTAL, false));// Setting the layout Manager
//        rv_recom.setLayoutManager(new LinearLayoutManager(this));
//        rv_recom.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void clickCityTo(View v){
        gotoCity(1);
    }

    private void setRecomAdapter() {
        if (recom_places != null) {
            if (recom_places.getFlight() != null && recom_places.getFlight().size() > 0) {
                PlaceHorAdapter pAdapter = new PlaceHorAdapter(bContext, recom_places.getFlight());
                rv_recom.setAdapter(pAdapter);
            }
        } else {
            getRecommendations();
        }
    }

    @Override
    protected void onResponse(int request_code, String response) {
        super.onResponse(request_code, response);
        if (request_code == REQUEST_RECOM) {
            if (recom_places != null) {
                setRecomAdapter();
            }
        }
    }
}