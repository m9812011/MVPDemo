package melvinlin.com.mvpdemo.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import melvinlin.com.mvpdemo.R;
import melvinlin.com.mvpdemo.adapter.LatestNewsAdapter;
import melvinlin.com.mvpdemo.bean.LatestNews;
import melvinlin.com.mvpdemo.presenter.LatestNewsPresenter;

public class LatestNewsTitleActivity extends AppCompatActivity implements ILatestNewsView {

    private ListView mListView;
    private Context mContext;
    private Button mBtnLatestNews;
    private ProgressBar mProgressBar;
    private TextView mTip;
    private TextView mErrorContent;
    private RelativeLayout mSuccessContent;
    private LatestNewsPresenter mBasePresenter;
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);
        mContext = this;

        mBasePresenter = new LatestNewsPresenter(this, LatestNews.class);

        mSuccessContent = (RelativeLayout)findViewById(R.id.id_success_content);
        mErrorContent = (TextView) findViewById(R.id.id_error_content);
        mTip = (TextView) findViewById(R.id.id_tip);
        mListView = (ListView) findViewById(R.id.id_list_view);
        mProgressBar = (ProgressBar)findViewById(R.id.id_progress_bar);
        mBtnLatestNews = (Button) findViewById(R.id.id_btn_latest_news);
        mBtnLatestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasePresenter.requestServer(null);
            }
        });

    }



    @Override
    public void showProgress(final boolean isShow) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (isShow) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void showOkHttpError(int errorCode, String errorDesc, String errorUrl) {
        mTip.setText("http error:"  + "errorCode:" + errorCode + ",errorDesc" + errorDesc + ",errorUrl:" + errorUrl);
    }

    @Override
    public void showServerError(int errorCode, String errorDesc) {
        mTip.setText("server error:"  + "errorCode:" + errorCode + ",errorDesc:" + errorDesc);
    }

    @Override
    public void showSuccess(boolean isSuccess) {
        if (isSuccess) {
            mSuccessContent.setVisibility(View.VISIBLE);
            mErrorContent.setVisibility(View.GONE);
            mBtnLatestNews.setVisibility(View.VISIBLE);
        } else {
            mSuccessContent.setVisibility(View.GONE);
            mErrorContent.setVisibility(View.VISIBLE);
            mBtnLatestNews.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLatestViewTitle(List<String> titles) {
        if (titles != null && titles.size() != 0) {
            LatestNewsAdapter adapter = new LatestNewsAdapter(titles, mContext);
            mListView.setAdapter(adapter);
        }
    }
}
