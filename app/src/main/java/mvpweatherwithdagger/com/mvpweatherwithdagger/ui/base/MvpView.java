package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base;

import android.content.res.Resources;
import android.support.annotation.StringRes;

import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ActivityComponent;


/**
 * Created by Emre on 01/10/2018.
 */

public interface MvpView {
    void showProgress();

    void hideProgress();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void hideKeyboard();

    ActivityComponent getActivityComponent();

    Resources resources();
}
