package melvinlin.com.mvpdemo.presenter;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import melvinlin.com.mvpdemo.AppManager;
import melvinlin.com.mvpdemo.bean.LatestNews;
import melvinlin.com.mvpdemo.model.IRequestLatestModel;
import melvinlin.com.mvpdemo.model.RequestLatestNewsModel;
import melvinlin.com.mvpdemo.view.ILatestNewsView;

public class LatestNewsPresenter implements ILatestNewsPresenter {

    private static final String TAG = "LatestNewsPresenter";

    private IRequestLatestModel mDataServer;
    private ILatestNewsView mILatestNewsView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public LatestNewsPresenter(ILatestNewsView latestNewsView) {
        this.mILatestNewsView = latestNewsView;
        this.mDataServer = new RequestLatestNewsModel(this);
    }

    @Override
    public void handleData(String jsonData) {
        LatestNews latestNews = AppManager.getGson().fromJson(jsonData, LatestNews.class);
        if (latestNews != null) {
            final List<String> titles = new ArrayList<>();
            List<LatestNews.StoriesBean> stories = latestNews.getStories();
            for (LatestNews.StoriesBean story : stories) {
                titles.add(story.getTitle());
            }
            if (titles.size() != 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mILatestNewsView.showLatestViewTitle(titles);
                    }
                });
            }
        } else {
            Log.d(TAG, " >> handleData >> latestNews is null");
        }
    }

    @Override
    public void getDataFromServer() {
        mDataServer.requestLatestNews();
    }

}
