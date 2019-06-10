package melvinlin.com.mvpdemo.view;

import java.util.List;

/**
 * 为了突出重点，当前View层只做一件事情：就是展示获取的数据
 */
public interface ILatestNewsView extends IBaseView{

    void showLatestViewTitle(List<String> titles);
}
