package fundroid.ixicode.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.List;
import java.util.Locale;

import fundroid.ixicode.R;
import fundroid.ixicode.ui.HomeActivity;
import fundroid.ixicode.ui.LoginActivity;
import fundroid.ixicode.utils.SharedPrefrenceHelper;
import fundroid.ixicode.utils.Slog;
import fundroid.ixicode.utils.VolleyHelper;
import fundroid.ixicode.utils.VolleyInterface;


/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 **/

public class BaseActivity extends AppCompatActivity implements VolleyInterface {

    public String RSymbol = "₹";
    protected Context bContext;
    protected Toolbar toolbar;

    private int onStartCount;
    private LayoutInflater inflater;
    private View parentLayout;
    private RelativeLayout screen_area;

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

    protected void gotoHome() {
        Intent intent = new Intent(bContext, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    protected void gotoLogin() {
        Intent intent = new Intent(bContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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

    private void setBasicToolBar(String title, int toolbar_id) {
        try {
            toolbar = (Toolbar) findViewById(toolbar_id);
            setSupportActionBar(toolbar);
            toolbar.setTitle(title);

            TextView tv_title = (TextView) findViewById(R.id.home_label_current_location);
            tv_title.setText("" + title);

            getSupportActionBar().setTitle("" + title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException ne) {
            Slog.e("error in getsupportActionbar centerlist on create");
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

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_white);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

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
        VolleyHelper.getRequestVolley(this, url, rcode);
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
        showProgress("Loading");
    }

    protected void onResponse(int request_code, String response, String data) {
        Slog.d("data : in base activity : " + data.toString());
    }

    @Override
    public void requestCompleted(int request_code, String response) {
        Slog.d("reqqquest completed");
        try {
            JSONObject jobj = new JSONObject(response);
            int rs = JsonUtils.getIntFromJSON(jobj, "rs");
            String msg = JsonUtils.getStringFromJSON(jobj, "msg");
            String data = JsonUtils.getStringFromJSON(jobj, "data");
            BaseVo baseVo = new BaseVo(rs, msg, data);

            if (baseVo != null) {
                if (baseVo.getRs() == 0) {
                    onResponse(request_code, response, data);
                } else {
                    showToast("" + baseVo.getMsg());
                    new AlertDialog.Builder(bContext)
                            .setTitle(R.string.app_name)
                            .setMessage(baseVo.getMsg())
//                            .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    dialogInterface.dismiss();
//                                }
//                            })
                            .show();
                }
            } else {
                showToast("Some Error occured");
            }
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

        View llSubLayout = new View(this);
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

    protected void addFooter(int footerId) {
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

    protected int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Slog.d("RegisterActivity", "I never expected this! Going down, going down!" + e);
            throw new RuntimeException(e);
        }
    }

    public void clickGetLocation(View v){
        Intent intent = new Intent(this, MapActivity.class);
        startActivityForResult(intent, REQUEST_GET_LOCATION);
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
//                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
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

    public void getMgrAssets() {
        String url = URL_GET_ASSETS.replace("<UID>", uid);
        volleyGetHit(url, REQUEST_GET_ASSETS);
    }

    public ArrayList<FuelType> getFuelTypes() {
        String data = SharedPrefrenceHelper.getFuels(bContext);
        if (data.length() > 0) {
            Gson gson = new Gson();
            FuelTypeVo assetVo = gson.fromJson(data, FuelTypeVo.class);
            fuelTypes = assetVo.getAssets();
            return fuelTypes;
        } else {
            String url = URL_FUEL_TYPES;
            volleyGetHit(url, REQUEST_FUEL_TYPES);
            return null;
        }
    }

    public void getBuildings() {
        String url = URL_GET_BUILDS.replace("<UID>", uid);
        volleyGetHit(url, REQUEST_GET_BUILDING);
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

    protected Marker addPointToMap(LatLng loc) {

        if (marker != null) {
            marker.remove();
        }

        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("My Location")
                .snippet("Selected Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));

//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        updateAddress(loc);

        Slog.d("zoom 1: = "+ zoom);

        if(zoom < 10){
            zoom = 13;
        }else{
            zoom = googleMap.getCameraPosition().zoom;
        }
        Slog.d("zoom 2: = "+ zoom);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(loc)      // Sets the center of the map to Mountain View
//                .zoom(googleMap.getCameraPosition().zoom)                   // Sets the zoom
                .zoom(zoom)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        return marker;
    }

    protected void updateAddress(LatLng loc){

    }

    protected String getCompleteAddressString(double LATITUDE, double LONGITUDE) {

        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(",");
                }

                strAdd = strReturnedAddress.toString();
                Slog.d("My Current loction address" + "" + strReturnedAddress.toString());
            } else {
                Slog.d("My Current loction address" + "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Slog.d("My Current loction address" + "Canont get Address!");
        }
        return strAdd;
    }
}