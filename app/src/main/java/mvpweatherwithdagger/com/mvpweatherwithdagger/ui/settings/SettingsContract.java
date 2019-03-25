package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.settings;

import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.MvpView;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.Presenter;

public interface SettingsContract {
    interface Outputs extends MvpView {
        void cleanFinishCountries();

    }

    interface Inputs<V extends SettingsContract.Outputs> extends Presenter<V> {
        void cleanCountries();

        void getAllCountries();
    }
}
