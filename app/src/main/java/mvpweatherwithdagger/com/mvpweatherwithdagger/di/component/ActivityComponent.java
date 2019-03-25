package mvpweatherwithdagger.com.mvpweatherwithdagger.di.component;


import dagger.Component;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.ActivityModule;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.PresenterModule;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.SharedPreferencesModule;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.scope.PerActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.MainActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BaseActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BaseFragment;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.countries.CountriesFragment;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.settings.SettingsFragment;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.weather.WeatherFragment;

/**
 * Created by Emre.Karatas on 10.10.2018.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PresenterModule.class, SharedPreferencesModule.class})
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

    void inject(WeatherFragment weatherFragment);

    void inject(CountriesFragment countriesFragment);

    void inject(SettingsFragment settingsFragment);

}
