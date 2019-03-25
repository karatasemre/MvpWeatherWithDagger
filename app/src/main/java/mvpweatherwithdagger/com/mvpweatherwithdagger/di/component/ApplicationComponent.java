package mvpweatherwithdagger.com.mvpweatherwithdagger.di.component;

import javax.inject.Singleton;

import dagger.Component;
import mvpweatherwithdagger.com.mvpweatherwithdagger.MvpApp;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.CountriesServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.WeatherServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.ApplicationModule;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MvpApp application);

    CountriesServiceManager countriesServisManager();

    WeatherServiceManager weatherServiceManager();
}
