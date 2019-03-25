package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base;


import android.content.Context;

import javax.inject.Inject;

import mvpweatherwithdagger.com.mvpweatherwithdagger.di.qualifier.ActivityContext;


/**
 * Created by Emre on 01/10/2018.
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    V mvpView;

//    @Inject
//    ApiClient apiClient;

    @Inject
    @ActivityContext
    public Context context;

    @Inject
    public BasePresenter() {
        super();
    }

//    @Override
//    public ApiClient apiClient() {
//        return apiClient;
//    }

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    @Override
    public void onApiError(Error error) {
        mvpView.onError(error.getMessage());
    }

    @Override
    public void userLoggedOut() {
        // set data null that related to user session
    }

    @Override
    public void openActivityWhenUserLoggedOut() {
        // write some code that open login activity
    }

    /**
     * Check Methods
     */

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Call Presenter.attachView(MvpView) before using inputs of the Presenter");
        }
    }
}
