package fundroid.ixicode.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import fundroid.ixicode.BuildConfig;
import fundroid.ixicode.R;
import fundroid.ixicode.model.City;
import fundroid.ixicode.model.Place;
import fundroid.ixicode.model.Point;
import fundroid.ixicode.model.RecomPlaces;
import fundroid.ixicode.ui.CityListActivity;
import fundroid.ixicode.ui.CityDetailsActivity;
import fundroid.ixicode.ui.HomeActivity;
import fundroid.ixicode.ui.LoginActivity;
import fundroid.ixicode.ui.PlaceDetailsActivity;
import fundroid.ixicode.ui.PointDetailsActivity;
import fundroid.ixicode.ui.ProfileActivity;
import fundroid.ixicode.ui.WebViewActivity;
import fundroid.ixicode.utils.NetworkUtil;
import fundroid.ixicode.utils.SharedPrefrenceHelper;
import fundroid.ixicode.utils.Slog;
import fundroid.ixicode.utils.VolleyHelper;
import fundroid.ixicode.utils.VolleyInterface;

import static fundroid.ixicode.base.API_Requests.REQUEST_RECOM;
import static fundroid.ixicode.base.Apis.URL_RECOMENDED;


/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 **/

public class BaseActivity extends AppCompatActivity implements VolleyInterface {

    protected Context bContext;
    protected Toolbar toolbar;

    private int onStartCount;
    private LayoutInflater inflater;
    private View parentLayout;
    private RelativeLayout screen_area;
    private ProgressDialog progressD;
    protected RecomPlaces recom_places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        inflater = getLayoutInflater();

        bContext = this;

        Slog.d("Class Name: " + this.getLocalClassName());

        initUi();
        onStartCount = 1;
        if (savedInstanceState == null) { // 1st time
            this.overridePendingTransition(R.anim.animation_entry_right, R.anim.stay_still);
        } else { // already created so reverse animation
            onStartCount = 2;
        }

