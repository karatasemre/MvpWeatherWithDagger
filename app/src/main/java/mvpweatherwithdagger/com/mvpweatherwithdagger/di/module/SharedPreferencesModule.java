package mvpweatherwithdagger.com.mvpweatherwithdagger.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.scope.PerActivity;

/**
 * Created by Emre.Karatas on 8.11.2018.
 */

@Module
public class SharedPreferencesModule {

    private Context context;

    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerActivity
    SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences("weather", Context.MODE_PRIVATE);
    }
}
