package melvinlin.com.mvpdemo.utils;

import melvinlin.com.mvpdemo.AppManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;

public class HttpUtils {
    public static final String REQUEST_TAG = "okhttp";
    private static Call sCall;

    private static Request builderGetRequest(String url) {
     return new Request.Builder()
             .tag(REQUEST_TAG)
             .url(url)
             .build();
    }

    public static void executeByGet(String url, Callback callback) {
        Request request = builderGetRequest(url);
        executeByGet(request, callback);
    }

    private static void executeByGet(Request request, Callback callback) {
        sCall = AppManager.getOkHttpClient().newCall(request);
        sCall.enqueue(callback);

    }

    public static void cancelLatestNewsCall() {
        sCall.cancel();
    }
}
