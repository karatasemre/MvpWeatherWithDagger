package mvpweatherwithdagger.com.mvpweatherwithdagger;

import android.app.Application;

import com.oneframe.android.networking.NetworkingFactory;

import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ApplicationComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.DaggerApplicationComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.module.ApplicationModule;

public class MvpApp extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

        NetworkingFactory.init(this);

    }
}
