package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.weather;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.weather.WeatherResponse;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.MvpView;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.Presenter;

public interface WeatherContract {
    interface Outputs extends MvpView {
        void setWeatherInfo(WeatherResponse weatherInfo);

        void errorInfo(String errorMessage);
    }

    interface Inputs<V extends WeatherContract.Outputs> extends Presenter<V> {
        void getWeatherInfo(String country);
    }
}
