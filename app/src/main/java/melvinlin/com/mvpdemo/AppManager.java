package melvinlin.com.mvpdemo;

import android.app.Application;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class AppManager extends Application {

    private static OkHttpClient sClient;
    private static Gson sGon;

    @Override
    public void onCreate() {
        super.onCreate();
        sClient = new OkHttpClient();
        sGon = new Gson();
    }

    public static OkHttpClient getOkHttpClient() {
        if (sClient != null) {
            return sClient;
        }
        return null;
    }

    public static Gson getGson() {
        if (sGon != null) {
            return sGon;
        }
        return null;
    }
}
