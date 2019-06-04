package melvinlin.com.mvpdemo.model;

import melvinlin.com.mvpdemo.bean.User;
import melvinlin.com.mvpdemo.presenter.ILoginPresenter;

/**
 * model负责数据的存取
 */
public class LoginModel implements ILoginModel {

    private ILoginPresenter mILoginPresenter;

    public LoginModel(ILoginPresenter mILoginPresenter) {
        this.mILoginPresenter = mILoginPresenter;
    }

    @Override
    public void login(final String userName, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("1111".equals(userName) && "1111".equals(password)) {
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    mILoginPresenter.loginSuccess();
                } else {
                    mILoginPresenter.loginFailed();
                }
            }

        }).start();
    }

}
