package melvinlin.com.mvpdemo.presenter;

import android.support.annotation.Nullable;

import java.util.HashMap;

import melvinlin.com.mvpdemo.model.IBaseModel;

public interface IBasePresenter<Param> {

    /**
     * 在View层调用的接口，用于通知Model层想服务器发起请求，参数可为空，比如，有些Get方式的请求就不需要参数
     *
     * @param param 请求参数
     */
    void requestServer(@Nullable Param param);

    /**
     *在Model层调用，通过此方法将服务器返回的数据传递给给Presenter层处理
     *
     * @param responseJson 服务器端返回的Json类型数据
     */
    void requestSuccess(String responseJson);

    /**
     * 在View层调用，用于通知Model层取消请求
     *
     */
    void cancelRequest();

    /**
     * 在Model层调用，当网络请求产生错误的时候
     *
     * @param errorCode 错误代码
     * @param errorDesc  错误描述
     * @param errorUrl  发生错误的url
     */
    void okHttpError(int errorCode, String errorDesc, String errorUrl);

    /**
     * 在子类中调用，用于拿到Model对象
     *
     * @return IBaseModel
     */
    IBaseModel getModel();

    /**
     * 在Model层中调用，此方法用于获取Presenter层处理好的参数
     */
    HashMap<String, String> getParams();
}
