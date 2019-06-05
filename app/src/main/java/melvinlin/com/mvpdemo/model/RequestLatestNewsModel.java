package melvinlin.com.mvpdemo.model;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import melvinlin.com.mvpdemo.Constants;
import melvinlin.com.mvpdemo.presenter.ILatestNewsPresenter;
import melvinlin.com.mvpdemo.utils.HttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Presenter層建構時關聯
 */
public class RequestLatestNewsModel implements IRequestLatestModel {

    private static final String TAG = "RequestLatestNewsModel";

    private ILatestNewsPresenter mLatestNewsPresenter;

    public RequestLatestNewsModel(ILatestNewsPresenter latestNewsPresenter) {
        this.mLatestNewsPresenter = latestNewsPresenter;
    }

    @Override
    public void requestLatestNews() {
        HttpUtils.execute(Constants.LATEST_NEWS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, ">> onFailure >> ");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d(TAG, ">> onFailure >> Not successful");
                }

                String latestNewsJson = response.body().string();
                if (!TextUtils.isEmpty(latestNewsJson)) {
                    mLatestNewsPresenter.handleData(latestNewsJson);
                }
            }
        });
    }
}
