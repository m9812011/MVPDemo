package melvinlin.com.mvpdemo.view;

/**
 * 1. 从EditText中拿到用户输入的userName
 * 2. 从EditText中拿到用户输入的password
 * 3. 在登录过程中需要展示一个progressbar，登录过程结束之后隐藏这个progressbar
 * 4. 展示登录成功后的view
 * 5. 展示登录失败后的view
 */
public interface ILoginView {
    String getUserName();
    String getUserPassword();
    void showLoading(boolean isShow);
    void showLoginSuccessView();
    void showLoginFailedView();
}
