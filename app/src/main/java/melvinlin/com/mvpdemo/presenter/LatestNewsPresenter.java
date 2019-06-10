package melvinlin.com.mvpdemo.presenter;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import melvinlin.com.mvpdemo.Constants;
import melvinlin.com.mvpdemo.bean.LatestNews;
import melvinlin.com.mvpdemo.view.ILatestNewsView;

/**
 * Presenter層，可控制
 * 1.數據操作 Model 層
 * 2.畫面變化 View 層
 * 3.Handler 處理
 */
public class LatestNewsPresenter extends BasePresenter<Nullable, LatestNews> {

    private static final String TAG = "LatestNewsPresenter";
    private ILatestNewsView mBaseView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public LatestNewsPresenter(ILatestNewsView baseView, Class<LatestNews> clazz) {
        super(baseView, clazz);
        this.mBaseView = baseView;
        getModel().setRequestUrl(Constants.LATEST_NEWS);
    }


    @Override
    public void serverResponse(LatestNews latestNews) {
        if (latestNews != null) {
            final List<String> titles = new ArrayList<>();
            List<LatestNews.StoriesBean> stories = latestNews.getStories();
            for (LatestNews.StoriesBean story: stories) {
                titles.add(story.getTitle());
            }
            if (titles.size() != 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBaseView.showLatestViewTitle(titles);
                        mBaseView.showProgress(false);
                    }
                });
            } else {
                Log.d(TAG, ">> handleData >> latestNews is null");
            }
        }
    }
}
