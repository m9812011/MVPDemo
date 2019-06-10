package melvinlin.com.mvpdemo.model;

public interface IBaseModel {

    /**
     * 向服务器请求数据的方法
     */
    void sendRequestToServer();

    /**
     * 设置请求url地址的接口
     *
     * @param url 请求地址
     */
    void setRequestUrl(String url);

    /**
     * 设置请求方式的接口，get post。
     *
     * @param method 请求方式
     */
    void setMethod(int method);

    /**
     * 取消请求的接口
     */
    void cancelRequest();

}
