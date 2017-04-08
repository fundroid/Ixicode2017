package fundroid.ixicode.utils;

import android.text.TextUtils;
import android.util.Log;

import fundroid.ixicode.BuildConfig;


public class Slog {

    static final String TAG = "ixi";
    static boolean DEBUG_MARK = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (DEBUG_MARK) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG_MARK) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG_MARK) {
            if (TextUtils.isEmpty(tag)) {
                Log.d(TAG, msg);
            } else {
                Log.d(tag, msg);
            }
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (DEBUG_MARK) {
            if (TextUtils.isEmpty(tag)) {
                Log.v(TAG, msg);
            } else {
                Log.v(tag, msg);
            }
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (DEBUG_MARK) {
            if (TextUtils.isEmpty(tag)) {
                Log.i(TAG, msg);
            } else {
                Log.i(tag, msg);
            }
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (DEBUG_MARK) {
            if (TextUtils.isEmpty(tag)) {
                Log.e(TAG, msg);
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void e(String msg) {
        if (DEBUG_MARK) {
            Log.e(TAG, msg);
        }
    }

    /**
     * @param tag
     * @param msg
     * @param t
     */
    public static void log(int level, String tag, String msg, Throwable t) {
        if (DEBUG_MARK) {

            switch (level) {
                case Log.ERROR:
                    Log.e(TAG, msg, t);
                    break;
                case Log.DEBUG:
                    Log.d(TAG, msg, t);
                    break;
                case Log.WARN:
                    Log.w(TAG, msg, t);
                    break;
                case Log.INFO:
                    Log.i(TAG, msg, t);
                    break;
                default:
                    Log.e("FATAL", "", new Exception("No supported Log level"));
            }
        }
    }
}
