package fundroid.ixicode.utils;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 **/

public class AppUtils {
    public static void setImageUrl(final View view, String url, int error) {
        if (null == url)
            return;
        Picasso.with(view.getContext()).load(Uri.parse(url)).error(error).into(((ImageView) view));
    }
}
