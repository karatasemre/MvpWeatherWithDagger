package mvpweatherwithdagger.com.mvpweatherwithdagger.di.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mvpweatherwithdagger.com.mvpweatherwithdagger.BuildConfig;
import mvpweatherwithdagger.com.mvpweatherwithdagger.MvpApp;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.CountriesServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.WeatherServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.qualifier.ApplicationContext;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */

@Module
public class ApplicationModule {
    private MvpApp application;

    public ApplicationModule(MvpApp application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    @Named("isDebug")
    public boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }


    @Provides
    @Singleton
    WeatherServiceManager provideWeatherServisManager() {
        return new WeatherServiceManager();
    }


    @Provides
    @Singleton
    CountriesServiceManager provideCountriesServisManager() {
        return new CountriesServiceManager();
    }
}
