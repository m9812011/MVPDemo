package melvinlin.com.mvpdemo.presenter;

import android.text.TextUtils;

import melvinlin.com.mvpdemo.model.ILoginModel;
import melvinlin.com.mvpdemo.model.LoginModel;
import melvinlin.com.mvpdemo.view.ILoginView;

/**
 *
 * P層實現類別,負責處理業務的邏輯
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;
    private ILoginModel mLoginModel;

    public LoginPresenter(ILoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModel(this);
    }

    @Override
    public void loginToServer() {
        String userName = mLoginView.getUserName();
        String password = mLoginView.getUserPassword();

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            mLoginView.showLoading(true);
            mLoginModel.login(userName, password);
        }
    }

    @Override
    public void loginSuccess() {
        mLoginView.showLoading(false);
        mLoginView.showLoginSuccessView();
    }

    @Override
    public void loginFailed() {
        mLoginView.showLoading(false);
        mLoginView.showLoginFailedView();
    }
}
