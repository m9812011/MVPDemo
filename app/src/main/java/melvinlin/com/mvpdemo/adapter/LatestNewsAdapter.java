package melvinlin.com.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import melvinlin.com.mvpdemo.R;

public class LatestNewsAdapter extends BaseAdapter {

    private List<String> mTitlesList;
    private Context mContext;
    private LayoutInflater mInflater;

    public LatestNewsAdapter(List<String> titles, Context context) {
        this.mTitlesList = titles;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTitlesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTitlesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String title = (String) getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.mInflater.inflate(R.layout.latest_news_title_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.id_tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(title);

        return convertView;
    }

    public void clear() {
        mTitlesList.clear();
    }

    static class ViewHolder {
        TextView title;
    }
}
