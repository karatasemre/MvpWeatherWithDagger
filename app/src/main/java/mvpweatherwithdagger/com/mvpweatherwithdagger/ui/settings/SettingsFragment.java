package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.settings;

import android.view.View;

import javax.inject.Inject;

import butterknife.OnClick;
import mvpweatherwithdagger.com.mvpweatherwithdagger.R;
import mvpweatherwithdagger.com.mvpweatherwithdagger.di.component.ActivityComponent;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.MainActivity;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BaseFragment;

public class SettingsFragment extends BaseFragment implements SettingsContract.Outputs {

    @Inject
    SettingsContract.Inputs<SettingsContract.Outputs> presenter;

    public static SettingsFragment newInstance() {
        SettingsFragment settingsFragment = new SettingsFragment();

        return settingsFragment;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViews(View rootView) {

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
        return R.layout.fragment_settings;
    }

    @Override
    protected int navigationTitleText() {
        return 0;
    }


    @OnClick(R.id.clean_country)
    public void cleanCountry() {
        showProgress();
        presenter.cleanCountries();
    }

    @OnClick(R.id.all_country)
    public void allCountry() {
        showProgress();
        presenter.getAllCountries();
    }

    @Override
    public void cleanFinishCountries() {
        hideProgress();
        MainActivity.instance.changeSettingsToCountry();
    }
}
