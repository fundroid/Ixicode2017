package fundroid.ixicode.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fundroid.ixicode.R;
import fundroid.ixicode.model.City;

public class CityDetailsActivity extends AppCompatActivity {

    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        city = (City)getIntent().getSerializableExtra("city");

        if(city == null){
            finish();
        }
    }
}