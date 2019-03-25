package mvpweatherwithdagger.com.mvpweatherwithdagger.di.module;

import dagger.Module;
import dagger.Provides;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.countries.CountriesContract;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.countries.CountriesPresenter;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.settings.SettingsContract;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.settings.SettingsPresenter;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.weather.WeatherContract;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.weather.WeatherPresenter;

@Module
public class PresenterModule {

    @Provides
    CountriesContract.Inputs<CountriesContract.Outputs> provideCountriesPresenter(CountriesPresenter<CountriesContract.Outputs> presenter) {
        return presenter;
    }

    @Provides
    SettingsContract.Inputs<SettingsContract.Outputs> provideSettingsPresenter(SettingsPresenter<SettingsContract.Outputs> presenter) {
        return presenter;
    }

    @Provides
    WeatherContract.Inputs<WeatherContract.Outputs> provideWeatherPresenter(WeatherPresenter<WeatherContract.Outputs> presenter) {
        return presenter;
    }
}