        try {
            parentLayout = findViewById(android.R.id.content);
        } catch (Exception e) {
            Slog.e("error in parent layout" + e.getMessage());
        }
    }


    private void initUi() {
        screen_area = (RelativeLayout) findViewById(R.id.screen_area);
    }

    public void gotoHome() {
        Intent intent = new Intent(bContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

  public void gotoProfile() {
        Intent intent = new Intent(bContext, ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void gotoHome(RecomPlaces places) {
        Intent intent = new Intent(bContext, HomeActivity.class);
        intent.putExtra("recom", places);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void gotoWeb(String url) {
        Intent intent = new Intent(bContext, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void goToMap(double lat, double lng) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lng));
        startActivity(intent);
    }

   public void gotoCityDetails(City city) {
        Intent intent = new Intent(bContext, CityDetailsActivity.class);
        intent.putExtra("cid", city.get_id());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

   public void gotoCityDetails(String cid) {
        Intent intent = new Intent(bContext, CityDetailsActivity.class);
        intent.putExtra("cid", cid);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void gotoLogin() {
        Intent intent = new Intent(bContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void gotoCity(int requestCode) {
        Intent intent = new Intent(bContext, CityListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, requestCode);
    }


    public void gotoPlaceDetalis(Place place){
        Intent intent = new Intent(bContext, PlaceDetailsActivity.class);
        intent.putExtra("place", place);
        startActivity(intent);
    }
    public void showpointDetails(Point point){
        Intent intent = new Intent(bContext, PointDetailsActivity.class);
        intent.putExtra("point", point);
        startActivity(intent);
    }
    public void logout() {
        clearUserData();
    }

    public void clearUserData() {
        //clear all saved preferences here
    }

    protected boolean isGuest() {
        return !SharedPrefrenceHelper.getIsLogin(this);
    }

    protected void setBasicToolBar(int resId) {
        setBasicToolBar(getString(resId), R.id.toolbar);
    }

    protected void setBasicToolBar(String title) {
        setBasicToolBar(title, R.id.toolbar);
    }

    protected void setBasicToolBar(String title, int toolbar_id) {
        try {
            toolbar = (Toolbar) findViewById(toolbar_id);
            setSupportActionBar(toolbar);
            toolbar.setTitle(title);

            TextView tv_title = (TextView) findViewById(R.id.home_label_current_location);
            tv_title.setText("" + title);

            getSupportActionBar().setTitle("" + title);
//            getSupportActionBar().setTitle(Html.fromHtml("<small>" + title + "</small>"));

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }
    }

    protected void setTransparentToolbar(int rid) {

        toolbar = (Toolbar) findViewById(rid);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayUseLogoEnabled(false);

//        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_white);
//        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        setStatusBarTranslucent(true);
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
    }

    public void volleyGetHit(String url, int rcode) {
        if (NetworkUtil.isNetworkAvailable(bContext)){
            VolleyHelper.getRequestVolley(this, url, rcode);
        }
    }

    public void volleyPostHit(String url, HashMap hm, int rcode) {
        VolleyHelper.postRequestVolley(bContext, url, hm, rcode);
    }

    public void volleyBodyHit(String url, String json, int rcode) {
        try {
            JSONObject jObj = new JSONObject(json);
            VolleyHelper.postRequestByBodyVolley(bContext, url, jObj, rcode);
        } catch (Exception e) {
            Slog.e("exception creating json object");
            e.printStackTrace();
        }
    }

    @Override
    public void requestStarted(int request_code) {
        Slog.d("reqquest started");
//        showProgress("Loading");
    }

    /**
     * get api response here
     */
    protected void onResponse(int request_code, String response) {
        if(request_code == REQUEST_RECOM){
            Slog.d("success ------------------");

            try {
                Gson gson = new Gson();
                JSONObject bObj = new JSONObject(response);
                JSONObject dataobj = bObj.getJSONObject("data");
//                JSONArray flights = dataobj.getJSONArray("flight");
//                places = gson.fromJson(dataobj.toString(), new TypeToken<ArrayList<Place>>() {}.getType());
                recom_places = gson.fromJson(dataobj.toString(), RecomPlaces.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    } //override this in child class

    @Override
    public void requestCompleted(int request_code, String response) {
        Slog.d("reqqquest completed");
        Slog.d("response : " + response);
        try {
            onResponse(request_code, response);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Some Error occured");
        }

        hideProgress();
    }

    @Override
    public void requestEndedWithError(int request_code, VolleyError error) {
        Slog.d("reqqquest error");
        hideProgress();
        onFailed(request_code, error);
    }

    protected void onFailed(int request_code, VolleyError error) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.stay_still, R.anim.animation_exit_right);
        } else if (onStartCount == 1) {
            onStartCount++;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.overridePendingTransition(R.anim.stay_still, R.anim.animation_exit_right);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String screen_name = getClass().getSimpleName();
        Slog.d("screen : " + screen_name);
//        AppController.getInstance().trackScreenView(screen_name);

        // Resuming the periodic location updates
    }

    protected void setscreenLayout(int layout_id) {

        View llSubLayout ;
        if (inflater == null) {
            inflater = getLayoutInflater();
        }
        llSubLayout = (View) inflater.inflate(layout_id, null);
        try {
            screen_area.removeAllViews();
            screen_area.removeAllViewsInLayout();
            screen_area = (RelativeLayout) findViewById(R.id.screen_area);
        } catch (Exception e) {
            e.printStackTrace();
        }
        screen_area.addView(llSubLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /*protected void addFooter(int footerId) {
        View footer = (View) inflater.inflate(footerId, null);
        ll_footer_area.setVisibility(View.VISIBLE);
        try {
            ll_footer_area.removeAllViews();
            ll_footer_area.removeAllViewsInLayout();
            ll_footer_area = (LinearLayout) findViewById(R.id.ll_footer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ll_footer_area.addView(footer);
    }
*/
    protected int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Slog.d("RegisterActivity", "I never expected this! Going down, going down!" + e);
            throw new RuntimeException(e);
        }
    }

    protected void showSnackbar(String msg) {
        if (parentLayout != null) {
            Snackbar snack = Snackbar.make(parentLayout, "" + msg, Snackbar.LENGTH_LONG);

            View view = snack.getView();
            view.setBackgroundResource(R.color.colorPrimary);
            TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setBackgroundResource(R.color.primary);
            tv.setTextColor(Color.WHITE);
            snack.show();
        }
    }

    protected void showProgress() {
        showProgress("Loading...");
    }

    public void showProgress(String msg) {
        //create progress progressD and show
        if (null == progressD) {
            progressD = new ProgressDialog(BaseActivity.this, ProgressDialog.STYLE_SPINNER);
            progressD.setMessage(msg);
            progressD.setCanceledOnTouchOutside(false);
            progressD.setIndeterminate(false);
        }

        if (!progressD.isShowing())
            progressD.show();
    }

    public void hideProgress() {
        if (null != progressD && progressD.isShowing()) {
            progressD.dismiss();
        }
    }

    public void showToast(String msg) {
        if (null != msg && !TextUtils.isEmpty(msg))
            Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLogToast(String msg) {
        if (BuildConfig.DEBUG)
            if (null != msg && !TextUtils.isEmpty(msg)) {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.stay_still, R.anim.animation_exit_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    protected void showExitDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BaseActivity.super.onBackPressed();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        dialog.show();
    }

    //calling the recommendations api to get recommended places.
    protected void getRecommendations(){
        volleyGetHit(URL_RECOMENDED, REQUEST_RECOM);
    }
}