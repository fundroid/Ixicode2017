package fundroid.ixicode.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    /**
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static String getStringFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getString(key);
        }
        return "";
    }

    public static JSONArray getJsonArrayFromJSON(JSONObject json, String key) throws JSONException {
        JSONArray jarr = new JSONArray();
        if (isValidJson(json, key)) {
            try {
                jarr = json.getJSONArray(key);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return jarr;

    }

    public static JSONObject getJsonObjFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getJSONObject(key);
        }
        return null;
    }

    /**
     * @param json
     * @param key
     */
    private static boolean isValidJson(JSONObject json, String key) {
        if (json == null || !json.has(key)) {
            return false;
        }
        return true;
    }

    /**
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static Long getLongFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getLong(key);
        }
        return 0l;
    }

    /**
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static Integer getIntFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getInt(key);
        }
        return 0;
    }

    public static double getFloatFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getDouble(key);
        }
        return 0.0;
    }

    public static Boolean getBoolFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getBoolean(key);
        }
        return false;
    }


}
