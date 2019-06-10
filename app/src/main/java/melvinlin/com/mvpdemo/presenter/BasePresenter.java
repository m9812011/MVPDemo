package melvinlin.com.mvpdemo.presenter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.HashMap;

import melvinlin.com.mvpdemo.model.IBaseModel;
import melvinlin.com.mvpdemo.model.LatestNewsModel;
import melvinlin.com.mvpdemo.view.IBaseView;

/**
 * 其他presenter类的基类，实现了基本的presenter层方法
 * @param <Params>
 * @param <Data>
 */
public abstract class BasePresenter<Params, Data> implements IBasePresenter<Params> {

    private static final String TAG = "BasePresenter";
    private IBaseModel mBaseModel;
    private IBaseView mBaseView;
    private Params params;
    private Class<Data> clazz;

    /**
     * 用于接收model层返回的数据，并且进行处理了之后，回调到view层
     *
     * @param data 泛型参数，在子类中指定，JavaBean类型
     * @return void
     */
    public abstract void serverResponse(Data data);


    public BasePresenter(IBaseView baseView, Class<Data> clazz) {
        this.mBaseView = baseView;
        this.mBaseModel = new LatestNewsModel(this);
        this.clazz = clazz;

    }

    @Override
    public void requestServer(@Nullable Params params) {
        this.params = params;
        mBaseView.showProgress(true);
        getModel().sendRequestToServer();
    }

    @Override
    public void requestSuccess(String responseJson) {
        mBaseView.showProgress(false);
        Gson gson = new Gson();
        Data data = gson.fromJson(responseJson, clazz);
        serverResponse(data);
        mBaseView.showProgress(true);
    }

    @Override
    public void cancelRequest() {
        mBaseModel.cancelRequest();
    }

    @Override
    public void okHttpError(int errorCode, String errorDesc, String errorUrl) {
        mBaseView.showOkHttpError(errorCode, errorDesc, errorUrl);
    }

    @Override
    public IBaseModel getModel() {
        return mBaseModel;
    }

    @Override
    public HashMap<String, String> getParams() {
        return null;
    }
}
