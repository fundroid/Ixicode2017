package fundroid.ixicode.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fundroid.ixicode.base.AppController;


/**
 * Created by sagarverma on 25/09/15.
 */
public class VolleyHelper {
    static String HASH = "aq34rfvg56yhnj8iknbgt67uyhbvcde45yhbvcder5ty";
    static String SALT = "s4g4rv07";
    private static int TIMEOUT = 30000;

    public static void postRequestVolley(final Context ctx, String url, final HashMap<String, String> hm, final int request_code) {
        VolleyInterface vi = (VolleyInterface) ctx;
        postRequestVolley(vi, url, hm, request_code);
    }

    public static void postRequestVolley(final VolleyInterface vi, String url, final HashMap<String, String> hm, final int request_code) {
        Slog.d("*******************************************************\n Url: " + url);
        vi.requestStarted(request_code);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Slog.d("*******************************************************\n Response: " + response);
                vi.requestCompleted(request_code, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Slog.e("*******************************************************\n Response with error", "error: " + error.getMessage());
                vi.requestEndedWithError(request_code, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                JSONObject _Data = new JSONObject(hm);
                Map<String, String> params = new HashMap<String, String>();
                Slog.d("*******************************************************\n Request:  " + _Data.toString());
                params.put("data", _Data.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                params.put("Accept", "application/x-www-form-urlencoded");   //application/json
                params.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.0.3; en-us; google_sdk Build/MR1) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
//                Slog.d("******************************************************\n headers");
                return params;
            }
        };

        sr.setRetryPolicy(new DefaultRetryPolicy(25000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(sr);
    }

    /*
    public static void postImageRequest(final Context ctx, String url, String filepath, String regid, final int request_code) {
        final VolleyInterface vi = (VolleyInterface) ctx;
        UploadImageTask uit = new UploadImageTask(vi, url, regid, filepath, request_code);
        uit.execute();
    }
*/

    public static void getRequestVolley(final VolleyInterface vi, String url, final int request_code) {
        Slog.d("*******************************************************\n Url: " + url);
        vi.requestStarted(request_code);
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Slog.d("*******************************************************\n Response: " + response);
                vi.requestCompleted(request_code, response.trim());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Slog.e("*******************************************************\n Response with error", "error: " + error.getMessage());
                vi.requestEndedWithError(request_code, error);
            }
        }) {

        };

        sr.setRetryPolicy(new DefaultRetryPolicy(125000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(sr);
    }

    private static void printHashMapValues(Map<String, String> hm) {
        Set setOfKeys = hm.keySet();
        Iterator iterator = setOfKeys.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = (String) hm.get(key);
            Slog.i("----    ", "Key: " + key + ", Value: " + value);
        }
    }

    public static void postRequestByBodyVolley(final Context ctx, String url, @NonNull final JSONObject params, final int requestCode) {
        final VolleyInterface vi = (VolleyInterface) ctx;

        vi.requestStarted(requestCode);
        Slog.d("URL is::" + url);
        Slog.i("PARAMS is::" + params.toString());
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vi.requestCompleted(requestCode, response);
                Slog.i("response is::" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vi.requestEndedWithError(requestCode, error);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if (params.toString() != null) {

                    String xx = params.toString();
//                    xx = xx.replace("\"", "\\\"");
//                    xx = "\""+xx+"\"";

                    byte[] b = xx.getBytes();
                    return b;
                } else {
                    return super.getBody();
                }
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(sr);
    }

    public static void postRequestByBodyVolley(final Context ctx, String url, @NonNull final String params, final int requestCode) {
        final VolleyInterface vi = (VolleyInterface) ctx;

        vi.requestStarted(requestCode);
        Slog.d("URL is::" + url);
        Slog.i("PARAMS is::" + params.toString());
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vi.requestCompleted(requestCode, response);
                Slog.i("response is::" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vi.requestEndedWithError(requestCode, error);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if (params.toString() != null) {
                    byte[] b = params.toString().getBytes();
                    return b;
                } else {
                    return super.getBody();
                }
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(sr);
    }

    public static void putRequestByBodyVolley(final Context ctx, String url, @NonNull final JSONObject params, final int requestCode, String trackApi) {
        final VolleyInterface vi = (VolleyInterface) ctx;

        vi.requestStarted(requestCode);
        Slog.d("URL is::" + url);
        Slog.i("PARAMS is::" + params.toString());
        StringRequest sr = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                vi.requestCompleted(requestCode, response);
                Slog.i("response is::" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vi.requestEndedWithError(requestCode, error);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if (params.toString() != null) {
                    byte[] b = params.toString().getBytes();
                    return b;
                } else {
                    return super.getBody();
                }
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(sr);
    }


    /*public static String executeMultipartPost(String url, String regid, String filePath) throws Exception {
        try {
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            bm.compress(Bitmap.CompressFormat.JPEG, 75, bos);
//            byte[] data = bos.toByteArray();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url);
//            ByteArrayBody bab = new ByteArrayBody(data, "forest.jpg");
            // File file= new File("/mnt/sdcard/forest.png");
            // FileBody bin = new FileBody(file);
//            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//            reqEntity.addPart("uploaded", bab);
//            reqEntity.addPart("photoCaption", new StringBody("sfsdfsdf"));


            final MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

            try {

                File file = new File(filePath);

                Slog.d("" + ConstantsAPI.USER_REG_ID + "  --   " + regid);
                Slog.d("" + ConstantsAPI.USER_DP + "   --   " + file.getAbsolutePath());

                entity.addPart("sagar", new StringBody("test"));
                entity.addPart(ConstantsAPI.USER_REG_ID, new StringBody(regid));
                entity.addPart(ConstantsAPI.USER_DP, new FileBody(file, "image/jpeg"));

            } catch (Exception e) {
                Slog.e("exception entity:: " + e.getMessage());
                e.printStackTrace();
            }

            postRequest.setEntity(entity);
            HttpResponse response = httpClient.execute(postRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "UTF-8"));
            String sResponse;
            StringBuilder s = new StringBuilder();

            while ((sResponse = reader.readLine()) != null) {
                s = s.append(sResponse);
            }

            return s.toString();
        } catch (Exception e) {
            // handle exception here
            Log.e(e.getClass().getName(), e.getMessage());
        }
        return "";
    }*/

    /*private static class UploadImageTask extends AsyncTask<Void, String, String> {

        private String url, regid, filepath;
        private VolleyInterface vi;
        private int request_code;

        public UploadImageTask(VolleyInterface vi, String url, String regid, String filepath, int request_code) {
            this.url = url;
            this.regid = regid;
            this.filepath = filepath;
            this.vi = vi;
            this.request_code = request_code;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            vi.requestStarted(request_code);
        }

        @Override
        protected String doInBackground(Void... params) {
            String response = "";
            try {
                response = executeMultipartPost(url, regid, filepath);
            } catch (Exception e) {
                Slog.e("error in uploading image :: message: " + e.getMessage());
//                vi.requestEndedWithError(request_code, error);
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null && !response.isEmpty()) {
                vi.requestCompleted(request_code, response);
            }
        }
    }*/
}