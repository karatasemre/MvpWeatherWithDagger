package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.countries;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.oneframe.android.networking.listener.NetworkResponseListener;
import com.oneframe.android.networking.model.ErrorModel;
import com.oneframe.android.networking.model.ResultModel;

import java.util.List;

import javax.inject.Inject;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.CountriesServiceManager;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.newCountry.CountryModel;
import mvpweatherwithdagger.com.mvpweatherwithdagger.data.shared.MySharedPreferences;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.BasePresenter;

public class CountriesPresenter<V extends CountriesContract.Outputs> extends BasePresenter<V> implements CountriesContract.Inputs<V> {
    @Inject
    MySharedPreferences mySharedPreferences;

    @Inject
    CountriesServiceManager countriesServiceManager;

    @Inject
    public CountriesPresenter() {
    }

    @Override
    public void getCountries() {
        countriesServiceManager.getCountries(new NetworkResponseListener<List<CountryModel>, String>() {
            @Override
            public void onSuccess(ResultModel<List<CountryModel>> resultModel) {
                List<CountryModel> resultList = resultModel.getModel();
                mySharedPreferences.setCountries(resultList);
                getMvpView().setCountries(resultList);
            }

            @Override
            public void onError(ErrorModel<String> errorModel) {
                getMvpView().countriesError(errorModel.getData());
            }
        });
    }

    @Override
    public void getLocalCountries() {
        getMvpView().setCountries(mySharedPreferences.getCountries());
    }

    @Override
    public void getFilteredValue(String filterValue) {
        List<CountryModel> resultList = Stream.of(mySharedPreferences.getCountries()).filter(p -> p.getName().toLowerCase().contains(filterValue.toLowerCase())).collect(Collectors.toList());
        getMvpView().setCountries(resultList);
    }
}
