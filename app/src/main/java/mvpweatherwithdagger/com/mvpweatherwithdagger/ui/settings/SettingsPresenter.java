package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.settings;

import com.oneframe.android.networking.listener.NetworkResponseListener;
import com.oneframe.android.networking.model.ErrorModel;
import com.oneframe.android.networking.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.CountriesServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.WeatherServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.newCountry.CountryModel;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.weather.WeatherResponse;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.shared.MySharedPreferences;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BasePresenter;

public class SettingsPresenter<V extends SettingsContract.Outputs> extends BasePresenter<V> implements SettingsContract.Inputs<V> {

    @Inject
    MySharedPreferences sharedPreferenceUtils;

    @Inject
    WeatherServiceManager weatherServiceManager;

    List<CountryModel> countryList;
    List<CountryModel> availableCountries;

    @Inject
    CountriesServiceManager countriesServiceManager;

    @Inject
    public SettingsPresenter() {
    }

    public void cleanCountries() {
        availableCountries = new ArrayList<>();

        countryList = new ArrayList<>();
        countryList = sharedPreferenceUtils.getCountries();
        for (CountryModel country : countryList) {
            weatherServiceManager = new WeatherServiceManager();
            weatherServiceManager.getWeatherInfos(country.getName(), new NetworkResponseListener<WeatherResponse, String>() {
                @Override
                public void onSuccess(ResultModel<WeatherResponse> resultModel) {
                    availableCountries.add(country);
                    finishCleanCountryOperation(country);

                }

                @Override
                public void onError(ErrorModel<String> errorModel) {
                    finishCleanCountryOperation(country);
                }
            });
        }
    }

    @Override
    public void getAllCountries() {
        countriesServiceManager.getCountries(new NetworkResponseListener<List<CountryModel>, String>() {
            @Override
            public void onSuccess(ResultModel<List<CountryModel>> resultModel) {
                List<CountryModel> resultList = resultModel.getModel();
                sharedPreferenceUtils.setCountries(resultList);
                getMvpView().cleanFinishCountries();

            }

            @Override
            public void onError(ErrorModel<String> errorModel) {
                getMvpView().hideProgress();
            }
        });
    }

    public void finishCleanCountryOperation(CountryModel country) {
        if (country == countryList.get(countryList.size() - 1)) {
            sharedPreferenceUtils.setCountries(availableCountries);
            getMvpView().cleanFinishCountries();
        }
    }
}
