package mvpweatherwithdagger.com.mvpweatherwithdagger.ui.countries;

import java.util.List;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.newCountry.CountryModel;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.MvpView;
import mvpweatherwithdagger.com.mvpweatherwithdagger.ui.base.Presenter;

public interface CountriesContract {
    interface Outputs extends MvpView {
        void setCountries(List<CountryModel> countries);

        void countriesError(String errorMessage);
    }

    interface Inputs<V extends CountriesContract.Outputs> extends Presenter<V> {
        void getCountries();

        void getLocalCountries();

        void getFilteredValue(String filterValue);
    }
}
