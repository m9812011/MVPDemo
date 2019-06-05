package melvinlin.com.mvpdemo.utils;

import melvinlin.com.mvpdemo.AppManager;
import okhttp3.Callback;
import okhttp3.Request;

public class HttpUtils {
    public static final String REQUEST_TAG = "okhttp";

    private static Request builderGetRequest(String url) {
     return new Request.Builder()
             .tag(REQUEST_TAG)
             .url(url)
             .build();
    }

    public static void execute(String url, Callback callback) {
        Request request = builderGetRequest(url);
        execute(request, callback);
    }

    private static void execute(Request request, Callback callback) {
        AppManager.getOkHttpClient().newCall(request).enqueue(callback);

    }

}
