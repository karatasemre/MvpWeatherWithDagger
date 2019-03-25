package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();

    //    ApiClient apiClient();
    void onApiError(Error error);

    void userLoggedOut();

    void openActivityWhenUserLoggedOut();
}

