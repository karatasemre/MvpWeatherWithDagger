package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.weather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import mvpweatherwithdagger.com.mvpweatherwithdagger.R;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.weather.WeatherResponse;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.shared.MySharedPreferences;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ActivityComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BaseFragment;

public class WeatherFragment extends BaseFragment implements WeatherContract.Outputs {

    @BindView(R.id.weather_status_icon)
    ImageView weatherStatusIcon;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.weather_status)
    TextView weatherStatus;
    @BindView(R.id.wind_speed)
    TextView windSpeed;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.temp_max)
    TextView tempMax;
    @BindView(R.id.temp_min)
    TextView tempMin;

    @Inject
    WeatherContract.Inputs<WeatherContract.Outputs> presenter;

    @Inject
    MySharedPreferences mySharedPreferences;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews(View rootView) {
        showProgress();
        presenter.getWeatherInfo(mySharedPreferences.getCurrentCity());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }

    @Override
    protected int layoutResourceID() {
        return R.layout.fragment_weather;
    }

    @Override
    protected int navigationTitleText() {
        return 0;
    }

    @Override
    public void setWeatherInfo(WeatherResponse weatherInfo) {
        cityName.setText(weatherInfo.getName());
        weatherStatus.setText(weatherInfo.getWeather().get(0).getDescription());
        humidity.setText(weatherInfo.getMain().getHumidity().toString());
        tempMax.setText(weatherInfo.getMain().getTempMax() + "");
        tempMin.setText(weatherInfo.getMain().getTempMin() + "");
        windSpeed.setText(weatherInfo.getWind().getSpeed().toString());

        Glide.with(getContext()).load("http://openweathermap.org/img/w/" + weatherInfo.getWeather().get(0).getIcon() + ".png").into(weatherStatusIcon);

        hideProgress();
    }

    @Override
    public void errorInfo(String errorMessage) {
        hideProgress();
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void triggerPresenter(String selectedCountry) {
        showProgress();
        presenter.getWeatherInfo(selectedCountry);
    }
}
