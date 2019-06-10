package melvinlin.com.mvpdemo.view;

// 若一个页面中存在多种类型的数据,则在此处添加泛型
public interface IBaseView {
    /**
     * 进行耗时操作时的用户友好交互接口，比如显示ProgressBar
     *
     * @param isShow
     */
    void showProgress(boolean isShow);

    /**
     * 显示网络请求错的的接口
     *
     * @param errorCode
     * @param errorDesc
     * @param errorUrl
     */
    void showOkHttpError(int errorCode, String errorDesc, String errorUrl);

    /**
     * 现实服务器端请求错误的接口
     *
     * @param errorCode
     * @param errorDesc
     */
    void showServerError(int errorCode, String errorDesc);

    /**
     * 请求成功或者失败之后，对应UI做出改变的接口
     *
     * @param isSuccess
     */
    void showSuccess(boolean isSuccess);

}
