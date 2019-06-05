package melvinlin.com.mvpdemo.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import melvinlin.com.mvpdemo.R;
import melvinlin.com.mvpdemo.adapter.LatestNewsAdapter;
import melvinlin.com.mvpdemo.presenter.LatestNewsPresenter;


public class LatestViewActivity extends AppCompatActivity implements ILatestNewsView{

    private ListView mListView;
    private Context mContext;
    private Button mBtnLatestNews;
    private LatestNewsPresenter mLatestNewsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_view);

        mContext = this;
        mLatestNewsPresenter = new LatestNewsPresenter(this);
        mListView = (ListView)findViewById(R.id.id_list_view);
        mBtnLatestNews = (Button)findViewById(R.id.id_btn_latest_news);
        mBtnLatestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLatestNewsPresenter.getDataFromServer();
            }
        });
    }

    @Override
    public void showLatestViewTitle(List<String> titles) {
        if (titles != null && titles.size() != 0) {
            LatestNewsAdapter adapter = new LatestNewsAdapter(titles, mContext);
            mListView.setAdapter(adapter);
        }
    }
}
