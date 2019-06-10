package melvinlin.com.mvpdemo.model;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import melvinlin.com.mvpdemo.Constants;
import melvinlin.com.mvpdemo.presenter.IBasePresenter;
import melvinlin.com.mvpdemo.utils.HttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LatestNewsModel implements IBaseModel {

    private static final String TAG = "ReqLaNewModelFromBase";
    private String url;
    //默认为Get方式 ，根据这个参数来区别不同方式的请求
    private int method;
    private IBasePresenter mBasePresenter;

    public LatestNewsModel(IBasePresenter basePresenter) {
        mBasePresenter = basePresenter;
    }


    @Override
    public void sendRequestToServer() {
        HttpUtils.executeByGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, ">> onFailure >> ");
                e.printStackTrace();
                mBasePresenter.okHttpError(Constants.URL_ERROR,e.getMessage(),url);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d(TAG, ">> onResponse >> " + "Not successful");
                    mBasePresenter.okHttpError(Constants.SERVER_ERROR,response.message(),url);

                }
                String mLatestNewsJson = response.body().string();
                if (!TextUtils.isEmpty(mLatestNewsJson)) {
                    mBasePresenter.requestSuccess(mLatestNewsJson);
                }
            }
        });
    }

    @Override
    public void setRequestUrl(String url) {
        this.url = url;
    }

    @Override
    public void setMethod(int method) {
        this.method = method;
    }

    @Override
    public void cancelRequest() {
        HttpUtils.cancelLatestNewsCall();
    }
}
