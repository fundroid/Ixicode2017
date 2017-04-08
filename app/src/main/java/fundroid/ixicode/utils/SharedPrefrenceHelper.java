package fundroid.ixicode.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Sagar Sagar Verma on 08-04-2017.
 */

public class SharedPrefrenceHelper {

    public static final String PREF_KEY = "fun.ixigo";
    private static final String ACCOUNT_LOGIN = "login-status";

    public static SharedPreferences defaultPrefrences;

    public static SharedPreferences getDefaultPref(Context context) {
        if (defaultPrefrences == null) {
            defaultPrefrences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        }
        return defaultPrefrences;
    }

    public static void setIsLogin(Context context, boolean islogin) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putBoolean(ACCOUNT_LOGIN, islogin);
        editor.commit();
        Slog.d(" mky:setIsLogin : " + "ISLOGIN : " + islogin);
    }

    public static boolean getIsLogin(Context context) {
        return getDefaultPref(context).getBoolean(ACCOUNT_LOGIN, false);
    }
}
