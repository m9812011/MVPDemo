package melvinlin.com.mvpdemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import melvinlin.com.mvpdemo.LoginSuccessActivity;
import melvinlin.com.mvpdemo.R;
import melvinlin.com.mvpdemo.presenter.LoginPresenter;

/**
 *  View 層實現類別,負責處理 UI 的邏輯
 */
public class LoginActivity extends AppCompatActivity implements ILoginView{

    private Button mLoginBtn;
    private EditText mUserName;
    private EditText mPassword;
    private ProgressBar mProgressBar;

    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginPresenter = new LoginPresenter(this);
        initView();

    }

    private void initView() {
        mLoginBtn = (Button) findViewById(R.id.id_btn_login);
        mUserName = (EditText) findViewById(R.id.id_et_username);
        mPassword = (EditText) findViewById(R.id.id_et_password);
        mProgressBar = (ProgressBar) findViewById(R.id.id_progress_bar);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.loginToServer();
            }
        });
    }

    @Override
    public String getUserName() {
        String userName = mUserName.getText().toString();
        if (!TextUtils.isEmpty(userName)) {
            return userName;
        }
        return null;
    }

    @Override
    public String getUserPassword() {
        String password = mPassword.getText().toString();
        if (!TextUtils.isEmpty(password)) {
            return password;
        }
        return null;
    }

    @Override
    public void showLoading(final boolean isShow) {
        if (isShow) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void showLoginSuccessView() {
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginFailedView() {
        Toast.makeText(this, "登錄失敗", Toast.LENGTH_SHORT).show();
    }
}
