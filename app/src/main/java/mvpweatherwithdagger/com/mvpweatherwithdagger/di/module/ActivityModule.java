package mvpweatherwithdagger.com.mvpweatherwithdagger.di.module;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.qualifier.ActivityContext;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.scope.PerActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.MainActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BaseActivity;

/**
 * Created by Emre.Karatas on 6.11.2018.
 */

@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    @PerActivity
    ProgressDialog provideProgressDialog() {
        return new ProgressDialog(activity);
    }

    @Provides
    @PerActivity
    Resources provideResources(
            @ActivityContext Context context) {
        return context.getResources();
    }

}
