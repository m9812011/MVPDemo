package melvinlin.com.mvpdemo.presenter;

/**
 * 1. 负责接收model的返回结果并且处理
 * 2. 将处理的结果以特定的形式，传递给view层，让view层去展示
 * 3. 通知model层去向数据源请求数据
 */
public interface ILoginPresenter {
    void loginToServer();
    void loginSuccess();
    void loginFailed();
}
