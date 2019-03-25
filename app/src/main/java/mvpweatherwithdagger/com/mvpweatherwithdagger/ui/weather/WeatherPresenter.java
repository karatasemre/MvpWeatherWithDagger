package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.weather;

import com.oneframe.android.networking.listener.NetworkResponseListener;
import com.oneframe.android.networking.model.ErrorModel;
import com.oneframe.android.networking.model.ResultModel;

import javax.inject.Inject;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.WeatherServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.weather.WeatherResponse;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BasePresenter;

public class WeatherPresenter<V extends WeatherContract.Outputs> extends BasePresenter<V> implements WeatherContract.Inputs<V> {
    @Inject
    WeatherServiceManager weatherServiceManager;

    @Inject
    public WeatherPresenter() {
    }

    @Override
    public void getWeatherInfo(String country) {
        weatherServiceManager.getWeatherInfos(country, new NetworkResponseListener<WeatherResponse, String>() {
            @Override
            public void onSuccess(ResultModel<WeatherResponse> resultModel) {
                getMvpView().setWeatherInfo(resultModel.getModel());
            }

            @Override
            public void onError(ErrorModel<String> errorModel) {
                getMvpView().errorInfo(errorModel.getData());
            }
        });
    }
}
