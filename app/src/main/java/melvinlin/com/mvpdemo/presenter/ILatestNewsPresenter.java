package melvinlin.com.mvpdemo.presenter;

public interface ILatestNewsPresenter {
    void handleData(String jsonData);
    void getDataFromServer();
}
