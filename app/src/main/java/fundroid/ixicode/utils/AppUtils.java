package fundroid.ixicode.utils;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 **/

public class AppUtils {
    public static void setImageUrl(final View view, String url, int error) {
        if (null == url)
            return;
        Picasso.with(view.getContext()).load(Uri.parse(url)).error(error).into(((ImageView) view));
    }

/*    public GeoPoint getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        GeoPoint p1 = null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new GeoPoint((double) (location.getLatitude() * 1E6),
                    (double) (location.getLongitude() * 1E6));

            return p1;
        }
    }*/

}
