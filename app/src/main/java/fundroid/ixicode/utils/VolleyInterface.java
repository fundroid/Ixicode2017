package fundroid.ixicode.utils;

import com.android.volley.VolleyError;

/**
 * Created by sagarverma on 25/09/15.
 */
public interface VolleyInterface {

    public void requestStarted(int request_code);

    public void requestCompleted(int request_code, String response);

    public void requestEndedWithError(int request_code, VolleyError error);
}
